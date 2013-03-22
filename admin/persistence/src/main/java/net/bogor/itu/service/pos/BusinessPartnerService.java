package net.bogor.itu.service.pos;

import net.bogor.itu.entity.pos.BusinessPartner;

import org.meruvian.yama.persistence.EntityListWrapper;
/**
 * @author Edy Setiawan
 * 
 */
public interface BusinessPartnerService {

	BusinessPartner findById(String id);

	BusinessPartner save(BusinessPartner businessPartner);

	EntityListWrapper<BusinessPartner> findByKeyword(String keyword,String order,String orderBy, int limit,
			int page);
	
}
