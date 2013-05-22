package org.teleneos.radius.history;

import org.meruvian.yama.persistence.PersistenceRepository;
import org.springframework.stereotype.Repository;

@Repository
public class ConnectionHistoryRepository extends
		PersistenceRepository<ConnectionHistory> {
	public ConnectionHistory findByAcct(String acctUniqueId) {
		try {
			return createQuery(entityClass, "h", "h", "h.radacct = ?1",
					acctUniqueId).getSingleResult();
		} catch (Exception e) {
			return null;
		}
	}
}
