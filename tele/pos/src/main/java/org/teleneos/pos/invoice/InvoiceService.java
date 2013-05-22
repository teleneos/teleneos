package org.teleneos.pos.invoice;

import org.meruvian.yama.persistence.EntityListWrapper;

/**
 * @author Edy Setiawan
 * 
 */
public interface InvoiceService {
	Invoice findById(String id);

	Invoice save(Invoice invoice);

	EntityListWrapper<Invoice> findByKeyword(String keyword, String order,
			String orderBy, int limit, int page);
}
