package org.meruvian.yama.persistence;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.meruvian.yama.security.user.service.UserService;
import org.springframework.beans.factory.annotation.Value;

/**
 * @author Dian Aditya
 * 
 */
public class UserIntializer {
	@Inject
	private UserService userService;

	@Value("${init.user}")
	private boolean init;

	@PostConstruct
	public void initUser() {
		if (init) {
			// if (userService.count() < 1) {
			userService.initUser();
			// }
		}
	}
}
