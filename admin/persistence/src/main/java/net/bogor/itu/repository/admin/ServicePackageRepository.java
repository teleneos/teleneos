package net.bogor.itu.repository.admin;

import net.bogor.itu.entity.admin.ServicePackage;
import net.bogor.itu.persistence.PersistenceRepository;

import org.meruvian.yama.persistence.EntityListWrapper;
import org.springframework.stereotype.Repository;

/**
 * @author Dian Aditya
 * 
 */
@Repository
public class ServicePackageRepository extends
		PersistenceRepository<ServicePackage> {
	public EntityListWrapper<ServicePackage> findByKeyword(String keyword,
			int limit, int page) {
		return findByKeyword(limit, page, keyword, "name");
	}
}
