package org.teleneos.pos.po;

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
@Table(name = "tc_purchase_order_detail")
public class PurchaseOrderDetail extends DefaultPersistence {

	private PurchaseOrder purchaseOrder;
	private Item item;
	private int quantity;
	private Long price;

	@ManyToOne
	@JoinColumn(name = "purchaseorder_id")
	@Audited(targetAuditMode=RelationTargetAuditMode.NOT_AUDITED)
	public PurchaseOrder getPurchaseOrder() {
		return purchaseOrder;
	}

	public void setPurchaseOrder(PurchaseOrder purchaseOrder) {
		this.purchaseOrder = purchaseOrder;
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
