/**
 * 
 */
package org.teleneos.log.network.host;

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
public class HostRepository extends PersistenceRepository<Host> {
	public EntityListWrapper<Host> findHosts(String telecentre, int limit,
			int page) {
		EntityListWrapper<Host> list = new EntityListWrapper<Host>();
		list.setLimit(limit);
		list.setCurrentPage(page);

		String ql = "SELECT host FROM Host host WHERE host.id IN"
				+ " (SELECT max(h.id) FROM Host h WHERE h.telecentre = ?1 GROUP BY h.hostid, h.telecentre)"
				+ " ORDER BY host.logInformation.createDate DESC";
		TypedQuery<Host> query = entityManager.createQuery(ql, Host.class);
		query.setParameter(1, telecentre);

		if (limit > 0) {
			query.setMaxResults(limit);
		}

		query.setFirstResult(page * limit);
		list.setEntityList(query.getResultList());

		ql = "SELECT count(distinct h.id) FROM Host h WHERE h.telecentre = ?1 GROUP BY h.telecentre ";
		TypedQuery<Long> lquery = entityManager.createQuery(ql, Long.class);
		lquery.setParameter(1, telecentre);

		list.setRowCount(lquery.getSingleResult());
		list.setTotalPage(PagingUtils.getTotalPage(list.getRowCount(), limit));

		return list;
	}
}
