package net.bogor.itu.action.pos;

import net.bogor.itu.entity.pos.Item;

import org.meruvian.yama.actions.DefaultAction;
import org.meruvian.yama.actions.DefaultActionModel;
import org.meruvian.yama.persistence.EntityListWrapper;
/**
 * @author Edy Setiawan
 * 
 */
public class ItemActionModel extends DefaultActionModel {

	private Item item = new Item();
	private EntityListWrapper<Item> items = new EntityListWrapper<Item>();

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public EntityListWrapper<Item> getItems() {
		return items;
	}

	public void setItems(EntityListWrapper<Item> items) {
		this.items = items;
	}

}
