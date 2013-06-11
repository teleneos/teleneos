/**
 * 
 */
package org.teleneos.radius.userpackage;

import java.util.List;

import org.meruvian.yama.persistence.EntityListWrapper;
import org.meruvian.yama.persistence.LogInformation.StatusFlag;
import org.meruvian.yama.persistence.PersistenceRepository;
import org.springframework.stereotype.Repository;
import org.teleneos.radius.internetpackage.InternetPackage;
import org.teleneos.radius.internetpackage.PaymentMethod;
import org.teleneos.radius.userpackage.UserPackage.Status;

/**
 * @author Dian Aditya
 * 
 */
@Repository
public class UserPackageRepository extends PersistenceRepository<UserPackage> {
	public void save(String transactionId, String username) {
		String ql = "SELECT d.internetPackage FROM TransactionDetail d WHERE d.transactionHeader.id = ?1 AND d.internetPackage IS NOT NULL";
		List<InternetPackage> packages = entityManager
				.createQuery(ql, InternetPackage.class)
				.setParameter(1, transactionId).getResultList();

		for (InternetPackage p : packages) {
			UserPackage userPackage = new UserPackage();
			userPackage.setInternetPackage(p);
			userPackage.setUsername(username);

			persist(userPackage);
		}
	}

	public UserPackage findActive(String username) {
		String criteria = "p.username = ?1 AND (p.status = ?2 OR p.status = ?3) "
				+ "AND p.internetPackage.logInformation.statusFlag = ?4 "
				+ "ORDER BY p.logInformation.createDate ASC";
		List<UserPackage> userPackages = createQuery(entityClass, "p", "p",
				criteria, username, Status.NOT_ACTIVATED_YET, Status.ACTIVE,
				StatusFlag.ACTIVE).setMaxResults(1).getResultList();

		if (userPackages.size() > 0) {
			return userPackages.get(0);
		}

		return null;
	}

	public EntityListWrapper<UserPackage> findByUsername(String username,
			int limit, int page) {
		return findAll(limit, page, "p",
				"p.username LIKE ?1 ORDER BY p.logInformation.updateDate DESC",
				username + "%");
	}
	
	public EntityListWrapper<UserPackage> findPostpaidUser(String keyword, int limit, int page) {
		return findAll(limit, page, "p", "p.internetPackage.paymentMethod = ?1 AND (p.internetPackage.code LIKE ?2 OR p.internetPackage.name LIKE ?3 OR p.username LIKE ?4) AND p.status = ?5",
				PaymentMethod.POSTPAID, keyword+"%", keyword+"%", keyword+"%", Status.END);
	}
	
	public EntityListWrapper<UserPackage> findByPackageCode(String code,
			int limit, int page) {
		return findAll(limit, page, "p", "p.internetPackage.code = ?1", code);
	}
}
