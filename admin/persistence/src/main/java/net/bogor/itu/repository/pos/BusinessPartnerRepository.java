package net.bogor.itu.repository.pos;

import net.bogor.itu.entity.pos.BusinessPartner;
import net.bogor.itu.entity.pos.ItemCategory;
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
public class BusinessPartnerRepository extends
		PersistenceRepository<BusinessPartner> {

	public EntityListWrapper<BusinessPartner> findByKeyword(String keyword,
			String order, String orderBy, int limit, int page, String condition) {

		String criteria = "(i.category LIKE ? AND i.name LIKE ? AND i.officePhone LIKE ? "
				+ "i.fax LIKE ? AND i.email LIKE ? AND "
				+ "i.address LIKE ? AND i.city LIKE ? AND "
				+ "i.zipCode LIKE ? AND i.country LIKE ? AND i.description LIKE ?)";
		criteria = criteria.replace("AND", condition);
		criteria += " AND i.logInformation.statusFlag = ? ORDER BY "
				+ StringUtils.defaultIfEmpty(order, "i.id") + " " + orderBy;
		Object[] params = { keyword, keyword,keyword, keyword,keyword, keyword,keyword, keyword,keyword, keyword, StatusFlag.ACTIVE };
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
