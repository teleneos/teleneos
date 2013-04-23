package net.bogor.itu.service.pos;

import net.bogor.itu.entity.pos.GoodReceivingDetail;

import org.meruvian.yama.persistence.EntityListWrapper;

/**
 * @author Edy Setiawan
 * 
 */
public interface GoodReceivingDetailService {
	GoodReceivingDetail findById(String id);

	GoodReceivingDetail save(GoodReceivingDetail goodReceivingDetail);

	EntityListWrapper<GoodReceivingDetail> findByKeyword(String keyword,
			int limit, int page);
	
	EntityListWrapper<GoodReceivingDetail> findByParent(String id,
			int limit, int page);
}
