package net.bogor.itu.entity.master;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OrderColumn;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonBackReference;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.meruvian.yama.persistence.DefaultPersistence;

@Entity
@Table(name = "tc_group_2")
public class GroupA extends DefaultPersistence {

	private static final long serialVersionUID = 345578434452418898L;

	private String code;
	private String name;
	private boolean pms;
	private boolean club;
	private Set<GroupPackage> groupPackages = new HashSet<GroupPackage>(0);
	
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isPms() {
		return pms;
	}

	public void setPms(boolean pms) {
		this.pms = pms;
	}

	public boolean isClub() {
		return club;
	}

	public void setClub(boolean club) {
		this.club = club;
	}
	
	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "group")
	@OrderColumn(name="grp_idx")
	public Set<GroupPackage> getGroupPackages() {
		return groupPackages;
	}

	public void setGroupPackages(Set<GroupPackage> groupPackages) {
		this.groupPackages = groupPackages;
	}

}
