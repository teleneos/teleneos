/**
 * 
 */
package net.bogor.itu.entity.radius;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import net.bogor.itu.entity.admin.User;
import net.bogor.itu.entity.master.InternetPackage;

import org.meruvian.yama.persistence.DefaultPersistence;

/**
 * @author Dian Aditya
 * 
 */
@Entity
@Table(name = "tc_user_package")
public class UserPackage extends DefaultPersistence {
	public enum Status {
		NOT_ACTIVATED_YET, ACTIVE, END
	}

	private User user;
	private InternetPackage internetPackage;
	private Date endDate;
	private Status status = Status.NOT_ACTIVATED_YET;
	private long quotaBalance = 0;
	private boolean unlimited = false;
	private List<ConnectionHistory> histories = new ArrayList<ConnectionHistory>();

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

	@Column(name = "end_date")
	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	@Enumerated(EnumType.ORDINAL)
	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	@OneToMany(mappedBy = "userPackage")
	public List<ConnectionHistory> getHistories() {
		return histories;
	}

	public void setHistories(List<ConnectionHistory> histories) {
		this.histories = histories;
	}

	@Column(name = "quota_balance")
	public long getQuotaBalance() {
		return quotaBalance;
	}

	public void setQuotaBalance(long quotaBalance) {
		this.quotaBalance = quotaBalance;
	}

	public boolean isUnlimited() {
		return unlimited;
	}

	public void setUnlimited(boolean unlimited) {
		this.unlimited = unlimited;
	}
}