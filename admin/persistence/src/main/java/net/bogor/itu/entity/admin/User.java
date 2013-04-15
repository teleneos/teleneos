package net.bogor.itu.entity.admin;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import net.bogor.itu.entity.Address;
import net.bogor.itu.entity.Name;

import org.meruvian.yama.persistence.DefaultPersistence;
import org.meruvian.yama.security.user.BackendUser;

/**
 * @author Dian Aditya
 * 
 */
@Entity
@Table(name = "tc_user")
public class User extends DefaultPersistence {

	private static final long serialVersionUID = -4402068984793789574L;

	private BackendUser user;
	private Name name = new Name();
	private Address address = new Address();
	private Date birthDate;
	private boolean male = true;
	private String phone;
	// private List<UserGroup> userGroups = new ArrayList<UserGroup>();
	// private GroupA group;
	// private InternetPackage internetPackage;
	// private Set<UserPackage> userPackages = new HashSet<UserPackage>(0);
	private String occupation;
	private String idcard;

	public User() {
	}

	public User(String id) {
		setId(id);
	}

	@OneToOne
	@JoinColumn(name = "backend_user_id")
	public BackendUser getUser() {
		return user;
	}

	public void setUser(BackendUser user) {
		this.user = user;
	}

	@Embedded
	public Name getName() {
		return name;
	}

	public void setName(Name name) {
		this.name = name;
	}

	@Embedded
	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	@Column(name = "birth_date")
	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public boolean isMale() {
		return male;
	}

	public void setMale(boolean male) {
		this.male = male;
	}

	@Column(length = 16)
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	// @JsonIgnore
	// @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
	// public List<UserGroup> getUserGroups() {
	// return userGroups;
	// }
	//
	// public void setUserGroups(List<UserGroup> userGroups) {
	// this.userGroups = userGroups;
	// }
	//
	// @ManyToOne
	// @JoinColumn(name = "group_id")
	// public GroupA getGroup() {
	// return group;
	// }
	//
	// public void setGroup(GroupA group) {
	// this.group = group;
	// }

	// @ManyToOne
	// @JoinColumn(name = "package_id")
	// public InternetPackage getInternetPackage() {
	// return internetPackage;
	// }
	//
	// public void setInternetPackage(InternetPackage internetPackage) {
	// this.internetPackage = internetPackage;
	// }

	// @JsonIgnore
	// @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
	// @OrderColumn(name = "usr_idx")
	// public Set<UserPackage> getUserPackages() {
	// return userPackages;
	// }
	//
	// public void setUserPackages(Set<UserPackage> userPackages) {
	// this.userPackages = userPackages;
	// }

	public String getOccupation() {
		return occupation;
	}

	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}

	public String getIdcard() {
		return idcard;
	}

	public void setIdcard(String idcard) {
		this.idcard = idcard;
	}

}
