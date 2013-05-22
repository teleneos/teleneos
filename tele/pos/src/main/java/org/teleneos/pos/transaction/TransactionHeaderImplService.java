package org.teleneos.pos.transaction;

import javax.inject.Inject;

import org.apache.commons.lang.StringUtils;
import org.meruvian.yama.persistence.EntityListWrapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.teleneos.pos.inventoryonhand.InventoryOnhand;
import org.teleneos.pos.inventoryonhand.InventoryOnhandRepository;
import org.teleneos.pos.stockaudit.StockAudit;
import org.teleneos.pos.stockaudit.StockAudit.Type;
import org.teleneos.pos.stockaudit.StockAuditService;
import org.teleneos.radius.userpackage.UserPackageRepository;

/**
 * @author Edy Setiawan
 * 
 */
@Service
@Transactional(readOnly = true)
public class TransactionHeaderImplService implements TransactionHeaderService {

	@Inject
	private TransactionHeaderRepository tHeaderRepository;

	@Inject
	private UserPackageRepository packageRepository;

	@Inject
	private InventoryOnhandRepository onhandRepository;

	@Inject
	private StockAuditService auditService;

	@Override
	public TransactionHeader findById(String id) {
		return tHeaderRepository.findById(id);
	}

	@Override
	@Transactional
	public TransactionHeader save(TransactionHeader transactionHeader) {
		if (StringUtils.isBlank(transactionHeader.getId())) {
			transactionHeader.setId(null);

			tHeaderRepository.persist(transactionHeader);
		} else {
			TransactionHeader th = tHeaderRepository.load(transactionHeader
					.getId());
			th.setCash(transactionHeader.getCash());
			th.setComplete(true);
			for (TransactionDetail td : th.getDetails()) {
				if (td.getItem() != null) {
					InventoryOnhand stock = onhandRepository.findByItem(td
							.getItem().getId());
					if (td.getConversion() != null) {
						int out = (td.getQuantity() * td.getConversion()
								.getMultiplyRate());
						stock.setStock(stock.getStock() - out);
						auditService.save(new StockAudit(td.getItem(),
								Type.OUTBOUND, out, "sales user "
										+ th.getUsername(), stock.getStock()));
					} else {
						stock.setStock(stock.getStock() - (td.getQuantity()));
						auditService.save(new StockAudit(td.getItem(),
								Type.OUTBOUND, td.getQuantity(), "sales user "
										+ th.getUsername(), stock.getStock()));
					}
				}
			}
			packageRepository.save(th.getId(), th.getUsername());

			transactionHeader = th;
		}

		return transactionHeader;
	}

	@Override
	public EntityListWrapper<TransactionHeader> findByKeyword(String keyword,
			String order, String orderBy, int limit, int page) {
		return tHeaderRepository.findByKeyword(keyword, limit, page);
	}

}
