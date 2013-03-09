package net.bogor.itu.service.admin;

import net.bogor.itu.entity.admin.ServicePackage;

import org.meruvian.yama.persistence.EntityListWrapper;

/**
 * @author Dian Aditya
 * 
 */
public interface ServicePackageService {
	ServicePackage findById(String id);

	ServicePackage save(ServicePackage service);

	EntityListWrapper<ServicePackage> findByKeyword(String keyword, int limit,
			int page);
}
