package org.teleneos.pos.item;

import org.meruvian.yama.persistence.EntityListWrapper;

/**
 * @author Edy Setiawan
 * 
 */
public interface ItemCategoryService {
	ItemCategory findById(String id);

	ItemCategory save(ItemCategory itemCategory);

	EntityListWrapper<ItemCategory> findByKeyword(String keyword, String order,
			String orderBy, int limit, int page);
}
