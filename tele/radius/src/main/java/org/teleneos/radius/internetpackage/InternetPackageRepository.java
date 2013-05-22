package org.teleneos.radius.internetpackage;

import org.meruvian.yama.persistence.EntityListWrapper;
import org.meruvian.yama.persistence.PersistenceRepository;
import org.springframework.stereotype.Repository;

@Repository
public class InternetPackageRepository extends
		PersistenceRepository<InternetPackage> {
	public EntityListWrapper<InternetPackage> find(String name, int limit,
			int page) {
		return findAll(limit, page, "u", "u.name LIKE ?1", name + "%");
	}
}
