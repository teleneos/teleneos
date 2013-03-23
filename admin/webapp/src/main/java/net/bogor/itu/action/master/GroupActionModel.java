package net.bogor.itu.action.master;

import java.util.ArrayList;
import java.util.List;

import net.bogor.itu.entity.master.GroupA;
import net.bogor.itu.entity.master.InternetPackage;

import org.meruvian.yama.actions.DefaultActionModel;
import org.meruvian.yama.persistence.EntityListWrapper;

public class GroupActionModel extends DefaultActionModel {
	
	private GroupA group = new GroupA();
	private EntityListWrapper<GroupA> groups = new EntityListWrapper<GroupA>();
	private List<String> packageIds = new ArrayList<String>();
	private EntityListWrapper<InternetPackage> packages = new EntityListWrapper<InternetPackage>();
	
	public GroupA getGroup() {
		return group;
	}

	public void setGroup(GroupA group) {
		this.group = group;
	}

	public EntityListWrapper<GroupA> getGroups() {
		return groups;
	}

	public void setGroups(EntityListWrapper<GroupA> groups) {
		this.groups = groups;
	}

	public List<String> getPackageIds() {
		return packageIds;
	}

	public void setPackageIds(List<String> packageIds) {
		this.packageIds = packageIds;
	}

	public EntityListWrapper<InternetPackage> getPackages() {
		return packages;
	}

	public void setPackages(EntityListWrapper<InternetPackage> packages) {
		this.packages = packages;
	}

}
