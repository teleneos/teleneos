/**
 * 
 */
package org.teleneos.radius.statistic;

import org.meruvian.yama.actions.DefaultActionModel;
import org.meruvian.yama.persistence.EntityListWrapper;
import org.meruvian.yama.security.user.User;
import org.teleneos.radius.internetpackage.InternetPackage;

/**
 * @author Dian Aditya
 * 
 */
public class UserActionModel extends DefaultActionModel {
	private User user = new User();
	private EntityListWrapper<User> users = new EntityListWrapper<User>();
	private EntityListWrapper<Object[]> details = new EntityListWrapper<Object[]>();
	private String pass;
	private EntityListWrapper<InternetPackage> packages = new EntityListWrapper<InternetPackage>();

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public EntityListWrapper<User> getUsers() {
		return users;
	}

	public void setUsers(EntityListWrapper<User> users) {
		this.users = users;
	}

	public EntityListWrapper<Object[]> getDetails() {
		return details;
	}

	public void setDetails(EntityListWrapper<Object[]> details) {
		this.details = details;
	}

	public EntityListWrapper<InternetPackage> getPackages() {
		return packages;
	}

	public void setPackages(EntityListWrapper<InternetPackage> packages) {
		this.packages = packages;
	}

}