package org.teleneos.radius.internetpackage;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.meruvian.yama.persistence.DefaultPersistence;

@Entity
@Table(name = "tc_internet_package", uniqueConstraints = { @UniqueConstraint(columnNames = "code") })
public class InternetPackage extends DefaultPersistence {

	private static final long serialVersionUID = 4319476619149651343L;

	public enum Type {
		COUNTDOWN, FIXTIME, NON_COUNTDOWN
	}

	public enum Status {
		ENABLE, DISABLE
	}

	private String code;
	private String name;
	private PaymentMethod paymentMethod = PaymentMethod.POSTPAID;
	private Type type = Type.COUNTDOWN;
	private long price = 0;
	private long time = 0;
	private long quota = 0;
	private long speed = 0;
	private long nextSpeed = 0;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "code", unique = true)
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Enumerated(EnumType.ORDINAL)
	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	public long getPrice() {
		return price;
	}

	public void setPrice(long price) {
		this.price = price;
	}

	@Enumerated(EnumType.ORDINAL)
	@Column(name = "payment_method")
	public PaymentMethod getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(PaymentMethod paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	public long getTime() {
		return time;
	}

	public void setTime(long time) {
		this.time = time;
	}

	public long getQuota() {
		return quota;
	}

	public void setQuota(long quota) {
		this.quota = quota;
	}

	public long getSpeed() {
		return speed;
	}

	public void setSpeed(long speed) {
		this.speed = speed;
	}

	public long getNextSpeed() {
		return nextSpeed;
	}

	public void setNextSpeed(long nextSpeed) {
		this.nextSpeed = nextSpeed;
	}

}
