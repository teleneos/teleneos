package org.teleneos.pos.inventoryonhand;

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
@Table(name = "tc_inventory_onhand")
public class InventoryOnhand extends DefaultPersistence {

	private Item item;
	private int stock;

	@ManyToOne
	@JoinColumn(name = "item_id")
	@Audited(targetAuditMode=RelationTargetAuditMode.NOT_AUDITED)
	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

}
