package net.bogor.itu.repository.pos;

import net.bogor.itu.entity.pos.PurchaseOrderDetail;
import net.bogor.itu.persistence.PersistenceRepository;

import org.meruvian.yama.persistence.EntityListWrapper;
import org.springframework.stereotype.Repository;

/**
 * @author Edy Setiawan
 * 
 */
@Repository
public class PurchaseOrderDetailRepository extends PersistenceRepository<PurchaseOrderDetail> {
	
	public EntityListWrapper<PurchaseOrderDetail> findByKeyword(String keyword,
			int limit, int page) { 

		String criteria = "(rd.purchaseOrder.id = ?)";
		Object[] params = {keyword};
		
		return findAll(limit, page, "rd", criteria, params);
	}

}
