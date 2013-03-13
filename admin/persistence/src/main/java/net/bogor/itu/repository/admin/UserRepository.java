package net.bogor.itu.repository.admin;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import net.bogor.itu.entity.admin.User;
import net.bogor.itu.persistence.PersistenceRepository;

import org.meruvian.yama.persistence.EntityListWrapper;
import org.springframework.stereotype.Repository;

/**
 * @author Dian Aditya
 * 
 */
@Repository
public class UserRepository extends PersistenceRepository<User> {
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
}
