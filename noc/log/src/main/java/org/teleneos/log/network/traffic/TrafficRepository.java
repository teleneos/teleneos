/**
 * 
 */
package org.teleneos.log.network.traffic;

import java.util.List;

import javax.persistence.TypedQuery;

import org.meruvian.yama.persistence.PersistenceRepository;
import org.springframework.stereotype.Repository;
import org.teleneos.log.network.traffic.Traffic.TrafficType;

/**
 * @author Dian Aditya
 * 
 */
@Repository
public class TrafficRepository extends PersistenceRepository<Traffic> {
	public List<Traffic> findLatestTraffic(String telecentre,
			TrafficType trafficType, int limit) {
		TypedQuery<Traffic> query = createQuery(entityClass, "t", "t",
				"t.telecentre = ?1 AND t.trafficType = ?2"
						+ " ORDER BY t.logInformation.createDate DESC",
				telecentre, trafficType);
		if (limit > 0)
			query.setMaxResults(limit);

		return query.getResultList();
	}

}
