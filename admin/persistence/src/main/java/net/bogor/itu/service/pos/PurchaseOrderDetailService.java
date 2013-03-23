package net.bogor.itu.service.pos;

import net.bogor.itu.entity.pos.PurchaseOrderDetail;

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
