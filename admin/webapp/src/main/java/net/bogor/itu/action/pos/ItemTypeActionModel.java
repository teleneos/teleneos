package net.bogor.itu.action.pos;

import java.util.ArrayList;
import java.util.List;

import net.bogor.itu.entity.pos.ItemType;
import net.bogor.itu.entity.pos.UnitOfMeasure;

import org.meruvian.yama.actions.DefaultActionModel;
import org.meruvian.yama.persistence.EntityListWrapper;
/**
 * @author Edy Setiawan
 * 
 */
public class ItemTypeActionModel extends DefaultActionModel{

	private ItemType ItemType = new ItemType();
	private EntityListWrapper<ItemType> itemTypes = new EntityListWrapper<ItemType>();
	private EntityListWrapper<UnitOfMeasure> uoms = new EntityListWrapper<UnitOfMeasure>();

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

	public EntityListWrapper<UnitOfMeasure> getUoms() {
		return uoms;
	}

	public void setUoms(EntityListWrapper<UnitOfMeasure> uoms) {
		this.uoms = uoms;
	}
}
