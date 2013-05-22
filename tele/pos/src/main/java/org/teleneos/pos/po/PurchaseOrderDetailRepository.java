package org.teleneos.pos.po;

import org.meruvian.yama.persistence.EntityListWrapper;
import org.meruvian.yama.persistence.PersistenceRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Edy Setiawan
 * 
 */
@Repository
public class PurchaseOrderDetailRepository extends
		PersistenceRepository<PurchaseOrderDetail> {

	public EntityListWrapper<PurchaseOrderDetail> findByKeyword(String keyword,
			int limit, int page) {

		String criteria = "(rd.purchaseOrder.id = ?)";
		Object[] params = { keyword };

		return findAll(limit, page, "rd", criteria, params);
	}

}
