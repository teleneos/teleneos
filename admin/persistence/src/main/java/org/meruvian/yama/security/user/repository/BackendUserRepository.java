/**
 * 
 */
package org.meruvian.yama.security.user.repository;

import org.meruvian.yama.persistence.EntityListWrapper;
import org.meruvian.yama.security.user.BackendUser;

/**
 * @author Dian Aditya
 * 
 */
public interface BackendUserRepository {
	BackendUser findByUsername(String username) throws Exception;

	EntityListWrapper<BackendUser> findByKeyword(String keyword, int limit,
			int page) throws Exception;
}
