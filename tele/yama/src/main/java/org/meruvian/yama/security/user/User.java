package org.meruvian.yama.security.user;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.meruvian.yama.persistence.DefaultPersistence;

/**
 * @author Dian Aditya
 * 
 */
@Entity
@Table(name = "tc_user")
public class User extends DefaultPersistence {
	private BackendUser user = new BackendUser();
	private Name name = new Name();
	private Address address = new Address();
	private Date birthDate;
	private boolean male = true;
	private String phone;
	private String occupation;
	private String idcard;

	public User() {
	}

	public User(String id) {
		setId(id);
	}

	@Embedded
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