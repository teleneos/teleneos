package org.teleneos.pos.po;

import org.meruvian.yama.persistence.EntityListWrapper;

/**
 * @author Edy Setiawan
 * 
 */
public interface PurchaseOrderDetailService {

	PurchaseOrderDetail findById(String id);

	PurchaseOrderDetail save(PurchaseOrderDetail purchaseOrderDetail);

	EntityListWrapper<PurchaseOrderDetail> findByKeyword(String keyword,
			int limit, int page);

}
