package org.teleneos.pos.item;

import org.meruvian.yama.actions.DefaultActionModel;
import org.meruvian.yama.persistence.EntityListWrapper;

/**
 * @author Edy Setiawan
 * 
 */
public class ItemCategoryActionModel extends DefaultActionModel {

	private ItemCategory itemCategory = new ItemCategory();
	private EntityListWrapper<ItemCategory> itemCategories = new EntityListWrapper<ItemCategory>();

	public ItemCategory getItemCategory() {
		return itemCategory;
	}

	public void setItemCategory(ItemCategory itemCategory) {
		this.itemCategory = itemCategory;
	}

	public EntityListWrapper<ItemCategory> getItemCategories() {
		return itemCategories;
	}

	public void setItemCategories(EntityListWrapper<ItemCategory> itemCategories) {
		this.itemCategories = itemCategories;
	}

}
