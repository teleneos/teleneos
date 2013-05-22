package org.teleneos.pos.item;

import org.meruvian.yama.persistence.EntityListWrapper;

/**
 * @author Edy Setiawan
 * 
 */
public interface ItemService {

	Item findById(String id);

	Item save(Item item);

	EntityListWrapper<Item> findByKeyword(String keyword, String order,
			String orderBy, int limit, int page);

}
