package net.bogor.itu.entity.radius;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.meruvian.yama.persistence.DefaultPersistence;

@Entity
@Table(name = "tc_connection_history")
public class ConnectionHistory extends DefaultPersistence {
	private static final long serialVersionUID = -2124655892933568537L;

	private String username;
	private String radacct;
	private UserPackage userPackage;
	private long quotaBalance;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
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