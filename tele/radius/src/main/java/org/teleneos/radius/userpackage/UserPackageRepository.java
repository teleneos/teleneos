/**
 * 
 */
package org.teleneos.radius.userpackage;

import java.util.Date;
import java.util.List;

import javax.persistence.NoResultException;

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
		String ql = "SELECT d.postpaidEnd, d.internetPackage FROM TransactionDetail d WHERE d.transactionHeader.id = ?1 AND d.internetPackage IS NOT NULL";
		List<Object[]> packages = entityManager
				.createQuery(ql)
				.setParameter(1, transactionId).getResultList();
		String qc = "SELECT up FROM UserPackage up WHERE up.internetPackage.paymentMethod = ?1 AND up.username = ?2 AND up.endDate IS NOT NULL";
		UserPackage up = null;
		try {
			up = entityManager.createQuery(qc, UserPackage.class)
					.setParameter(1, PaymentMethod.POSTPAID)
					.setParameter(2, username)
					.setMaxResults(1)
					.getSingleResult();
		} catch (NoResultException e) {
			System.err.println(e.getMessage());
		}
		for (Object[] p : packages) {
			InternetPackage ip = (InternetPackage) p[1];
			Date date = (Date) p[0];
			if (up != null && ip.getPaymentMethod().equals(PaymentMethod.POSTPAID)) {
				date = new Date(date.getTime() + (ip.getTime() * 60000));
				if(date.after(up.getEndDate())){
					up.setEndDate(date);
				}
			} else {
				up = new UserPackage();
				up.setInternetPackage(ip);
				up.setUsername(username);
				if(ip.getPaymentMethod().equals(PaymentMethod.POSTPAID)){
					date = new Date(date.getTime() + (ip.getTime() * 60000));
					up.setEndDate(date);
				}
			}
			persist(up);
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
