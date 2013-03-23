package net.bogor.itu.service.master;

import net.bogor.itu.entity.master.InternetPackage;

import org.meruvian.yama.persistence.EntityListWrapper;

public interface PackageManagerService {
	
	EntityListWrapper<InternetPackage> findByName(String keyword,String order,String orderBy, int limit,
			int page);

	InternetPackage save(InternetPackage internetPackage);

	InternetPackage findById(String id);
	
	EntityListWrapper<InternetPackage> all();
}
