package org.meruvian.yama.security;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.ldap.userdetails.InetOrgPerson;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

public class YamaLogoutSuccessHandler implements LogoutSuccessHandler {
	private static final Log LOG = LogFactory
			.getLog(YamaLogoutSuccessHandler.class);
	@Inject
	private RadiusService radiusService;

	@Override
	public void onLogoutSuccess(HttpServletRequest req,
			HttpServletResponse res, Authentication auth) throws IOException,
			ServletException {
		LOG.info("onLogoutSuccess");
		Object principal = auth.getPrincipal();
		if (principal instanceof InetOrgPerson) {
			radiusService.logout(((InetOrgPerson) auth.getPrincipal())
					.getUsername());
		} else if (principal instanceof User) {
			radiusService.logout(((User) auth.getPrincipal()).getUsername());
		}
		res.sendRedirect("/");
	}

}
