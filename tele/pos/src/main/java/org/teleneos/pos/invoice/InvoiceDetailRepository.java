package org.teleneos.pos.invoice;

import org.meruvian.yama.persistence.EntityListWrapper;
import org.meruvian.yama.persistence.PersistenceRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Edy Setiawan
 * 
 */
@Repository
public class InvoiceDetailRepository extends
		PersistenceRepository<InvoiceDetail> {

	public EntityListWrapper<InvoiceDetail> findByKeyword(String keyword,
			int limit, int page) {

		String criteria = "(rd.invoice.id = ?)";
		Object[] params = { keyword };

		return findAll(limit, page, "rd", criteria, params);
	}

}
