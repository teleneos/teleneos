package net.bogor.itu.service.pos;

import net.bogor.itu.entity.pos.GoodReceiving;

import org.meruvian.yama.persistence.EntityListWrapper;

/**
 * @author Edy Setiawan
 * 
 */
public interface GoodReceivingService {
	GoodReceiving findById(String id);

	GoodReceiving save(GoodReceiving goodReceiving);

	EntityListWrapper<GoodReceiving> findByKeyword(String keyword,String order,String orderBy, int limit,
			int page);
}
