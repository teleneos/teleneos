/**
 * 
 */
package org.meruvian.yama.security.user.repository;

import org.meruvian.yama.persistence.EntityListWrapper;
import org.meruvian.yama.security.user.User;

/**
 * @author Dian Aditya
 * 
 */
public interface UserRepository {
	User findByUsername(String username);

	EntityListWrapper<User> findByUsername(String username, int limit, int page);

	EntityListWrapper<User> findByTelecentre(String telecentre,
			String username, int limit, int page);

	void persist(User user);
}
