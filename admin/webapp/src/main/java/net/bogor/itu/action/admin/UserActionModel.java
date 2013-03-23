/**
 * 
 */
package net.bogor.itu.action.admin;

import java.util.ArrayList;
import java.util.List;

import org.meruvian.yama.actions.DefaultActionModel;
import org.meruvian.yama.persistence.EntityListWrapper;

import net.bogor.itu.entity.admin.User;
import net.bogor.itu.entity.master.GroupA;
import net.bogor.itu.entity.master.GroupPackage;
import net.bogor.itu.entity.master.InternetPackage;

/**
 * @author Dian Aditya
 * 
 */
public class UserActionModel extends DefaultActionModel {
	private User user = new User();
	private EntityListWrapper<User> users = new EntityListWrapper<User>();
	private EntityListWrapper<GroupA> groups = new EntityListWrapper<GroupA>();
	private GroupA group = new GroupA();
	private List<GroupPackage> groupPackages = new ArrayList<GroupPackage>();
	
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

	public EntityListWrapper<GroupA> getGroups() {
		return groups;
	}

	public void setGroups(EntityListWrapper<GroupA> groups) {
		this.groups = groups;
	}

	public GroupA getGroup() {
		return group;
	}

	public void setGroup(GroupA group) {
		this.group = group;
	}

	public List<GroupPackage> getGroupPackages() {
		return groupPackages;
	}

	public void setGroupPackages(List<GroupPackage> groupPackages) {
		this.groupPackages = groupPackages;
	}

}