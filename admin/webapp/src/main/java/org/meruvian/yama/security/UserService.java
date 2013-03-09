/**
 * Copyright 2012 BlueOxygen Technology
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0 
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.meruvian.yama.security;

import java.io.IOException;
import java.util.Arrays;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.meruvian.yama.security.user.BackendUser;
import org.meruvian.yama.security.user.service.BackendUserService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

/**
 * @author Dian Aditya
 * 
 */
public class UserService extends SavedRequestAwareAuthenticationSuccessHandler
		implements UserDetailsService {

	@Inject
	private BackendUserService userService;

	@Override
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {
		BackendUser user = userService.getByUsername(username);

		if (user != null) {
			UserDetails details = new User(user.getUsername(),
					user.getPassword(),
					Arrays.asList(new SimpleGrantedAuthority(user.getRole())));

			return details;
		}

		return null;
	}

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request,
			HttpServletResponse response, Authentication authentication)
			throws IOException, ServletException {
		User user = (User) authentication.getPrincipal();
		BackendUser backendUser = userService.getByUsername(user.getUsername());

		request.getSession().setAttribute(
				SessionCredentials.YAMA_SECURITY_USER, backendUser);

		if (StringUtils.isBlank(request.getParameter("redirectUri"))) {
			super.onAuthenticationSuccess(request, response, authentication);
		} else {
			setTargetUrlParameter("redirectUri");
			handle(request, response, authentication);
		}
	}
}
