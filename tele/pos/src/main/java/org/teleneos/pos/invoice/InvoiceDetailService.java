package org.teleneos.pos.invoice;

import org.meruvian.yama.persistence.EntityListWrapper;

/**
 * @author Edy Setiawan
 * 
 */
public interface InvoiceDetailService {
	InvoiceDetail findById(String id);

	InvoiceDetail save(InvoiceDetail invoiceDetail);

	EntityListWrapper<InvoiceDetail> findByKeyword(String keyword, int limit,
			int page);

}
