package net.bogor.itu.entity.master;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.meruvian.yama.persistence.DefaultPersistence;

@Entity
@Table(name = "tc_group_package", uniqueConstraints = @UniqueConstraint(columnNames = {
		"group_id", "internet_package_id" }))
public class GroupPackage extends DefaultPersistence {

	private static final long serialVersionUID = -9060496265848725113L;

	private GroupA group;
	private InternetPackage internetPackage;

	@ManyToOne
	@JoinColumn(name = "group_id")
	public GroupA getGroup() {
		return group;
	}

	public void setGroup(GroupA group) {
		this.group = group;
	}

	@ManyToOne
	@JoinColumn(name = "internet_package_id")
	public InternetPackage getInternetPackage() {
		return internetPackage;
	}

	public void setInternetPackage(InternetPackage internetPackage) {
		this.internetPackage = internetPackage;
	}

}
