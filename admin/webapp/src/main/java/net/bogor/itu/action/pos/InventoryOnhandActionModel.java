package net.bogor.itu.action.pos;

import net.bogor.itu.entity.pos.InventoryOnhand;
import net.bogor.itu.entity.pos.Item;

import org.meruvian.yama.actions.DefaultActionModel;
import org.meruvian.yama.persistence.EntityListWrapper;

/**
 * @author Edy Setiawan
 * 
 */
public class InventoryOnhandActionModel extends DefaultActionModel {

	private InventoryOnhand inventoryOnhand = new InventoryOnhand();
	private EntityListWrapper<InventoryOnhand> inventoryOnhands = new EntityListWrapper<InventoryOnhand>();

	public InventoryOnhand getInventoryOnhand() {
		return inventoryOnhand;
	}

	public void setInventoryOnhand(InventoryOnhand inventoryOnhand) {
		this.inventoryOnhand = inventoryOnhand;
	}

	public EntityListWrapper<InventoryOnhand> getInventoryOnhands() {
		return inventoryOnhands;
	}

	public void setInventoryOnhands(
			EntityListWrapper<InventoryOnhand> inventoryOnhands) {
		this.inventoryOnhands = inventoryOnhands;
	}
}
