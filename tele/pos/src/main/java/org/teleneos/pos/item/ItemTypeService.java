package org.teleneos.pos.item;

import org.meruvian.yama.persistence.EntityListWrapper;

/**
 * @author Edy Setiawan
 * 
 */
public interface ItemTypeService {
	ItemType findById(String id);

	ItemType save(ItemType itemType);

	EntityListWrapper<ItemType> findByKeyword(String keyword, String order,
			String orderBy, int limit, int page);
}
