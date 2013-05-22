package org.teleneos.radius.userpackage;

import org.meruvian.yama.persistence.EntityListWrapper;

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
