package org.teleneos.pos.po;

import org.meruvian.yama.persistence.EntityListWrapper;

/**
 * @author Edy Setiawan
 * 
 */
public interface PurchaseOrderService {
	PurchaseOrder findById(String id);

	PurchaseOrder save(PurchaseOrder purchaseOrder);

	EntityListWrapper<PurchaseOrder> findByKeyword(String keyword,
			String order, String orderBy, int limit, int page);

}