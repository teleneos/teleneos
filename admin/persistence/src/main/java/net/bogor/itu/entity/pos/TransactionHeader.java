package net.bogor.itu.entity.pos;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import net.bogor.itu.entity.admin.User;

import org.meruvian.yama.persistence.DefaultPersistence;

/**
 * @author Edy Setiawan
 * 
 */
@Entity
@Table(name = "tc_transaction_header")
public class TransactionHeader extends DefaultPersistence {

	private long counter;
	private User user;
	private Long cash;
	private List<TransactionDetail> details = new ArrayList<TransactionDetail>();

	@ManyToOne
	@JoinColumn(name = "user_id")
	public User getUser() {
		return user;
	}

	public long getCounter() {
		return counter;
	}

	public void setCounter(long counter) {
		this.counter = counter;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Long getCash() {
		return cash;
	}

	public void setCash(Long cash) {
		this.cash = cash;
	}

	@OneToMany(mappedBy = "transactionHeader")
	public List<TransactionDetail> getDetails() {
		return details;
	}

	public void setDetails(List<TransactionDetail> details) {
		this.details = details;
	}
}