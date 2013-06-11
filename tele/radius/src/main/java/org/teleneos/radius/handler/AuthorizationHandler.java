/**
 * 
 */
package org.teleneos.radius.handler;

import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.NoResultException;

import net.jradius.dictionary.Attr_AuthType;
import net.jradius.dictionary.Attr_UserName;
import net.jradius.dictionary.Attr_UserPassword;
import net.jradius.handler.PacketHandlerBase;
import net.jradius.packet.RadiusPacket;
import net.jradius.packet.attribute.AttributeList;
import net.jradius.server.JRadiusRequest;
import net.jradius.server.JRadiusServer;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.teleneos.radius.userpackage.UserPackage;
import org.teleneos.radius.userpackage.UserPackageService;

/**
 * @author Dian Aditya
 * 
 */
public class AuthorizationHandler extends PacketHandlerBase {
	private static final Log LOG = LogFactory
			.getLog(AuthorizationHandler.class);

	@Inject
	@Named("authManager")
	private AuthenticationManager manager;

	@Inject
	private UserDetailsService userService;

	@Inject
	private UserPackageService packageService;

	@Override
	public boolean handle(JRadiusRequest request) throws Exception {
		AttributeList ci = request.getConfigItems();
		RadiusPacket req = request.getRequestPacket();

		AttributeList rp = req.getAttributes();

		Attr_UserName username = (Attr_UserName) rp.get(Attr_UserName.TYPE);

		Attr_UserPassword password = (Attr_UserPassword) rp
				.get(Attr_UserPassword.TYPE);

		LOG.info("Authorize user: " + username);

		try {
			String usernamee = (String) username.getValue().getValueObject();

			UserDetails user = userService.loadUserByUsername(usernamee);

			Authentication authentication = new UsernamePasswordAuthenticationToken(
					user, new String(password.getValue().getBytes()),
					user.getAuthorities());

			authentication = manager.authenticate(authentication);

			UserPackage userPackage = packageService
					.findActivePackage(usernamee);

			if (userPackage == null) {
				ci.add(new Attr_AuthType("Reject"), true);

				LOG.info("No active package for user: " + user.getUsername());
			} else {
				ci.add(new Attr_AuthType("Accept"), true);
				LOG.info("Authorization success for user: "
						+ user.getUsername());
			}
		} catch (BadCredentialsException e) {
			ci.add(new Attr_AuthType("Reject"), true);
		} catch (NoResultException e) {
			ci.add(new Attr_AuthType("Reject"), true);
		}

		request.setReturnValue(JRadiusServer.RLM_MODULE_UPDATED);
		return false;
	}
}
