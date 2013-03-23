package net.bogor.itu.service.pos;

import net.bogor.itu.entity.pos.PurchaseOrder;

import org.meruvian.yama.persistence.EntityListWrapper;



/**
 * @author Edy Setiawan
 * 
 */
public interface PurchaseOrderService {
	PurchaseOrder findById(String id);

	PurchaseOrder save(PurchaseOrder purchaseOrder);

	EntityListWrapper<PurchaseOrder> findByKeyword(String keyword,String order,String orderBy, int limit,
			int page);
	
}