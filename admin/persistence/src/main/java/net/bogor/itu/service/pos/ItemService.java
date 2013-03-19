package net.bogor.itu.service.pos;

import net.bogor.itu.entity.pos.Item;

import org.meruvian.yama.persistence.EntityListWrapper;

/**
 * @author Edy Setiawan
 * 
 */
public interface ItemService {

	Item findById(String id);

	Item save(Item item);

	EntityListWrapper<Item> findByKeyword(String keyword,String order,String orderBy, int limit,
			int page);

}
