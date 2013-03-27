package net.bogor.itu.entity.pos;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import net.bogor.itu.entity.master.InternetPackage;

import org.meruvian.yama.persistence.DefaultPersistence;

/**
 * @author Edy Setiawan
 * 
 */
@Entity
@Table(name = "tc_transaction_detail")
public class TransactionDetail extends DefaultPersistence {

	private Item item;
	private int quantity;
	private Long price;
	private TransactionHeader transactionHeader;
	private InternetPackage internetPackage;

	@ManyToOne
	@JoinColumn(name = "item_id")
	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public Long getPrice() {
		return price;
	}

	public void setPrice(Long price) {
		this.price = price;
	}

	@ManyToOne
	@JoinColumn(name = "transcation_header_id")
	public TransactionHeader getTransactionHeader() {
		return transactionHeader;
	}

	public void setTransactionHeader(TransactionHeader transactionHeader) {
		this.transactionHeader = transactionHeader;
	}

	@ManyToOne
	@JoinColumn(name = "internetpackage_id")
	public InternetPackage getInternetPackage() {
		return internetPackage;
	}

	public void setInternetPackage(InternetPackage internetPackage) {
		this.internetPackage = internetPackage;
	}

}
