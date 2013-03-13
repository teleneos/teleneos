/**
 * 
 */
package net.bogor.itu.action.admin;

import org.meruvian.yama.actions.DefaultActionModel;
import org.meruvian.yama.persistence.EntityListWrapper;

import net.bogor.itu.entity.admin.User;

/**
 * @author Dian Aditya
 * 
 */
public class UserActionModel extends DefaultActionModel {
	private User user = new User();
	private EntityListWrapper<User> users = new EntityListWrapper<User>();

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
}