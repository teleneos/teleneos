/**
 * 
 */
package org.teleneos.noc.telecentre;

import javax.inject.Inject;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.ldap.core.ContextMapper;
import org.springframework.ldap.core.DirContextAdapter;
import org.springframework.ldap.core.DirContextOperations;
import org.springframework.ldap.core.DistinguishedName;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.core.support.LdapContextSource;
import org.springframework.stereotype.Repository;

/**
 * @author Dian Aditya
 * 
 */
@Repository("ldapTelecentreRepository")
public class LdapTelecentreRepository implements TelecentreRepository {

	private LdapTemplate ldapTemplate;

	@Value("${ldap.tele.search_base}")
	private String searchBase;

	@Inject
	public void setContextSource(LdapContextSource source) {
		ldapTemplate = new LdapTemplate(source);
	}

	@Override
	public Telecentre findById(String id) {
		DistinguishedName dn = new DistinguishedName(searchBase);
		dn.add("cn", id);

		return (Telecentre) ldapTemplate.lookup(dn,
				new TelecentreContextMapper());
	}


	@Override
	public void persist(Telecentre telecentre) {
		boolean persist = StringUtils.isBlank(telecentre.getId());

		DirContextOperations ctx = null;
		DistinguishedName dn = new DistinguishedName(searchBase);

		if (persist) {
			dn.add("cn", RandomStringUtils.random(6,
					"ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz"));
			ctx = new DirContextAdapter(dn);
		} else {
			dn.add("cn", telecentre.getId());
			ctx = ldapTemplate.lookupContext(dn);
		}

		ctx.setAttributeValues("objectClass", new Object[] { "inetOrgPerson",
				"simpleSecurityObject" });

		ctx.setAttributeValue("mail", telecentre.getEmail());
		ctx.setAttributeValue("sn", telecentre.getName());
		ctx.setAttributeValue("telephoneNumber", telecentre.getPhone());
		ctx.setAttributeValue("street", telecentre.getAddress().getStreet1());
		ctx.setAttributeValue("userPassword", telecentre.getPassword());
		ctx.setAttributeValue("postalCode", telecentre.getAddress().getZip());
		ctx.setAttributeValue("l", StringUtils.join(
				new Object[] { telecentre.getLat(), telecentre.getLng() }, ','));

		if (persist) {
			ldapTemplate.bind(ctx);
		} else {
			ldapTemplate.modifyAttributes(ctx);
		}
	}

	private class TelecentreContextMapper implements ContextMapper {
		@Override
		public Object mapFromContext(Object ctx) {
			DirContextAdapter context = (DirContextAdapter) ctx;

			Telecentre telecentre = new Telecentre();
			telecentre.setId(context.getStringAttribute("cn"));
			telecentre.setEmail(context.getStringAttribute("mail"));
			telecentre.setName(context.getStringAttribute("sn"));
			telecentre.setPhone(context.getStringAttribute("telephoneNumber"));
			byte[] bs = (byte[]) context.getObjectAttribute("userPassword");
			telecentre.setPassword(new String(bs));
			telecentre.getAddress().setStreet1(
					context.getStringAttribute("street"));
			telecentre.getAddress().setZip(
					context.getStringAttribute("postalCode"));

			String[] coord = StringUtils.split(context.getStringAttribute("l"),
					',');
			if (coord != null) {
				if (coord.length == 2) {
					telecentre.setLat(new Double(coord[0]));
					telecentre.setLng(new Double(coord[1]));
				}
			}

			return telecentre;
		}

	}
}
