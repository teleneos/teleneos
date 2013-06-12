/**
 * 
 */
package org.meruvian.yama.security.user.service;

import org.meruvian.yama.persistence.EntityListWrapper;
import org.meruvian.yama.security.user.User;

/**
 * @author Dian Aditya
 * 
 */
public interface UserService {
	User save(User user);

	User findByUsername(String username);

	EntityListWrapper<User> findByUsername(String username, int limit, int page);
	
	EntityListWrapper<User> findByEmail(String email);

	User remove(User user);

	User changePassword(String username, String newpass);

	void initUser();
}
