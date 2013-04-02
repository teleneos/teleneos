package net.bogor.itu.entity.master;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.OneToMany;
import javax.persistence.OrderColumn;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.meruvian.yama.persistence.DefaultPersistence;

@Entity
@Table(name = "tc_group_2", uniqueConstraints = { 
		@UniqueConstraint(columnNames = "code") 
		})
public class GroupA extends DefaultPersistence {

	private static final long serialVersionUID = 345578434452418898L;

	private String code;
	private String name;
	private boolean club;
	private boolean freeOfCharge = true;
	private PaymentMethod paymentMethod = PaymentMethod.POSTPAID;
	private long paymentPeriod = 0;
	private Set<GroupPackage> groupPackages = new HashSet<GroupPackage>(0);
	
	@Column(name = "code", unique = true)
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

	public boolean isClub() {
		return club;
	}

	public void setClub(boolean club) {
		this.club = club;
	}

	@Column(name = "free_of_charge")
	public boolean isFreeOfCharge() {
		return freeOfCharge;
	}

	public void setFreeOfCharge(boolean freeOfCharge) {
		this.freeOfCharge = freeOfCharge;
	}

	@Enumerated(EnumType.ORDINAL)
	@Column(name = "payment_method")
	public PaymentMethod getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(PaymentMethod paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	/**
	 * 
	 * @return Payment period in minutes
	 */
	@Column(name = "payment_period")
	public long getPaymentPeriod() {
		return paymentPeriod;
	}

	public void setPaymentPeriod(long paymentPeriod) {
		this.paymentPeriod = paymentPeriod;
	}

	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "group")
	@OrderColumn(name = "grp_idx")
	public Set<GroupPackage> getGroupPackages() {
		return groupPackages;
	}

	public void setGroupPackages(Set<GroupPackage> groupPackages) {
		this.groupPackages = groupPackages;
	}

}
