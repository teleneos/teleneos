package net.bogor.itu.entity.admin;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import net.bogor.itu.entity.master.InternetPackage;

import org.meruvian.yama.persistence.DefaultPersistence;

//@Entity
//@Table(name = "tc_user_package", uniqueConstraints = @UniqueConstraint(columnNames = {
//		"user_id", "internet_package_id" }))
public class UserPackage extends DefaultPersistence {

	private static final long serialVersionUID = 7803500449298773427L;

	private User user;
	private InternetPackage internetPackage;

	@ManyToOne
	@JoinColumn(name = "user_id")
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
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
