package net.bogor.itu.repository.pos;

import net.bogor.itu.entity.pos.StockAudit;
import net.bogor.itu.persistence.PersistenceRepository;

import org.apache.commons.lang.StringUtils;
import org.meruvian.yama.persistence.EntityListWrapper;
import org.meruvian.yama.persistence.LogInformation.StatusFlag;
import org.springframework.stereotype.Repository;

@Repository
public class StockAuditRepository extends PersistenceRepository<StockAudit> {
	public EntityListWrapper<StockAudit> findByKeyword(String keyword,
			String order, String orderBy, int limit, int page, String condition) {

		String criteria = "(i.description LIKE ? AND i.item.name LIKE ?)";
		criteria = criteria.replace("AND", condition);
		criteria += " AND i.logInformation.statusFlag = ? ORDER BY "
				+ StringUtils.defaultIfEmpty(order, "i.id") + " " + orderBy;
		Object[] params = { keyword, keyword, StatusFlag.ACTIVE };
		for (int i = 0; i < params.length - 1; i++) {
			if (params[i] instanceof String || params[i] == null) {
				params[i] = StringUtils.defaultIfEmpty((String) params[i], "");
				params[i] = StringUtils.join(new String[] { "%",
						(String) params[i], "%" });
			}
		}

		return findAll(limit, page, "i", criteria, params);
	}
	
	public EntityListWrapper<StockAudit> findByItem(String itemId,
			String order, String orderBy, int limit, int page, String condition) {

		String criteria = " i.item.id = ? AND i.logInformation.statusFlag = ? ORDER BY "
				+ StringUtils.defaultIfEmpty(order, "i.id") + " " + orderBy;
		Object[] params = { itemId, StatusFlag.ACTIVE };
		
		return findAll(limit, page, "i", criteria, params);
	}
}
