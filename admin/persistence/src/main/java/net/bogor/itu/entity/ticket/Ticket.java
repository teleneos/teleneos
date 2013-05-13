package net.bogor.itu.entity.ticket;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

import org.meruvian.yama.persistence.DefaultPersistence;

@Entity
@Table(name="tc_ticket")
public class Ticket extends DefaultPersistence {

	private static final long serialVersionUID = -9021069156271276845L;

	public enum Priority {
		LOW, NORMAL, HIGH
	}
	
	private long code;
	private String subject;
	private String message;
	private Priority priority;

	@Column(name = "code", unique = true, nullable = false, columnDefinition = "serial")
	public long getCode() {
		return code;
	}

	public void setCode(long code) {
		this.code = code;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Enumerated(EnumType.ORDINAL)
	public Priority getPriority() {
		return priority;
	}

	public void setPriority(Priority priority) {
		this.priority = priority;
	}

}
