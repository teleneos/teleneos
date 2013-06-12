/**
 * 
 */
package org.meruvian.yama.security.user.repository;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import org.meruvian.yama.persistence.EntityListWrapper;
import org.meruvian.yama.persistence.PersistenceRepository;
import org.meruvian.yama.security.user.User;
import org.springframework.stereotype.Repository;

/**
 * @author Dian Aditya
 * 
 */
@Repository("dbUserRepository")
public class DbUserRepository extends PersistenceRepository<User> implements
		UserRepository {
	public User findByUsername(String username) {
		TypedQuery<User> query = createQuery(entityClass, "u", "u",
				"u.user.username = ?1", username);

		try {
			return query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

	public EntityListWrapper<User> findByUsername(String username, int limit,
			int page) {
		return findAll(limit, page, "u", "u.user.username LIKE ?1", username
				+ "%");
	}

	@Override
	public EntityListWrapper<User> findByEmail(String email) {
		return findAll(0, 0, "u", "u.user.email = ?1", email
				+ "");
	}
}
