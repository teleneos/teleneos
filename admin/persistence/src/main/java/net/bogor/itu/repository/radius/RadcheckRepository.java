/**
 * 
 */
package net.bogor.itu.repository.radius;

import javax.persistence.TypedQuery;

import net.bogor.itu.entity.radius.Radcheck;
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
public class RadcheckRepository extends
		PersistenceRepository<DefaultPersistence> {
	public EntityListWrapper<Radcheck> findByUsername(String username,
			int limit, int page) {
		EntityListWrapper<Radcheck> list = new EntityListWrapper<Radcheck>();
		list.setLimit(limit);
		list.setCurrentPage(page);

		String ql = "SELECT r FROM Radcheck r WHERE r.username LIKE ?1 ORDER BY r.acctstoptime DESC";
		TypedQuery<Radcheck> query = entityManager.createQuery(ql,
				Radcheck.class);
		query.setParameter(1, "%" + username);

		if (limit > 0) {
			query.setMaxResults(limit);
		}
		query.setFirstResult(page);

		list.setEntityList(query.getResultList());

		ql = "SELECT COUNT(r) FROM Radcheck r WHERE r.username LIKE ?1 ORDER BY r.acctstoptime DESC";
		TypedQuery<Long> lquery = entityManager.createQuery(ql, Long.class);
		lquery.setParameter(1, "%" + username);

		list.setRowCount(lquery.getSingleResult());
		list.setTotalPage(PagingUtils.getTotalPage(list.getRowCount(), limit));

		return list;
	}

	public Radcheck findById(Long id) {
		return entityManager.find(Radcheck.class, id);
	}

	public void persist(Radcheck radcheck) {
		entityManager.persist(radcheck);
	}

	public Radcheck merge(Radcheck radcheck) {
		return entityManager.merge(radcheck);
	}

	public void delete(Radcheck radcheck) {
		entityManager.remove(radcheck);
	}
}
