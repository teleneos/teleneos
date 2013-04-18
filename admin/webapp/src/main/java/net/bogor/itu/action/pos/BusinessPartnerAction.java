package net.bogor.itu.action.pos;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import net.bogor.itu.service.pos.BusinessPartnerService;

import org.meruvian.inca.struts2.rest.ActionResult;
import org.meruvian.inca.struts2.rest.annotation.Action;
import org.meruvian.inca.struts2.rest.annotation.Action.HttpMethod;
import org.meruvian.inca.struts2.rest.annotation.Result;
import org.meruvian.inca.struts2.rest.annotation.Results;
import org.meruvian.yama.actions.DefaultAction;

import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.validator.annotations.RequiredStringValidator;
import com.opensymphony.xwork2.validator.annotations.Validations;

@Action(name = "/pos/businesspartner")
@Results({ @Result(name = DefaultAction.INPUT, type = "freemarker", location = "/view/pos/businesspartner/businesspartner-form.ftl") })
public class BusinessPartnerAction extends DefaultAction implements ModelDriven<BusinessPartnerActionModel>{
	private BusinessPartnerActionModel model = new BusinessPartnerActionModel();
	
	private List<String> category = new ArrayList<String>();
	
	private ActionResult redirectToIndex = new ActionResult("redirect",
			"/pos/businesspartner");
	
	@Inject
	private BusinessPartnerService businessPartnerService;

	@Action
	public ActionResult businessPartnerList() {
		model.setBusinessPartners(businessPartnerService.findByKeyword(model.getQ(),null,"ASC", model.getMax(),
				model.getPage() - 1));

		return new ActionResult("freemarker",
				"/view/pos/businesspartner/businesspartner-list.ftl");
	}

	@Action(name = "/add", method = HttpMethod.GET)
	public ActionResult addForm() {
		return new ActionResult("freemarker",
				"/view/pos/businesspartner/businesspartner-form.ftl");
	}

	@Action(name = "/edit/{businessPartner.id}", method = HttpMethod.GET)
	public ActionResult editForm() {
		String id = model.getBusinessPartner().getId();
		if (id == null)
			return redirectToIndex;

		model.setBusinessPartner(businessPartnerService.findById(id));
		if (model.getBusinessPartner() == null)
			return redirectToIndex;

		return new ActionResult("freemarker",
				"/view/pos/businesspartner/businesspartner-form.ftl");
	}

	@Action(name = "/edit/{businessPartner.id}", method = HttpMethod.POST)
	@Validations(requiredStrings = { @RequiredStringValidator(fieldName = "businessPartner.name", trim = true, key = "message.admin.businesspartner.name.notnull")})
	public ActionResult updateService() {
		return addService();
	}

	@Action(name = "/add", method = HttpMethod.POST)
	@Validations(requiredStrings = { @RequiredStringValidator(fieldName = "businessPartner.name", trim = true, key = "message.admin.businesspartner.name.notnull")})
	public ActionResult addService() {
		businessPartnerService.save(model.getBusinessPartner());

		return redirectToIndex;
	}
	
	@Override
	public BusinessPartnerActionModel getModel() {
		return model;
	}

	public List<String> getCategory() {
		category.add("Vendor");
		category.add("Customer");
		return category;
	}
	
}
