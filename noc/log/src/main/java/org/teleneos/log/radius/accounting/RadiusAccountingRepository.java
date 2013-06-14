/**
 * 
 */
package org.teleneos.log.radius.accounting;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.meruvian.yama.persistence.EntityListWrapper;
import org.meruvian.yama.persistence.PersistenceRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Dian Aditya
 * 
 */
@Repository
public class RadiusAccountingRepository extends
		PersistenceRepository<RadiusAccounting> {

	public EntityListWrapper<RadiusAccounting> findAll(String telecentre,
			String user, long from, long to, String order, int limit, int page) {
		StringBuilder criteria = new StringBuilder();
		List<Object> params = new ArrayList<Object>();

		criteria.append("r.telecentre = ?1 AND r.logInformation.createDate >= ?2 AND r.logInformation.createDate <= ?3");

		params.add(telecentre);
		params.add(new Date(from));

		if (to <= 0) {
			params.add(new Date());
		} else {
			params.add(to);
		}

		if (StringUtils.isNotBlank(user)) {
			criteria.append(" AND r.username = ?4");
			params.add(user);
		}

		criteria.append(" ORDER BY r.logInformation.createDate");

		order = StringUtils.defaultIfBlank(order, "DESC");
		if (!order.equalsIgnoreCase("DESC")) {
			order = "ASC";
		}

		criteria.append(" ").append(order);

		EntityListWrapper<RadiusAccounting> accts = findAll(limit, page,
				entityClass, "r", criteria.toString(), params.toArray());

		return accts;
	}
}
