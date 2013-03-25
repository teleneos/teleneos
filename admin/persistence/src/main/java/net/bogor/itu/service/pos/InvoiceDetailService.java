package net.bogor.itu.service.pos;

import net.bogor.itu.entity.pos.InvoiceDetail;

import org.meruvian.yama.persistence.EntityListWrapper;

/**
 * @author Edy Setiawan
 * 
 */
public interface InvoiceDetailService {
	InvoiceDetail findById(String id);

	InvoiceDetail save(InvoiceDetail invoiceDetail);

	EntityListWrapper<InvoiceDetail> findByKeyword(String keyword,
			int limit, int page);

}
