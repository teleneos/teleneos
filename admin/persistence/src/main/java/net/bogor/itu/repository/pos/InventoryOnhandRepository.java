package net.bogor.itu.repository.pos;

import javax.persistence.Query;

import net.bogor.itu.entity.pos.InventoryOnhand;
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
public class InventoryOnhandRepository extends PersistenceRepository<InventoryOnhand> {
	
	public InventoryOnhand findByItem(String id){
		String ql = "SELECT i FROM InventoryOnhand i WHERE i.item.id = ?";
		Query query = entityManager.createQuery(ql)
				.setParameter(1, id);
		try {
			return (InventoryOnhand) query.getSingleResult();			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public EntityListWrapper<InventoryOnhand> findByKeyword(String keyword,
			String order, String orderBy, int limit, int page, String condition) {

		String criteria = "(i.item.name LIKE ? )";
		criteria = criteria.replace("AND", condition);
		criteria += " AND i.logInformation.statusFlag = ? ORDER BY "
				+ StringUtils.defaultIfEmpty(order, "i.id") + " " + orderBy;
		Object[] params = { keyword, StatusFlag.ACTIVE };
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
