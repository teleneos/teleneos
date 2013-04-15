package net.bogor.itu.entity.radius;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import net.bogor.itu.entity.admin.User;

import org.meruvian.yama.persistence.DefaultPersistence;

@Entity
@Table(name = "tc_connection_history")
public class ConnectionHistory extends DefaultPersistence {
	private static final long serialVersionUID = -2124655892933568537L;

	private User user;
	private String radacct;
	private UserPackage userPackage;
	private long quotaBalance;

	@OneToOne
	@JoinColumn(name = "user_id")
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getRadacct() {
		return radacct;
	}

	public void setRadacct(String radacct) {
		this.radacct = radacct;
	}

	@ManyToOne
	@JoinColumn(name = "user_package_id")
	public UserPackage getUserPackage() {
		return userPackage;
	}

	public void setUserPackage(UserPackage userPackage) {
		this.userPackage = userPackage;
	}

	@Column(name = "quota_balance")
	public long getQuotaBalance() {
		return quotaBalance;
	}

	public void setQuotaBalance(long quotaBalance) {
		this.quotaBalance = quotaBalance;
	}
}