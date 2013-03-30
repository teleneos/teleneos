/**
 * 
 */
package net.bogor.itu.service.admin;

import net.bogor.itu.entity.admin.User;

import org.meruvian.yama.persistence.EntityListWrapper;

/**
 * @author Dian Aditya
 * 
 */
public interface UserService {
	User save(User user);

	User findById(String id);

	User findByUsername(String username);

	EntityListWrapper<User> findByUsername(String username, int limit, int page);

	EntityListWrapper<Object[]> findDetailByUsername(String username,
			int limit, int page);

	User remove(User user);
}
