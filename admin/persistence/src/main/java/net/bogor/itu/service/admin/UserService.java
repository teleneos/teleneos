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
	User save(User user) throws Exception;

	User findByUsername(String username) throws Exception;

	EntityListWrapper<User> findByUsername(String username, int limit, int page)
			throws Exception;

	EntityListWrapper<Object[]> findDetailByUsername(String username,
			int limit, int page) throws Exception;

	User remove(User user);
}
