package net.bogor.itu.entity.pos;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.meruvian.yama.persistence.DefaultPersistence;
/**
 * @author Edy Setiawan
 * 
*/
@Entity
@Table(name = "tc_invoice")
public class Invoice extends DefaultPersistence {

	private String title;
	private String description;
	private Date dueDate;
	private Long cash;
	private PurchaseOrder purchaseOrder;
	private int status;
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Date getDueDate() {
		return dueDate;
	}
	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}
	public Long getCash() {
		return cash;
	}
	public void setCash(Long cash) {
		this.cash = cash;
	}
	
	@ManyToOne
	@JoinColumn(name="purchaseorder_id")
	public PurchaseOrder getPurchaseOrder() {
		return purchaseOrder;
	}
	public void setPurchaseOrder(PurchaseOrder purchaseOrder) {
		this.purchaseOrder = purchaseOrder;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}

}
