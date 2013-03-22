package net.bogor.itu.repository.pos;

import net.bogor.itu.entity.pos.RequisitionDetail;
import net.bogor.itu.persistence.PersistenceRepository;

import org.meruvian.yama.persistence.EntityListWrapper;
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

		String criteria = "(rd.requisition.id = ?)";
		Object[] params = {keyword};
		
		return findAll(limit, page, "rd", criteria, params);
	}

}
