package net.bogor.itu.repository.pos;

import net.bogor.itu.entity.pos.Requisition;
import net.bogor.itu.entity.pos.Requisition;
import net.bogor.itu.persistence.PersistenceRepository;

import org.apache.commons.lang.StringUtils;
import org.meruvian.yama.persistence.EntityListWrapper;
import org.meruvian.yama.persistence.LogInformation.StatusFlag;
import org.springframework.stereotype.Repository;

/**
 * @author Edy Setiawan
 * 
 */
@Repository
public class RequisitionRepository extends PersistenceRepository<Requisition> {
	
	public EntityListWrapper<Requisition> findByKeyword(String keyword,
			String order, String orderBy, int limit, int page, String condition) {

		String criteria = "(th.title LIKE ? AND th.description LIKE ?)";
		criteria = criteria.replace("AND", condition);
		criteria += " AND th.logInformation.statusFlag = ? ORDER BY "
				+ StringUtils.defaultIfEmpty(order, "th.id") + " " + orderBy;
		Object[] params = { keyword, keyword,
				StatusFlag.ACTIVE };
		for (int i = 0; i < params.length - 1; i++) {
			if (params[i] instanceof String || params[i] == null) {
				params[i] = StringUtils.defaultIfEmpty((String) params[i], "");
				params[i] = StringUtils.join(new String[] { "%",
						(String) params[i], "%" });
			}
		}

		return findAll(limit, page, "th", criteria, params);
	}

}
