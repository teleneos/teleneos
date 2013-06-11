/**
 * 
 */
package org.meruvian.yama.security.user.repository;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.inject.Inject;
import javax.naming.InvalidNameException;
import javax.naming.directory.SearchControls;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.meruvian.yama.persistence.EntityListWrapper;
import org.meruvian.yama.persistence.utils.PagingUtils;
import org.meruvian.yama.security.user.Address;
import org.meruvian.yama.security.user.BackendUser;
import org.meruvian.yama.security.user.Name;
import org.meruvian.yama.security.user.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.ldap.control.PagedResult;
import org.springframework.ldap.control.PagedResultsDirContextProcessor;
import org.springframework.ldap.core.ContextMapper;
import org.springframework.ldap.core.DirContextAdapter;
import org.springframework.ldap.core.DirContextOperations;
import org.springframework.ldap.core.DistinguishedName;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.core.support.LdapContextSource;
import org.springframework.ldap.filter.AndFilter;
import org.springframework.ldap.filter.EqualsFilter;
import org.springframework.ldap.filter.WhitespaceWildcardsFilter;
import org.springframework.stereotype.Repository;

/**
 * @author Dian Aditya
 * 
 */
@Repository("ldapUserRepository")
public class LdapUserRepository implements UserRepository {

	private LdapTemplate ldapTemplate;

	@Value("${ldap.user.search_base}")
	private String userSearchBase;

	@Value("${ldap.group.search_base}")
	private String groupSearchBase;

	@Value("${ldap.teleuser.search_base}")
	private String teleuserSearchBase;

	private LdapContextSource source;

	private static final SimpleDateFormat DATE = new SimpleDateFormat(
			"dd-MM-yyyy");

	@Inject
	public void setContextSource(LdapContextSource source) {
		ldapTemplate = new LdapTemplate(source);
		this.source = source;
	}

	@Override
	public User findByUsername(String username) {
		DistinguishedName dn = new DistinguishedName(userSearchBase);
		dn.add("uid", username);

		return (User) ldapTemplate.lookup(dn, new UserContextMapper());
	}

	@Override
	public EntityListWrapper<User> findByUsername(String username, int limit,
			int page) {
		PagedResultsDirContextProcessor processor = new PagedResultsDirContextProcessor(
				100);

		SearchControls controls = new SearchControls();
		controls.setSearchScope(SearchControls.SUBTREE_SCOPE);

		AndFilter filter = new AndFilter();
		filter.and(new EqualsFilter("objectClass", "person"));
		filter.and(new WhitespaceWildcardsFilter("uid", username));

		List results = ldapTemplate.search(userSearchBase, filter.encode(),
				controls, new UserContextMapper(), processor);

		PagedResult pagedResult = new PagedResult(results,
				processor.getCookie());

		EntityListWrapper<User> list = new EntityListWrapper<User>();
		list.setEntityList(pagedResult.getResultList());

		return list;
	}

	@Override
	public EntityListWrapper<User> findByTelecentre(String telecentre,
			String username, int limit, int page) {
		DistinguishedName dn = new DistinguishedName(teleuserSearchBase);
		dn.add("cn", telecentre);

		DirContextOperations ctx = ldapTemplate.lookupContext(dn);
		String[] members = ctx.getStringAttributes("uniqueMember");
		int count = members.length;
		int start = limit * page;
		int stop = start + limit;

		EntityListWrapper<User> users = new EntityListWrapper<User>();
		users.setLimit(limit);
		users.setCurrentPage(page);
		users.setRowCount(count);
		users.setTotalPage(PagingUtils.getTotalPage(count, limit));

		if (start > count) {
			return users;
		}

		if (limit < 1 || stop > count) {
			stop = count - 1;
		}

		for (int i = start; i < stop; i++) {
			String member = members[i];
			String[] ms = StringUtils.split(member, ',');

			DistinguishedName userDn = new DistinguishedName(StringUtils.join(
					ArrayUtils.subarray(ms, 0, 2), ','));

			User user = (User) ldapTemplate.lookup(userDn,
					new UserContextMapper());
			users.getEntityList().add(user);
		}

		return users;
	}

