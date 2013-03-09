package net.bogor.itu.persistence;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.meruvian.yama.security.user.service.BackendUserService;
import org.springframework.beans.factory.annotation.Value;

/**
 * @author Dian Aditya
 * 
 */
public class UserIntializer {
	@Inject
	private BackendUserService userService;

	@Value("${init.user}")
	private boolean init;

	@PostConstruct
	private void initUser() {
		if (init) {
			if (userService.count() < 1) {
				userService.initUser();
			}
		}
	}
}
