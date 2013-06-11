package org.meruvian.yama.security;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

public class YamaLogoutSuccessHandler implements LogoutSuccessHandler {

	@Inject
	private RadiusService radiusService;

	@Override
	public void onLogoutSuccess(HttpServletRequest req,
			HttpServletResponse res, Authentication auth) throws IOException,
			ServletException {
		radiusService.logout(((User) auth.getPrincipal()).getUsername());
		res.sendRedirect("/");
	}

}
