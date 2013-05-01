package net.bogor.itu.service.pos;

import net.bogor.itu.entity.pos.StockAudit;

import org.meruvian.yama.persistence.EntityListWrapper;

public interface StockAuditService {
	
	StockAudit save(StockAudit stockAudit);

	EntityListWrapper<StockAudit> findByKeyword(String keyword, String order,
			String orderBy, int limit, int page);
	
	EntityListWrapper<StockAudit> findByItem(String itemId, String order,
			String orderBy, int limit, int page);
	
}
