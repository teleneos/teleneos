package org.teleneos.radius.internetpackage;

import org.meruvian.yama.persistence.EntityListWrapper;
import org.meruvian.yama.persistence.PersistenceRepository;
import org.springframework.stereotype.Repository;

@Repository
public class PackageManagerRepository extends
		PersistenceRepository<InternetPackage> {
	public EntityListWrapper<InternetPackage> findByKeyword(String keyword,
			int limit, int page) {

		return findAll(limit, page, "i", "i.name LIKE ?1 OR i.code LIKE ?1",
				"%" + keyword + "%");
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
