package net.bogor.itu.action.pos;

import net.bogor.itu.entity.pos.BusinessPartner;

import org.meruvian.yama.actions.DefaultActionModel;
import org.meruvian.yama.persistence.EntityListWrapper;

/**
 * @author Edy Setiawan
 * 
 */
public class BusinessPartnerActionModel extends DefaultActionModel {

	private BusinessPartner businessPartner = new BusinessPartner();
	private EntityListWrapper<BusinessPartner> businessPartners = new EntityListWrapper<BusinessPartner>();

	public BusinessPartner getBusinessPartner() {
		return businessPartner;
	}

	public void setBusinessPartner(BusinessPartner businessPartner) {
		this.businessPartner = businessPartner;
	}

	public EntityListWrapper<BusinessPartner> getBusinessPartners() {
		return businessPartners;
	}

	public void setBusinessPartners(
			EntityListWrapper<BusinessPartner> businessPartners) {
		this.businessPartners = businessPartners;
	}

}
