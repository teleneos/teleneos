package net.bogor.itu.service.pos;

import javax.inject.Inject;

import net.bogor.itu.entity.pos.BusinessPartner;
import net.bogor.itu.repository.pos.BusinessPartnerRepository;

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
public class BusinessPartnerImplService implements BusinessPartnerService{
	
	@Inject
	private BusinessPartnerRepository businessPartnerRepository;

	@Override
	public BusinessPartner findById(String id) {
		return businessPartnerRepository.findById(id);
	}

	@Override
	@Transactional
	public BusinessPartner save(BusinessPartner businessPartner) {
		if (StringUtils.isBlank(businessPartner.getId())) {
			businessPartner.setId(null);

			businessPartnerRepository.persist(businessPartner);
		} else {
			BusinessPartner bp = businessPartnerRepository.load(businessPartner.getId());
			bp.setCategory(businessPartner.getCategory());
			bp.setName(businessPartner.getName());
			bp.setOfficePhone(businessPartner.getOfficePhone());
			bp.setFax(businessPartner.getFax());
			bp.setEmail(businessPartner.getEmail());
			bp.setAddress(businessPartner.getAddress());
			bp.setCity(businessPartner.getCity());
			bp.setZipCode(businessPartner.getZipCode());
			bp.setCountry(businessPartner.getCountry());
			bp.setDescription(businessPartner.getDescription());

			businessPartner = bp;
		}
		return businessPartner;
	}

	@Override
	public EntityListWrapper<BusinessPartner> findByKeyword(String keyword,
			String order, String orderBy, int limit, int page) {
		if (StringUtils.isBlank(keyword))
			return businessPartnerRepository.findAll(limit, page);
		return businessPartnerRepository.findByKeyword(keyword,order,orderBy,limit, page,"OR");
	}

}
