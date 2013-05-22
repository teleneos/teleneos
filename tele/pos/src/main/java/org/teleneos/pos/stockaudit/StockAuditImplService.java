package org.teleneos.pos.stockaudit;

import javax.inject.Inject;

import org.apache.commons.lang.StringUtils;
import org.meruvian.yama.persistence.EntityListWrapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class StockAuditImplService implements StockAuditService {

	@Inject
	private StockAuditRepository auditRepository;

	@Override
	@Transactional
	public StockAudit save(StockAudit stockAudit) {
		if (StringUtils.isBlank(stockAudit.getId())) {
			stockAudit.setId(null);
			auditRepository.persist(stockAudit);
		} else {
			StockAudit dt = auditRepository.load(stockAudit.getId());
			dt.setItem(stockAudit.getItem());
			dt.setDescription(stockAudit.getDescription());
			dt.setQuantity(stockAudit.getQuantity());
			stockAudit = dt;
		}
		return stockAudit;
	}

	@Override
	public EntityListWrapper<StockAudit> findByKeyword(String keyword,
			String order, String orderBy, int limit, int page) {
		if (StringUtils.isBlank(keyword))
			return auditRepository.findAll(limit, page);
		return auditRepository.findByKeyword(keyword, order, orderBy, limit,
				page, "OR");
	}

	@Override
	public EntityListWrapper<StockAudit> findByItem(String itemId,
			String order, String orderBy, int limit, int page) {
		return auditRepository.findByItem(itemId, order, orderBy, limit, page,
				"OR");
	}

}
