package net.bogor.itu.entity.admin;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import net.bogor.itu.entity.Address;
import net.bogor.itu.entity.Name;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.meruvian.yama.persistence.DefaultPersistence;
import org.meruvian.yama.security.user.BackendUser;

/**
 * @author Dian Aditya
 * 
 */
@Entity
@Table(name = "tc_user")
public class User extends DefaultPersistence {
	private BackendUser user;
	private Name name = new Name();
	private Address address = new Address();
	private Date birthDate;
	private boolean male = true;
	private String phone;
	private List<UserGroup> userGroups = new ArrayList<UserGroup>();

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

	@JsonIgnore
	@OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
	public List<UserGroup> getUserGroups() {
		return userGroups;
	}

	public void setUserGroups(List<UserGroup> userGroups) {
		this.userGroups = userGroups;
	}

}
