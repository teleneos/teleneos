/**
 * 
 */
package net.bogor.itu.radius;

import javax.inject.Inject;
import javax.inject.Named;

import net.jradius.dictionary.Attr_AuthType;
import net.jradius.dictionary.Attr_CHAPChallenge;
import net.jradius.dictionary.Attr_CHAPPassword;
import net.jradius.dictionary.Attr_UserName;
import net.jradius.dictionary.Attr_UserPassword;
import net.jradius.handler.PacketHandlerBase;
import net.jradius.packet.RadiusPacket;
import net.jradius.packet.attribute.AttributeList;
import net.jradius.server.JRadiusRequest;
import net.jradius.server.JRadiusServer;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * @author Dian Aditya
 * 
 */
public class AuthorizationHandler extends PacketHandlerBase {

	@Inject
	@Named("authManager")
	private AuthenticationManager manager;

	@Inject
	private UserDetailsService userService;

	@Override
	public boolean handle(JRadiusRequest request) throws Exception {
		AttributeList ci = request.getConfigItems();
		RadiusPacket req = request.getRequestPacket();

		AttributeList rp = req.getAttributes();

		Attr_UserName username = (Attr_UserName) rp.get(Attr_UserName.TYPE);
		Attr_CHAPPassword chapPassword = (Attr_CHAPPassword) rp
				.get(Attr_CHAPPassword.TYPE);
		Attr_CHAPChallenge chapChallenge = (Attr_CHAPChallenge) rp
				.get(Attr_CHAPChallenge.TYPE);

		Attr_UserPassword password = (Attr_UserPassword) rp
				.get(Attr_UserPassword.TYPE);

		UserDetails user = userService.loadUserByUsername((String) username
				.getValue().getValueObject());

		System.out.println(username.getValue().getValueObject());
		System.out.println(new String(password.getValue().getBytes()));

		Authentication authentication = new UsernamePasswordAuthenticationToken(
				user, new String(password.getValue().getBytes()),
				user.getAuthorities());

		try {
			authentication = manager.authenticate(authentication);
			System.out.println(authentication);

			SecurityContextHolder.getContext()
					.setAuthentication(authentication);

			ci.add(new Attr_AuthType("Accept"), true);
		} catch (BadCredentialsException e) {
			ci.add(new Attr_AuthType("Reject"), true);
		}

		request.setReturnValue(JRadiusServer.RLM_MODULE_UPDATED);
		return false;
	}
}
