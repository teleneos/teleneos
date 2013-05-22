/**
 * 
 */
package org.teleneos.radius.userpackage;

import org.meruvian.yama.persistence.EntityListWrapper;
import org.teleneos.radius.statistic.OnlineUserActionModel;

/**
 * @author Dian Aditya
 * 
 */
public class UserPackageActionModel extends OnlineUserActionModel {
	private UserPackage userPackage = new UserPackage();
	private EntityListWrapper<UserPackage> userPackages = new EntityListWrapper<UserPackage>();

	public UserPackage getUserPackage() {
		return userPackage;
	}

	public void setUserPackage(UserPackage userPackage) {
		this.userPackage = userPackage;
	}

	public EntityListWrapper<UserPackage> getUserPackages() {
		return userPackages;
	}

	public void setUserPackages(EntityListWrapper<UserPackage> userPackages) {
		this.userPackages = userPackages;
	}
}
