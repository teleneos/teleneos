package net.bogor.itu.persistence;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import net.bogor.itu.service.admin.UserService;

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
