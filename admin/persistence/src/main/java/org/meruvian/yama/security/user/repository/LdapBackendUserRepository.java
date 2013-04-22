package org.meruvian.yama.security.user.repository;

import java.util.List;

import javax.inject.Inject;
import javax.naming.InvalidNameException;
import javax.naming.NamingException;
import javax.naming.directory.Attributes;
import javax.naming.directory.SearchControls;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.meruvian.yama.persistence.EntityListWrapper;
import org.meruvian.yama.security.user.BackendUser;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.ldap.control.PagedResult;
import org.springframework.ldap.control.PagedResultsDirContextProcessor;
import org.springframework.ldap.core.AttributesMapper;
import org.springframework.ldap.core.DistinguishedName;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.filter.AndFilter;
import org.springframework.ldap.filter.EqualsFilter;
import org.springframework.stereotype.Repository;

/**
 * @author Dian Aditya
 * 
 */
@Repository("ldapBackendUserRepository")
public class LdapBackendUserRepository implements BackendUserRepository {

	private static final Log LOG = LogFactory
			.getLog(LdapBackendUserRepository.class);

	@Inject
	private LdapTemplate ldapTemplate;

	@Value("${ldap.base}")
	private String baseDn;

	@Value("${ldap.group.search_base}")
	private String groupSearchBase;

	@Value("${ldap.user.search_base}")
	private String userSearchBase;

	@Override
	public BackendUser findByUsername(String username)
			throws InvalidNameException {
		DistinguishedName dn = new DistinguishedName(baseDn);
		dn.add(userSearchBase);
		dn.add("uid", username);

		return (BackendUser) ldapTemplate.lookup(dn,
				new BackendUserAttributesMapper());
	}

	@Override
	public EntityListWrapper<BackendUser> findByKeyword(String keyword,
			int limit, int page) throws InvalidNameException {
		PagedResultsDirContextProcessor processor = new PagedResultsDirContextProcessor(
				100);

		SearchControls controls = new SearchControls();
		controls.setSearchScope(SearchControls.SUBTREE_SCOPE);

		DistinguishedName dn = new DistinguishedName(baseDn);
		dn.add(userSearchBase);

		AndFilter filter = new AndFilter();
		filter.and(new EqualsFilter("objectClass", "person"));
		filter.and(new EqualsFilter("uid", keyword + "*"));

		List results = ldapTemplate.search("", filter.encode(), controls,
				new BackendUserAttributesMapper(), processor);

		PagedResult pagedResult = new PagedResult(results,
				processor.getCookie());

		EntityListWrapper<BackendUser> list = new EntityListWrapper<BackendUser>();
		list.setEntityList(pagedResult.getResultList());

		return list;
	}

	private class BackendUserAttributesMapper implements AttributesMapper {

		@Override
		public Object mapFromAttributes(Attributes attributes)
				throws NamingException {
			BackendUser backendUser = new BackendUser();
			backendUser.setUsername((String) attributes.get("uid").get());
			backendUser.setPassword((String) attributes.get("password").get());
			backendUser.setEmail((String) attributes.get("mail").get());

			DistinguishedName dn = new DistinguishedName(baseDn);
			dn.add(groupSearchBase);

			AndFilter filter = new AndFilter();
			filter.and(new EqualsFilter("objectClass", "groupOfUniqueNames"));
			filter.and(new EqualsFilter("uniqueMember", dn.encode()));

			List roles = ldapTemplate.search("", filter.encode(),
					new AttributesMapper() {
						@Override
						public Object mapFromAttributes(Attributes attributes)
								throws NamingException {
							return attributes.get("cn").get();
						}
					});
			String role = roles.size() > 0 ? ((String) roles.get(0))
					.toUpperCase() : null;
			backendUser.setRole(role);

			return backendUser;
		}
	}
}