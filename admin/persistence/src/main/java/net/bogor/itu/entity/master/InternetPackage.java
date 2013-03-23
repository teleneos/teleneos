package net.bogor.itu.entity.master;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

import org.meruvian.yama.persistence.DefaultPersistence;

@Entity
@Table(name = "tc_internet_package")
public class InternetPackage extends DefaultPersistence {

	private static final long serialVersionUID = 4319476619149651343L;
	
	public enum Type {
		COUNTDOWN, FIXTIME
	}

	public enum Status {
		ENABLE, DISABLE
	}
	
	private String code;
	private String name;
	private Type type;
	private int variable;
	private long price;
	private Status status;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
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

	public int getVariable() {
		return variable;
	}

	public void setVariable(int variable) {
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

}