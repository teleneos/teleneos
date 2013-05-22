package org.teleneos.pos.inventoryonhand;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.meruvian.yama.persistence.DefaultPersistence;
import org.teleneos.pos.item.Item;

/**
 * @author Edy Setiawan
 * 
 */
@Entity
@Table(name = "tc_inventory_onhand")
public class InventoryOnhand extends DefaultPersistence {

	private Item item;
	private int stock;

	@ManyToOne
	@JoinColumn(name = "item_id")
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
