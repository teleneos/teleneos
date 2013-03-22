package net.bogor.itu.service.pos;

import net.bogor.itu.entity.pos.InventoryOnhand;

import org.meruvian.yama.persistence.EntityListWrapper;

/**
 * @author Edy Setiawan
 * 
 */
public interface InventoryOnhandService {
	
	InventoryOnhand findById(String id);

	InventoryOnhand save(InventoryOnhand inventoryOnhand);

	EntityListWrapper<InventoryOnhand> findByKeyword(String keyword,String order,String orderBy, int limit,
			int page);

}
