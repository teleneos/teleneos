/**
 * 
 */
package net.bogor.itu.action.admin;

import net.bogor.itu.entity.radius.UserPackage;

import org.meruvian.yama.actions.DefaultActionModel;
import org.meruvian.yama.persistence.EntityListWrapper;

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
