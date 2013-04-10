package net.bogor.itu.entity.master;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.meruvian.yama.persistence.DefaultPersistence;

@Entity
@Table(name = "tc_internet_package", uniqueConstraints = { 
		@UniqueConstraint(columnNames = "code") 
		})
public class InternetPackage extends DefaultPersistence {

	private static final long serialVersionUID = 4319476619149651343L;

	public enum Type {
		COUNTDOWN, FIXTIME, NON_COUNTDOWN
	}

	public enum Status {
		ENABLE, DISABLE
	}
	
	private PaymentMethod paymentMethod = PaymentMethod.POSTPAID;
	private long paymentPeriod = 0;
	private String code;
	private String name;
	private Type type = Type.COUNTDOWN;
	private long variable = 0;
	private long price;
	private Status status = Status.ENABLE;

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

	public long getVariable() {
		return variable;
	}

	public void setVariable(long variable) {
		this.variable = variable;
	}

	public long getPrice() {
		return price;
	}

	public void setPrice(long price) {
		this.price = price;
	}

	@Enumerated(EnumType.ORDINAL)
	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}
	@Enumerated(EnumType.ORDINAL)
	@Column(name = "payment_method")
	public PaymentMethod getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(PaymentMethod paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

}
