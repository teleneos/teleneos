/**
 * 
 */
package net.bogor.itu.repository.admin.user;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.inject.Inject;
import javax.naming.InvalidNameException;
import javax.naming.NamingException;
import javax.naming.directory.Attributes;
import javax.naming.directory.SearchControls;

import net.bogor.itu.entity.Address;
import net.bogor.itu.entity.Name;
import net.bogor.itu.entity.admin.User;

import org.apache.commons.lang.StringUtils;
import org.meruvian.yama.persistence.EntityListWrapper;
import org.meruvian.yama.security.user.BackendUser;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.ldap.control.PagedResult;
import org.springframework.ldap.control.PagedResultsDirContextProcessor;
import org.springframework.ldap.core.AttributesMapper;
import org.springframework.ldap.core.DirContextAdapter;
import org.springframework.ldap.core.DirContextOperations;
import org.springframework.ldap.core.DistinguishedName;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.filter.AndFilter;
import org.springframework.ldap.filter.EqualsFilter;
import org.springframework.stereotype.Repository;

/**
 * @author Dian Aditya
 * 
 */
// @Repository("ldapUserRepository")
public class LdapUserRepository implements UserRepository {

	@Inject
	private LdapTemplate ldapTemplate;

	@Value("${ldap.base}")
	private String baseDn;

	@Value("${ldap.user.search_base}")
	private String userSearchBase;

	private static final SimpleDateFormat DATE = new SimpleDateFormat(
			"dd-MM-yyyy");

	@Override
	public User findByUsername(String username) throws InvalidNameException {
		DistinguishedName dn = new DistinguishedName(baseDn);
		dn.add(userSearchBase);
		dn.add("uid", username);

		return (User) ldapTemplate.lookup(dn, new UserAttributesMapper());
	}

	@Override
	public EntityListWrapper<User> findByUsername(String username, int limit,
			int page) throws InvalidNameException {
		PagedResultsDirContextProcessor processor = new PagedResultsDirContextProcessor(
				100);

		SearchControls controls = new SearchControls();
		controls.setSearchScope(SearchControls.SUBTREE_SCOPE);

		DistinguishedName dn = new DistinguishedName(baseDn);
		dn.add(userSearchBase);

		AndFilter filter = new AndFilter();
		filter.and(new EqualsFilter("objectClass", "person"));
		filter.and(new EqualsFilter("uid", username + "*"));

		List results = ldapTemplate.search("", filter.encode(), controls,
				new UserAttributesMapper(), processor);

		PagedResult pagedResult = new PagedResult(results,
				processor.getCookie());

		EntityListWrapper<User> list = new EntityListWrapper<User>();
		list.setEntityList(pagedResult.getResultList());

		return list;
	}

	@Override
	public EntityListWrapper<Object[]> findDetailByUsername(String username,
			int limit, int page) {
		return null;
	}

	@Override
	public void persist(User user) throws InvalidNameException {
		DistinguishedName dn = new DistinguishedName(baseDn);
		dn.add(userSearchBase);
		dn.add("uid", user.getId());

		boolean persist = StringUtils.isBlank(user.getId());

		DirContextOperations ctx = null;

		if (persist) {
			ctx = new DirContextAdapter();
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
		ctx.setAttributeValue("sn", name.getLast());
		ctx.setAttributeValue("title", name.getTitle());
		ctx.setAttributeValue("street", address.getStreet1());
		ctx.setAttributeValue("postalCode", address.getZip());
		ctx.setAttributeValue("uid", bUser.getUsername());
		ctx.setAttributeValue("mail", bUser.getEmail());
		ctx.setAttributeValue("password", bUser.getPassword());
		ctx.setAttributeValue("description", DATE.format(user.getBirthDate()));
		ctx.setAttributeValue("uidNumber", user.getIdcard());
		ctx.setAttributeValue("employeeType", user.getOccupation());
		ctx.setAttributeValue("homePhone", user.getPhone());

		if (persist) {
			ldapTemplate.bind(ctx);
		} else {
			ldapTemplate.modifyAttributes(ctx);
		}
	}

	private class UserAttributesMapper implements AttributesMapper {
		@Override
		public Object mapFromAttributes(Attributes attributes)
				throws NamingException {
			Name name = new Name();
			name.setFirst((String) attributes.get("cn").get());
			name.setLast((String) attributes.get("sn").get());
			name.setTitle((String) attributes.get("title").get());

			Address address = new Address();
			address.setStreet1((String) attributes.get("street").get());
			address.setZip((String) attributes.get("postalCode").get());

			BackendUser bUser = new BackendUser();
			bUser.setUsername((String) attributes.get("uid").get());
			bUser.setEmail((String) attributes.get("mail").get());
			bUser.setPassword((String) attributes.get("password").get());
			bUser.setId(bUser.getUsername());

			String bd = (String) attributes.get("description").get();

			User user = new User();
			user.setName(name);
			user.setAddress(address);
			user.setIdcard((String) attributes.get("uidNumber").get());
			user.setOccupation((String) attributes.get("employeeType").get());
			user.setPhone((String) attributes.get("homePhone").get());
			user.setUser(bUser);
			user.setId(bUser.getId());

			try {
				user.setBirthDate(DATE.parse(bd));
			} catch (ParseException e) {
			}

			return user;
		}
	}

}