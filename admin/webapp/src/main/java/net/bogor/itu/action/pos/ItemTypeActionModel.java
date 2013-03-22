package net.bogor.itu.action.pos;

import net.bogor.itu.entity.pos.ItemType;

import org.meruvian.yama.actions.DefaultActionModel;
import org.meruvian.yama.persistence.EntityListWrapper;
/**
 * @author Edy Setiawan
 * 
 */
public class ItemTypeActionModel extends DefaultActionModel{

	private ItemType ItemType = new ItemType();
	private EntityListWrapper<ItemType> itemTypes = new EntityListWrapper<ItemType>();

	public ItemType getItemType() {
		return ItemType;
	}

	public void setItemType(ItemType itemType) {
		ItemType = itemType;
	}

	public EntityListWrapper<ItemType> getItemTypes() {
		return itemTypes;
	}

	public void setItemTypes(EntityListWrapper<ItemType> itemTypes) {
		this.itemTypes = itemTypes;
	}

}
