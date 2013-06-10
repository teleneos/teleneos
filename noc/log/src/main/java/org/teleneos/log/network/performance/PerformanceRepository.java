/**
 * 
 */
package org.teleneos.log.network.performance;

import java.util.List;

import javax.persistence.TypedQuery;

import org.meruvian.yama.persistence.PersistenceRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Dian Aditya
 * 
 */
@Repository
public class PerformanceRepository extends PersistenceRepository<Performance> {
	public List<Performance> findLatestPerformance(String telecentre, int limit) {
		TypedQuery<Performance> query = createQuery(entityClass, "p", "p",
				"p.telecentre = ?1 ORDER BY p.logInformation.createDate DESC",
				telecentre);

		if (limit > 0)
			query.setMaxResults(limit);

		return query.getResultList();
	}
}
