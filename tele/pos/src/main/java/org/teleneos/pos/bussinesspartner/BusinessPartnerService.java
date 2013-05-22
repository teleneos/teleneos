package org.teleneos.pos.bussinesspartner;

import org.meruvian.yama.persistence.EntityListWrapper;

/**
 * @author Edy Setiawan
 * 
 */
public interface BusinessPartnerService {

	BusinessPartner findById(String id);

	BusinessPartner save(BusinessPartner businessPartner);

	EntityListWrapper<BusinessPartner> findByKeyword(String keyword,
			String order, String orderBy, int limit, int page);

}
