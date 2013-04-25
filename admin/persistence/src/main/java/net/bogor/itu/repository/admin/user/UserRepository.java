/**
 * 
 */
package net.bogor.itu.repository.admin.user;

import net.bogor.itu.entity.admin.User;

import org.meruvian.yama.persistence.EntityListWrapper;

/**
 * @author Dian Aditya
 * 
 */
public interface UserRepository {
	User findByUsername(String username);

	EntityListWrapper<User> findByUsername(String username, int limit, int page);

	void persist(User user);
}
