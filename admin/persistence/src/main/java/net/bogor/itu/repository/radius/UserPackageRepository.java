/**
 * 
 */
package net.bogor.itu.repository.radius;

import java.util.List;

import javax.persistence.NoResultException;

import net.bogor.itu.entity.admin.User;
import net.bogor.itu.entity.master.InternetPackage;
import net.bogor.itu.entity.radius.UserPackage;
import net.bogor.itu.entity.radius.UserPackage.Status;
import net.bogor.itu.persistence.PersistenceRepository;

import org.springframework.stereotype.Repository;

/**
 * @author Dian Aditya
 * 
 */
@Repository
public class UserPackageRepository extends PersistenceRepository<UserPackage> {
	public void save(String transactionId, User user) {
		String ql = "SELECT d.internetPackage FROM TransactionDetail d WHERE d.transactionHeader.id = ?1 AND d.internetPackage IS NOT NULL";
		List<InternetPackage> packages = entityManager
				.createQuery(ql, InternetPackage.class)
				.setParameter(1, transactionId).getResultList();

		for (InternetPackage p : packages) {
			UserPackage userPackage = new UserPackage();
			userPackage.setInternetPackage(p);
			userPackage.setUser(user);

			persist(userPackage);
		}
	}

	public UserPackage findActive(String userId) {
		UserPackage userPackage = null;

		try {
			String criteria = "p.user.id = ?1 AND (p.status = ?2 OR p.status = ?3) "
					+ "ORDER BY p.logInformation.updateDate ASC";
			userPackage = createQuery(entityClass, "p", "p", criteria, userId,
					Status.NOT_ACTIVATED_YET, Status.ACTIVE, Status.ACTIVE)
					.setMaxResults(1).getSingleResult();
		} catch (NoResultException e) {
		}

		return userPackage;
	}
}
