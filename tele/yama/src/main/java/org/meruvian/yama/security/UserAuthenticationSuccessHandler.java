/**
 * 
 */
package org.meruvian.yama.security;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.meruvian.yama.security.user.BackendUser;
import org.meruvian.yama.security.user.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.ldap.userdetails.InetOrgPerson;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;

/**
 * @author Dian Aditya
 * 
 */
public class UserAuthenticationSuccessHandler extends
		SavedRequestAwareAuthenticationSuccessHandler {

	@Inject
	private UserService userService;
	
	private RequestCache requestCache = new HttpSessionRequestCache();
	
	@Inject
	private RadiusService radiusService;
	
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
		
		if (user.getRole() != "ADMINISTRATOR") {
			radiusService.login(user.getUsername(), getIpAddr(request));
		} 
		
		SavedRequest savedRequest = requestCache.getRequest(request, response);
		if (savedRequest != null) {
			String redirectUrl = savedRequest.getRedirectUrl();
			if (redirectUrl.endsWith(".json") || redirectUrl.endsWith(".xml")) {
				redirectUrl = "/";
			}
			getRedirectStrategy().sendRedirect(request, response, redirectUrl);
		} else {
			getRedirectStrategy().sendRedirect(request, response, "/");
		}
	}

	@Override
	public void setRequestCache(RequestCache requestCache) {
		super.setRequestCache(requestCache);
		this.requestCache = requestCache;
	}
	
	public static String getIpAddr(HttpServletRequest request) {
		String ip = request.getHeader("X-Real-IP");
		if (null != ip && !"".equals(ip.trim())
				&& !"unknown".equalsIgnoreCase(ip)) {
			return ip;
		}
		ip = request.getHeader("X-Forwarded-For");
		if (null != ip && !"".equals(ip.trim())
				&& !"unknown".equalsIgnoreCase(ip)) {
			int index = ip.indexOf(',');
			if (index != -1) {
				return ip.substring(0, index);
			} else {
				return ip;
			}
		}
		return request.getRemoteAddr();
	}
}
