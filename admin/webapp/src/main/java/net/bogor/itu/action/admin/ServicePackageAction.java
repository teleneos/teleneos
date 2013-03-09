package net.bogor.itu.action.admin;

import javax.inject.Inject;

import net.bogor.itu.service.admin.ServicePackageService;

import org.apache.struts2.interceptor.validation.SkipValidation;
import org.meruvian.inca.struts2.rest.ActionResult;
import org.meruvian.inca.struts2.rest.annotation.Action;
import org.meruvian.inca.struts2.rest.annotation.Action.HttpMethod;
import org.meruvian.inca.struts2.rest.annotation.Result;
import org.meruvian.inca.struts2.rest.annotation.Results;
import org.meruvian.yama.actions.DefaultAction;

import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.validator.annotations.ConversionErrorFieldValidator;
import com.opensymphony.xwork2.validator.annotations.RequiredStringValidator;
import com.opensymphony.xwork2.validator.annotations.Validations;

/**
 * @author Dian Aditya
 * 
 */
@Action(name = "/admin/service")
@Results({ @Result(name = DefaultAction.INPUT, type = "freemarker", location = "/view/admin/service/service-form.ftl") })
public class ServicePackageAction extends DefaultAction implements
		ModelDriven<ServicePackageActionModel> {
	private ServicePackageActionModel model = new ServicePackageActionModel();
	private ActionResult redirectToIndex = new ActionResult("redirect",
			"/admin/service");

	@Inject
	private ServicePackageService service;

	@Action
	public ActionResult serviceList() {
		model.setServices(service.findByKeyword(model.getQ(), model.getMax(),
				model.getPage() - 1));

		return new ActionResult("freemarker",
				"/view/admin/service/service-list.ftl");
	}

	@Action(name = "/add", method = HttpMethod.GET)
	public ActionResult addForm() {
		return new ActionResult("freemarker",
				"/view/admin/service/service-form.ftl");
	}

	@Action(name = "/edit/{service.id}", method = HttpMethod.GET)
	public ActionResult editForm() {
		String id = model.getService().getId();
		if (id == null)
			return redirectToIndex;

		model.setService(service.findById(id));
		if (model.getService() == null)
			return redirectToIndex;

		return new ActionResult("freemarker",
				"/view/admin/service/service-form.ftl");
	}

	@Action(name = "/edit/{service.id}", method = HttpMethod.POST)
	@Validations(requiredStrings = { @RequiredStringValidator(fieldName = "service.name", trim = true, key = "message.admin.service.name.notnull") }, conversionErrorFields = {
			@ConversionErrorFieldValidator(fieldName = "service.quotaLimit", key = "message.admin.service.trafficquota.notnumber"),
			@ConversionErrorFieldValidator(fieldName = "service.timeLimit", key = "message.admin.service.timequota.notnumber"),
			@ConversionErrorFieldValidator(fieldName = "service.unitPrice", key = "message.admin.service.unitprice.notnumber") })
	public ActionResult updateService() {
		return addService();
	}

	@Action(name = "/add", method = HttpMethod.POST)
	@Validations(requiredStrings = { @RequiredStringValidator(fieldName = "service.name", trim = true, key = "message.admin.service.name.notnull") }, conversionErrorFields = {
			@ConversionErrorFieldValidator(fieldName = "service.quotaLimit", key = "message.admin.service.trafficquota.notnumber"),
			@ConversionErrorFieldValidator(fieldName = "service.timeLimit", key = "message.admin.service.timequota.notnumber"),
			@ConversionErrorFieldValidator(fieldName = "service.unitPrice", key = "message.admin.service.unitprice.notnumber") })
	public ActionResult addService() {
		service.save(model.getService());

		return redirectToIndex;
	}

	@Override
	public ServicePackageActionModel getModel() {
		return model;
	}
}