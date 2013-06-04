/**
 * 
 */
package org.teleneos.log.common;

import java.util.List;

import javax.persistence.TypedQuery;

import org.meruvian.yama.persistence.PersistenceRepository;
import org.springframework.stereotype.Repository;
import org.teleneos.log.network.History;

/**
 * @author Dian Aditya
 * 
 */
@Repository
public class HistoryRepository<T extends History> extends
		PersistenceRepository<T> {
	public List<T> findByTelecentre(String telecentre, int limit) {
		TypedQuery<T> query = createQuery(entityClass, "h", "h",
				"telecentre = ?1 ORDER BY h.logInformation.createDate DESC",
				telecentre);
		query.setMaxResults(limit);

		return query.getResultList();
	}
}
