/**
 * 
 */
package org.meruvian.yama.security;

import java.util.Arrays;

import javax.inject.Inject;

import org.meruvian.yama.security.user.BackendUser;
import org.meruvian.yama.security.user.service.UserService;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * @author Dian Aditya
 * 
 */
public class UserAuthenticationService implements UserDetailsService {

	@Inject
	private UserService userService;

	@Override
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {
		org.meruvian.yama.security.user.User u = userService
				.findByUsername(username);
		BackendUser user = u.getUser();

		if (user != null) {
			UserDetails details = new User(user.getUsername(),
					user.getPassword(),
					Arrays.asList(new SimpleGrantedAuthority(user.getRole())));

			return details;
		}

		return null;
	}

}
