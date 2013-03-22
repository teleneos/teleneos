package net.bogor.itu.service.pos;

import net.bogor.itu.entity.pos.RequisitionDetail;

import org.meruvian.yama.persistence.EntityListWrapper;

/**
 * @author Edy Setiawan
 * 
 */
public interface RequisitionDetailService {
	RequisitionDetail findById(String id);
	
	RequisitionDetail save(RequisitionDetail requisitionDetail);
	
	EntityListWrapper<RequisitionDetail> findByKeyword(String keyword, int limit,
			int page);
}
