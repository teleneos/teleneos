package org.teleneos.pos.requisition;

import org.meruvian.yama.persistence.EntityListWrapper;
import org.meruvian.yama.persistence.PersistenceRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Edy Setiawan
 * 
 */
@Repository
public class RequisitionDetailRepository extends
		PersistenceRepository<RequisitionDetail> {

	public EntityListWrapper<RequisitionDetail> findByKeyword(String keyword,
			int limit, int page) {

		String criteria = "(po.requisition.id = ?)";
		Object[] params = { keyword };

		return findAll(limit, page, "po", criteria, params);
	}

}
