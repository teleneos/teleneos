package net.bogor.itu.entity.admin;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.meruvian.yama.persistence.DefaultPersistence;

/**
 * @author Dian Aditya
 * 
 */
@Entity
@Table(name = "tc_group")
public class Group extends DefaultPersistence {
	private String name;
	private String description;
	private List<UserGroup> userGroups = new ArrayList<UserGroup>();
	private List<ServicePackageGroup> servicePackageGroups = new ArrayList<ServicePackageGroup>();

	@Column(nullable = false)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@JsonIgnore
	@OneToMany(mappedBy = "group", fetch = FetchType.LAZY)
	public List<UserGroup> getUserGroups() {
		return userGroups;
	}

	public void setUserGroups(List<UserGroup> userGroups) {
		this.userGroups = userGroups;
	}

	@JsonIgnore
	@OneToMany(mappedBy = "group", fetch = FetchType.LAZY)
	public List<ServicePackageGroup> getServicePackageGroups() {
		return servicePackageGroups;
	}

	public void setServicePackageGroups(
			List<ServicePackageGroup> servicePackageGroups) {
		this.servicePackageGroups = servicePackageGroups;
	}
}