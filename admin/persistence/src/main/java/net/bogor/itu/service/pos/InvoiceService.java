package net.bogor.itu.service.pos;

import net.bogor.itu.entity.pos.Invoice;

import org.meruvian.yama.persistence.EntityListWrapper;
/**
 * @author Edy Setiawan
 * 
 */
public interface InvoiceService {
	Invoice findById(String id);

	Invoice save(Invoice invoice);

	EntityListWrapper<Invoice> findByKeyword(String keyword,String order,String orderBy, int limit,
			int page);
}
