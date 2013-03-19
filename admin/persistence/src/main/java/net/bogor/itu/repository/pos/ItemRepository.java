package net.bogor.itu.repository.pos;

import net.bogor.itu.entity.pos.Item;
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
public class ItemRepository extends PersistenceRepository<Item> {
	public EntityListWrapper<Item> findByKeyword(String keyword, String order,
			String orderBy, int limit, int page, String condition) {

		String criteria = "(i.code LIKE ? AND i.name LIKE ? AND i.description LIKE ? AND i.category.name LIKE ?)";
		criteria = criteria.replace("AND", condition);
		criteria += " AND i.logInformation.statusFlag = ? ORDER BY "
				+ StringUtils.defaultIfEmpty(order, "i.id") + " " + orderBy;
		Object[] params = { keyword, keyword, keyword, keyword,
				StatusFlag.ACTIVE };
		for (int i = 0; i < params.length - 1; i++) {
			if (params[i] instanceof String || params[i] == null) {
				params[i] = StringUtils.defaultIfEmpty((String) params[i], "");
				params[i] = StringUtils.join(new String[] { "%",
						(String) params[i], "%" });
			}
		}

		return findAll(limit, page, "i", criteria, params);
	}

}
