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
	User findByUsername(String username) throws Exception;

	EntityListWrapper<User> findByUsername(String username, int limit, int page)
			throws Exception;

	EntityListWrapper<Object[]> findDetailByUsername(String username,
			int limit, int page) throws Exception;

	void persist(User user) throws Exception;
}
