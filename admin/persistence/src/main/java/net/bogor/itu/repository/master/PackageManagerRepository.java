package net.bogor.itu.repository.master;

import java.util.Arrays;

import net.bogor.itu.entity.master.InternetPackage;
import net.bogor.itu.entity.master.PaymentMethod;
import net.bogor.itu.persistence.PersistenceRepository;

import org.apache.commons.lang.StringUtils;
import org.meruvian.yama.persistence.EntityListWrapper;
import org.meruvian.yama.persistence.LogInformation.StatusFlag;
import org.springframework.stereotype.Repository;

@Repository
public class PackageManagerRepository extends
		PersistenceRepository<InternetPackage> {
	public EntityListWrapper<InternetPackage> findByKeyword(String keyword,
			String order, String orderBy, int limit, int page, String condition) {

		String criteria = "( i.name LIKE ? )";
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

	public EntityListWrapper<InternetPackage> findByPaymentMethod(
			boolean freeCharge, PaymentMethod method, String groupId,
			int limit, int page) {
		String criteria = "";
		if (freeCharge) {
			criteria = "i.price <= 0";
		} else {
			switch (method) {
			case PREPAID:
				criteria = "(i.type = 0 OR i.type = 1)";
				break;
			case POSTPAID:
				criteria = "(i.type= 2)";
				break;
			default:
				break;
			}
		}

		criteria += " AND i NOT IN (SELECT g.internetPackage FROM GroupPackage g WHERE g.group.id = ?1)";

		return findAll(limit, page, "i", criteria, groupId);
	}
}