	@Override
	public void persist(User user) {
		boolean persist = StringUtils.isBlank(user.getId());

		DirContextOperations ctx = null;
		DistinguishedName dn = new DistinguishedName(userSearchBase);
		dn.add("uid", user.getUser().getUsername());

		if (persist) {
			ctx = new DirContextAdapter(dn);
		} else {
			ctx = ldapTemplate.lookupContext(dn);
		}

		ctx.setAttributeValues("objectClass",
				new Object[] { "inetOrgPerson", "organizationalPerson",
						"person", "top", "simpleSecurityObject" });

		Name name = user.getName();
		Address address = user.getAddress();
		BackendUser bUser = user.getUser();

		ctx.setAttributeValue("cn", name.getFirst());
		if (!StringUtils.isBlank(name.getLast()))
			ctx.setAttributeValue("sn", name.getLast());
		else
			ctx.setAttributeValue("sn", "-");
		ctx.setAttributeValue("title", name.getTitle());
		ctx.setAttributeValue("street", address.getStreet1());
		ctx.setAttributeValue("postalCode", address.getZip());
		ctx.setAttributeValue("uid", bUser.getUsername());
		ctx.setAttributeValue("mail", bUser.getEmail());
		ctx.setAttributeValue("userPassword", bUser.getPassword());
		ctx.setAttributeValue("description", DATE.format(user.getBirthDate()));
		ctx.setAttributeValue("employeeNumber", user.getIdcard().trim());
		if (!StringUtils.isBlank(user.getOccupation()))
			ctx.setAttributeValue("employeeType", user.getOccupation());
		else if (!persist)
			ctx.removeAttributeValue("employeeType",
					ctx.getStringAttribute("employeeType"));
		if (!StringUtils.isBlank(user.getPhone()))
			ctx.setAttributeValue("homePhone", user.getPhone());
		else if (!persist)
			ctx.removeAttributeValue("homePhone",
					ctx.getStringAttribute("homePhone"));

		if (persist) {
			ldapTemplate.bind(ctx);

			try {
				dn = new DistinguishedName(groupSearchBase);
				dn.add("cn", "User");

				DirContextOperations op = ldapTemplate.lookupContext(dn);
				DistinguishedName groupDn = new DistinguishedName(
						source.getBaseLdapPathAsString());
				groupDn.addAll(ctx.getDn());
				op.addAttributeValue("uniqueMember", groupDn.encode());
				ldapTemplate.modifyAttributes(op);
			} catch (InvalidNameException e) {
				throw new RuntimeException(e);
			}
		} else {
			ldapTemplate.modifyAttributes(ctx);
		}
	}

	private class UserContextMapper implements ContextMapper {
		@Override
		public Object mapFromContext(Object ctx) {
			DirContextAdapter context = (DirContextAdapter) ctx;

			Name name = new Name();
			name.setFirst(context.getStringAttribute("cn"));
			name.setLast(context.getStringAttribute("sn"));
			name.setTitle(context.getStringAttribute("title"));

			Address address = new Address();
			address.setStreet1(context.getStringAttribute("street"));
			address.setZip(context.getStringAttribute("postalCode"));

			BackendUser bUser = new BackendUser();
			bUser.setUsername(context.getStringAttribute("uid"));
			bUser.setEmail(context.getStringAttribute("mail"));
			bUser.setPassword(new String((byte[]) context
					.getObjectAttribute("userPassword")));

			String bd = context.getStringAttribute("description");

			User user = new User();
			user.setName(name);
			user.setAddress(address);
			user.setIdcard(context.getStringAttribute("employeeNumber"));
			user.setOccupation(context.getStringAttribute("employeeType"));
			user.setPhone(context.getStringAttribute("homePhone"));
			user.setUser(bUser);
			user.setId(bUser.getUsername());

			try {
				user.setBirthDate(bd == null ? null : DATE.parse(bd));
			} catch (ParseException e) {
			}

			try {
				DistinguishedName dn = new DistinguishedName(
						source.getBaseLdapPathAsString());
				dn.addAll(context.getDn());

				AndFilter filter = new AndFilter();
				filter.and(new EqualsFilter("objectClass", "groupOfUniqueNames"));
				filter.and(new EqualsFilter("uniqueMember", dn.encode()));

				List list = ldapTemplate.search("", filter.encode(),
						new ContextMapper() {
							@Override
							public Object mapFromContext(Object ctx) {
								DirContextAdapter context = (DirContextAdapter) ctx;

								return context.getStringAttribute("cn");
							}
						});

				bUser.setRole(list.isEmpty() ? null : (String) list.get(0));
			} catch (InvalidNameException e) {
				throw new RuntimeException(e);
			}

			return user;
		}
	}
}