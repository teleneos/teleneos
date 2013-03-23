package net.bogor.itu.repository.radius;

import javax.persistence.TypedQuery;

import net.bogor.itu.entity.radius.Radacct;
import net.bogor.itu.persistence.PersistenceRepository;

import org.meruvian.yama.persistence.DefaultPersistence;
import org.meruvian.yama.persistence.EntityListWrapper;
import org.meruvian.yama.persistence.utils.PagingUtils;
import org.springframework.stereotype.Repository;

/**
 * @author Dian Aditya
 * 
 */
@Repository
public class RadacctRepository extends
		PersistenceRepository<DefaultPersistence> {
	public EntityListWrapper<Radacct> findByUsername(String username,
			int limit, int page) {
		EntityListWrapper<Radacct> list = new EntityListWrapper<Radacct>();
		list.setLimit(limit);
		list.setCurrentPage(page);

		String ql = "SELECT r FROM Radacct r WHERE r.username LIKE ?1 ORDER BY r.acctstoptime DESC";
		TypedQuery<Radacct> query = entityManager
				.createQuery(ql, Radacct.class);
		query.setParameter(1, "%" + username);

		if (limit > 0) {
			query.setMaxResults(limit);
		}
		query.setFirstResult(page);

		list.setEntityList(query.getResultList());

		ql = "SELECT COUNT(r) FROM Radacct r WHERE r.username LIKE ?1 ORDER BY r.acctstoptime DESC";
		TypedQuery<Long> lquery = entityManager.createQuery(ql, Long.class);
		lquery.setParameter(1, "%" + username);

		list.setRowCount(lquery.getSingleResult());
		list.setTotalPage(PagingUtils.getTotalPage(list.getRowCount(), limit));

		return list;
	}
	
	public Radacct findFirstSession(String username) {
		String ql = "SELECT r FROM Radacct r WHERE r.username LIKE ?1 ORDER BY r.acctstarttime ASC LIMIT 0, 1";
		TypedQuery<Radacct> query = entityManager
				.createQuery(ql, Radacct.class);
		query.setParameter(1, "%" + username);
		return query.getResultList().get(0);
	}
	
	public EntityListWrapper<Radacct> findOnlineUser(String username,
			int limit, int page) {
		EntityListWrapper<Radacct> list = new EntityListWrapper<Radacct>();
		list.setLimit(limit);
		list.setCurrentPage(page);

		String ql = "SELECT r FROM Radacct r WHERE r.username LIKE ?1 AND r.acctstoptime IS NULL ORDER BY r.acctstoptime DESC";
		TypedQuery<Radacct> query = entityManager
				.createQuery(ql, Radacct.class);
		query.setParameter(1, "%" + username);

		if (limit > 0) {
			query.setMaxResults(limit);
		}
		query.setFirstResult(page);

		list.setEntityList(query.getResultList());

		ql = "SELECT COUNT(r) FROM Radacct r WHERE r.username LIKE ?1 AND r.acctstoptime IS NULL ORDER BY r.acctstoptime DESC";
		TypedQuery<Long> lquery = entityManager.createQuery(ql, Long.class);
		lquery.setParameter(1, "%" + username);

		list.setRowCount(lquery.getSingleResult());
		list.setTotalPage(PagingUtils.getTotalPage(list.getRowCount(), limit));

		return list;
	}

	public Radacct findById(Long id) {
		return entityManager.find(Radacct.class, id);
	}
}
