package net.bogor.itu.action.admin;

import net.bogor.itu.entity.admin.Group;

import org.meruvian.yama.actions.DefaultActionModel;
import org.meruvian.yama.persistence.EntityListWrapper;

/**
 * @author Dian Aditya
 * 
 */
public class GroupActionModel extends DefaultActionModel {
	private Group group = new Group();
	private EntityListWrapper<Group> groups = new EntityListWrapper<Group>();

	public Group getGroup() {
		return group;
	}

	public void setGroup(Group group) {
		this.group = group;
	}

	public EntityListWrapper<Group> getGroups() {
		return groups;
	}

	public void setGroups(EntityListWrapper<Group> groups) {
		this.groups = groups;
	}
}