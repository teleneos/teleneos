package net.bogor.itu.service.pos;

import net.bogor.itu.entity.pos.ItemCategory;

import org.meruvian.yama.persistence.EntityListWrapper;
/**
 * @author Edy Setiawan
 * 
 */
public interface ItemCategoryService {
	ItemCategory findById(String id);

	ItemCategory save(ItemCategory itemCategory);

	EntityListWrapper<ItemCategory> findByKeyword(String keyword,String order,String orderBy, int limit,
			int page);
}
