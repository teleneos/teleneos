package org.teleneos.pos.invoice;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.envers.Audited;
import org.hibernate.envers.RelationTargetAuditMode;
import org.meruvian.yama.persistence.DefaultPersistence;
import org.teleneos.pos.item.Item;

/**
 * @author Edy Setiawan
 * 
 */
@Entity
@Audited
@Table(name = "tc_invoice_detail")
public class InvoiceDetail extends DefaultPersistence {

	private Invoice invoice;
	private Item item;
	private int quantity;
	private Long price;

	@ManyToOne
	@JoinColumn(name = "invoice_id")
	@Audited(targetAuditMode=RelationTargetAuditMode.NOT_AUDITED)
	public Invoice getInvoice() {
		return invoice;
	}

	public void setInvoice(Invoice invoice) {
		this.invoice = invoice;
	}

	@ManyToOne
	@JoinColumn(name = "item_id")
	@Audited(targetAuditMode=RelationTargetAuditMode.NOT_AUDITED)
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

}
