/**
 * 
 */
package net.bogor.itu.repository.admin.user;

import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import net.bogor.itu.entity.admin.User;
import net.bogor.itu.persistence.PersistenceRepository;

import org.meruvian.yama.persistence.EntityListWrapper;
import org.meruvian.yama.persistence.utils.PagingUtils;
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

	public EntityListWrapper<Object[]> findDetailByUsername(String username,
			int limit, int page) {
		EntityListWrapper<Object[]> list = new EntityListWrapper<Object[]>();
		list.setLimit(limit);
		list.setCurrentPage(page);

		String ql = "SELECT u, SUM(a.acctinputoctets), SUM(a.acctoutputoctets), SUM(a.acctsessiontime) FROM User u JOIN u.user b LEFT OUTER JOIN b.accts a WHERE b.username LIKE ?1 GROUP BY u";

		Query query = entityManager.createQuery(ql);
		query.setParameter(1, username + "%");

		if (limit > 0) {
			query.setMaxResults(limit);
		}

		query.setFirstResult(page * limit);

		list.setEntityList(query.getResultList());

		ql = "SELECT COUNT(u) FROM User u WHERE u.user.username LIKE ?1";

		TypedQuery<Long> lquery = entityManager.createQuery(ql, Long.class);
		lquery.setParameter(1, username + "%");

		list.setRowCount(lquery.getSingleResult());
		list.setTotalPage(PagingUtils.getTotalPage(list.getRowCount(), limit));

		return list;
	}
}
