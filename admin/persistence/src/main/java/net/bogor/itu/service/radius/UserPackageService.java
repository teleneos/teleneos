package net.bogor.itu.service.radius;

import net.bogor.itu.entity.radius.UserPackage;

/**
 * @author Dian Aditya
 * 
 */
public interface UserPackageService {
	UserPackage findActivePackage(String username);

	UserPackage save(UserPackage userPackage);
}
