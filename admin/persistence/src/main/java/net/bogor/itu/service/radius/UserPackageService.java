package net.bogor.itu.service.radius;

import org.meruvian.yama.persistence.EntityListWrapper;

import net.bogor.itu.entity.radius.UserPackage;

/**
 * @author Dian Aditya
 * 
 */
public interface UserPackageService {
	UserPackage findActivePackage(String username);

	UserPackage save(UserPackage userPackage);

	EntityListWrapper<UserPackage> findPackageByUser(String username,
			int limit, int page);

	EntityListWrapper<UserPackage> findUserByPackageCode(String code,
			int limit, int page);
}
