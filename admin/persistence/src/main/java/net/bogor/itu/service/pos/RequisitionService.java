package net.bogor.itu.service.pos;

import net.bogor.itu.entity.pos.Requisition;

import org.meruvian.yama.persistence.EntityListWrapper;

/**
 * @author Edy Setiawan
 * 
 */
public interface RequisitionService {
	Requisition findById(String id);

	Requisition save(Requisition requisition);

	EntityListWrapper<Requisition> findByKeyword(String keyword,String order,String orderBy, int limit,
			int page);
}
