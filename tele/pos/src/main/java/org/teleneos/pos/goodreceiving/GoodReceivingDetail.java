package org.teleneos.pos.goodreceiving;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.envers.Audited;
import org.hibernate.envers.RelationTargetAuditMode;
import org.meruvian.yama.persistence.DefaultPersistence;
import org.teleneos.pos.item.Item;
import org.teleneos.pos.uom.UnitOfMeasure;

/**
 * @author Edy Setiawan
 * 
 */
@Entity
@Audited
@Table(name = "tc_good_receiving_detail")
public class GoodReceivingDetail extends DefaultPersistence {

	private static final long serialVersionUID = 3631371487891960869L;
	
	private GoodReceiving goodReceiving;
	private Item item;
	private int quantity;
	private String description;
	private UnitOfMeasure uom;

	@ManyToOne
	@JoinColumn(name = "good_receiving_id")
	@Audited(targetAuditMode=RelationTargetAuditMode.NOT_AUDITED)
	public GoodReceiving getGoodReceiving() {
		return goodReceiving;
	}

	public void setGoodReceiving(GoodReceiving goodReceiving) {
		this.goodReceiving = goodReceiving;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@ManyToOne
	@JoinColumn(name = "uom_id")
	@Audited(targetAuditMode=RelationTargetAuditMode.NOT_AUDITED)
	public UnitOfMeasure getUom() {
		return uom;
	}

	public void setUom(UnitOfMeasure uom) {
		this.uom = uom;
	}

	
}
