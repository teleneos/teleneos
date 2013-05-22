package org.teleneos.radius.internetpackage;

import org.meruvian.yama.persistence.EntityListWrapper;

public interface PackageManagerService {

	EntityListWrapper<InternetPackage> findByName(String keyword, String order,
			String orderBy, int limit, int page);

	InternetPackage save(InternetPackage internetPackage);

	InternetPackage findById(String id);

	EntityListWrapper<InternetPackage> all();

	EntityListWrapper<InternetPackage> findByPaymentMethod(boolean freeCharge,
			PaymentMethod method, String groupId, int limit, int page);
}
