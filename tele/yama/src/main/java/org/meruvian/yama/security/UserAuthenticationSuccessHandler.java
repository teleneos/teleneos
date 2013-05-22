/**
 * 
 */
package org.meruvian.yama.security;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.meruvian.yama.security.user.BackendUser;
import org.meruvian.yama.security.user.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.ldap.userdetails.InetOrgPerson;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

/**
 * @author Dian Aditya
 * 
 */
public class UserAuthenticationSuccessHandler extends
		SavedRequestAwareAuthenticationSuccessHandler {

	@Inject
	private UserService userService;

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request,
			HttpServletResponse response, Authentication authentication)
			throws IOException, ServletException {

		String username = null;
		String authority = null;

		if (authentication.getPrincipal() instanceof User) {
			User u = (User) authentication.getPrincipal();
			username = u.getUsername();
		} else if (authentication.getPrincipal() instanceof InetOrgPerson) {
			InetOrgPerson person = (InetOrgPerson) authentication
					.getPrincipal();
			username = person.getUsername();
			authority = person.getAuthorities().isEmpty() ? null : person
					.getAuthorities().iterator().next().getAuthority();
		}

		org.meruvian.yama.security.user.User us = userService
				.findByUsername(username);
		BackendUser user = us.getUser();
		if (authority != null)
			user.setRole(authority);

		request.getSession().setAttribute(
				SessionCredentials.YAMA_SECURITY_USER, user);
		request.getSession().setAttribute(
				SessionCredentials.YAMA_SECURITY_USER_DETAIL, us);

		if (StringUtils.isBlank(request.getParameter("redirectUri"))) {
			super.onAuthenticationSuccess(request, response, authentication);
		} else {
			setTargetUrlParameter("redirectUri");
			handle(request, response, authentication);
		}
	}
}
