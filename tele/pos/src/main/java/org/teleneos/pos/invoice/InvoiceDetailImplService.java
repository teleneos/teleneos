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
public class InvoiceDetailImplService implements InvoiceDetailService {

	@Inject
	private InvoiceDetailRepository invoiceDetailRepository;

	@Override
	public InvoiceDetail findById(String id) {
		return invoiceDetailRepository.findById(id);
	}

	@Override
	@Transactional
	public InvoiceDetail save(InvoiceDetail invoiceDetail) {
		if (StringUtils.isBlank(invoiceDetail.getId())) {
			invoiceDetail.setId(null);

			invoiceDetailRepository.persist(invoiceDetail);
		} else {
			InvoiceDetail id = invoiceDetailRepository.load(invoiceDetail
					.getId());
			id.setInvoice(invoiceDetail.getInvoice());
			id.setItem(invoiceDetail.getItem());
			id.setQuantity(invoiceDetail.getQuantity());
			id.setPrice(invoiceDetail.getPrice());

			invoiceDetail = id;
		}
		return invoiceDetail;
	}

	@Override
	public EntityListWrapper<InvoiceDetail> findByKeyword(String keyword,
			int limit, int page) {
		if (StringUtils.isBlank(keyword))
			return invoiceDetailRepository.findAll(limit, page);
		return invoiceDetailRepository.findByKeyword(keyword, limit, page);
	}

}
