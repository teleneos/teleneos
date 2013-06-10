/**
 * 
 */
package org.teleneos.log.common;

import java.util.List;

import javax.persistence.TypedQuery;

import org.meruvian.yama.persistence.PersistenceRepository;
import org.teleneos.log.network.Item;

/**
 * @author Dian Aditya
 * 
 */
public class ItemRepository<T extends Item> extends PersistenceRepository<T> {
	public List<T> findByTelecentre(String telecentre, int limit) {
		TypedQuery<T> query = createQuery(entityClass, "h", "h",
				"telecentre = ?1 ORDER BY h.logInformation.createDate DESC",
				telecentre);
		query.setMaxResults(limit);

		return query.getResultList();
	}
}
