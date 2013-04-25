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

	User findByUsername(String username);

	EntityListWrapper<User> findByUsername(String username, int limit, int page);

	/**
	 * 
	 * @return [username, total download (in byte), total upload (in byte),
	 *         online time (in seconds)]
	 */
	EntityListWrapper<Object[]> findDetailByUsername(String username,
			int limit, int page);

	User remove(User user);

	User changePassword(String username, String newpass);

	void initUser();
}
