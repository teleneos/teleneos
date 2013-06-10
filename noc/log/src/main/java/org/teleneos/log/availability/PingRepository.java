/**
 * 
 */
package org.teleneos.log.availability;

import javax.persistence.TypedQuery;

import org.meruvian.yama.persistence.EntityListWrapper;
import org.meruvian.yama.persistence.PersistenceRepository;
import org.meruvian.yama.persistence.utils.PagingUtils;
import org.springframework.stereotype.Repository;

/**
 * @author Dian Aditya
 * 
 */
@Repository
public class PingRepository extends PersistenceRepository<Ping> {
	public EntityListWrapper<Object[]> findLatestStatus(String name, int limit,
			int page) {
		EntityListWrapper<Object[]> list = new EntityListWrapper<Object[]>();
		list.setLimit(limit);
		list.setCurrentPage(page);

		String ql = "SELECT p.telecentre, max(p.logInformation.createDate) as c FROM Ping p WHERE p.telecentre LIKE ?1 GROUP BY p.telecentre ORDER BY c";
		TypedQuery<Object[]> query = entityManager.createQuery(ql,
				Object[].class);
		query.setParameter(1, name + "%");

		if (limit > 0) {
			query.setMaxResults(limit);
		}
		query.setFirstResult(page * limit);

		list.setEntityList(query.getResultList());

		ql = "SELECT count(distinct p.telecentre) FROM Ping p WHERE p.telecentre LIKE ?1";
		TypedQuery<Long> lquery = entityManager.createQuery(ql, Long.class);
		lquery.setParameter(1, name + "%");

		list.setRowCount(lquery.getSingleResult());
		list.setTotalPage(PagingUtils.getTotalPage(list.getRowCount(), limit));

		return list;
	}
}
