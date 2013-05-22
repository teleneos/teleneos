package org.teleneos.pos.invoice;

import javax.inject.Inject;

import org.apache.commons.lang.StringUtils;
import org.meruvian.yama.persistence.EntityListWrapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Edy Setiawan
 * 
 */
@Service
@Transactional(readOnly = true)
public class InvoiceImplService implements InvoiceService {

	@Inject
	private InvoiceRepository invoiceRepository;

	@Override
	public Invoice findById(String id) {
		return invoiceRepository.findById(id);
	}

	@Override
	@Transactional
	public Invoice save(Invoice invoice) {
		if (StringUtils.isBlank(invoice.getId())) {
			invoice.setId(null);

			invoiceRepository.persist(invoice);
		} else {
			Invoice in = invoiceRepository.load(invoice.getId());
			in.setStatus(invoice.getStatus());

			invoice = in;
		}

		return invoice;
	}

	@Override
	public EntityListWrapper<Invoice> findByKeyword(String keyword,
			String order, String orderBy, int limit, int page) {
		if (StringUtils.isBlank(keyword))
			return invoiceRepository.findAll(limit, page);
		return invoiceRepository.findByKeyword(keyword, order, orderBy, limit,
				page, "OR");
	}

}
