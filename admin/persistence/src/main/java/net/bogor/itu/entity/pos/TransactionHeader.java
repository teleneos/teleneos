package net.bogor.itu.entity.pos;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;
import org.hibernate.envers.RelationTargetAuditMode;
import org.meruvian.yama.persistence.DefaultPersistence;

/**
 * @author Edy Setiawan
 * 
 */
@Entity
@Audited
@Table(name = "tc_transaction_header")
public class TransactionHeader extends DefaultPersistence {

	private static final long serialVersionUID = -9121939327021435066L;
	
	private long counter;
	private Long cash;
	private String username;
	private List<TransactionDetail> details = new ArrayList<TransactionDetail>();
	private boolean complete = false;
	
	@NotAudited
	@Column(name = "counter", unique = true, nullable = false, columnDefinition = "serial")
	public long getCounter() {
		return counter;
	}

	public void setCounter(long counter) {
		this.counter = counter;
	}

	public Long getCash() {
		return cash;
	}

	public void setCash(Long cash) {
		this.cash = cash;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@OneToMany(mappedBy = "transactionHeader")
	@Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
	public List<TransactionDetail> getDetails() {
		return details;
	}

	public void setDetails(List<TransactionDetail> details) {
		this.details = details;
	}

	public boolean isComplete() {
		return complete;
	}

	public void setComplete(boolean complete) {
		this.complete = complete;
	}
}