package org.teleneos.radius.internetpackage;

import javax.inject.Inject;

import org.meruvian.inca.struts2.rest.ActionResult;
import org.meruvian.inca.struts2.rest.annotation.Action;
import org.meruvian.inca.struts2.rest.annotation.Action.HttpMethod;
import org.meruvian.inca.struts2.rest.annotation.Result;
import org.meruvian.inca.struts2.rest.annotation.Results;
import org.meruvian.yama.actions.DefaultAction;
import org.springframework.dao.DataIntegrityViolationException;
import org.teleneos.radius.internetpackage.InternetPackage.Type;

import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.validator.annotations.ConversionErrorFieldValidator;
import com.opensymphony.xwork2.validator.annotations.RequiredStringValidator;
import com.opensymphony.xwork2.validator.annotations.Validations;

@Action(name = "/master/packages")
@Results({
		@Result(name = DefaultAction.INPUT, type = "freemarker", location = "/view/master/packagemanager/packagemanager-form.ftl"),
		@Result(name = DefaultAction.INDEX, type = "freemarker", location = "/view/master/packagemanager/packagemanager-list.ftl") })
public class PackageManagerAction extends DefaultAction implements
		ModelDriven<PackageManagerActionModel> {

	private static final long serialVersionUID = 735436507412605816L;

	private PackageManagerActionModel model = new PackageManagerActionModel();
	private ActionResult redirectToIndex = new ActionResult("redirect",
			"/master/packages");

	@Inject
	private PackageManagerService service;

	@Action
	public String packageList() {
		model.setInternetPackages(service.findByName(model.getQ(), null, "ASC",
				model.getMax(), model.getPage() - 1));
		return DefaultAction.INDEX;
	}

	@Action(name = "/add", method = HttpMethod.GET)
	public String addForm() {
		return DefaultAction.INPUT;
	}

	@Action(name = "/edit/{internetPackage.id}", method = HttpMethod.GET)
	public ActionResult editForm() {
		String id = model.getInternetPackage().getId();
		if (id == null)
			return redirectToIndex;
		model.setInternetPackage(service.findById(id));
		if (model.getInternetPackage() == null)
			return redirectToIndex;

		return new ActionResult("freemarker",
				"/view/master/packagemanager/packagemanager-form.ftl");
	}

	@Action(name = "/edit/{internetPackage.id}", method = HttpMethod.POST)
	@Validations(requiredStrings = {
			@RequiredStringValidator(fieldName = "internetPackage.code", trim = true, key = "message.master.package.code.notnull"),
			@RequiredStringValidator(fieldName = "internetPackage.name", trim = true, key = "message.master.package.name.notnull") }, conversionErrorFields = {
			@ConversionErrorFieldValidator(fieldName = "internetPackage.price", key = "message.master.package.price.notvalid"),
			@ConversionErrorFieldValidator(fieldName = "internetPackage.time", key = "message.master.package.time.notvalid"),
			@ConversionErrorFieldValidator(fieldName = "internetPackage.quota", key = "message.master.package.quota.notvalid"),
			@ConversionErrorFieldValidator(fieldName = "internetPackage.speed", key = "message.master.package.speed.notvalid"),
			@ConversionErrorFieldValidator(fieldName = "internetPackage.nextSpeed", key = "message.master.package.nextspeed.notvalid") })
	public ActionResult updateService() {
		return addService();
	}

	@Action(name = "/add", method = HttpMethod.POST)
	@Validations(requiredStrings = {
			@RequiredStringValidator(fieldName = "internetPackage.code", trim = true, key = "message.master.package.code.notnull"),
			@RequiredStringValidator(fieldName = "internetPackage.name", trim = true, key = "message.master.package.name.notnull")}, conversionErrorFields = {
			@ConversionErrorFieldValidator(fieldName = "internetPackage.price", key = "message.master.package.price.notvalid"),
			@ConversionErrorFieldValidator(fieldName = "internetPackage.time", key = "message.master.package.time.notvalid"),
			@ConversionErrorFieldValidator(fieldName = "internetPackage.quota", key = "message.master.package.quota.notvalid"),
			@ConversionErrorFieldValidator(fieldName = "internetPackage.speed", key = "message.master.package.speed.notvalid"),
			@ConversionErrorFieldValidator(fieldName = "internetPackage.nextSpeed", key = "message.master.package.nextspeed.notvalid") })
	public ActionResult addService() {
		if (model.getInternetPackage().getPaymentMethod()
				.equals(PaymentMethod.POSTPAID)) {
			model.getInternetPackage().setType(Type.NON_COUNTDOWN);
		} else if (model.getInternetPackage().getPaymentMethod()
				.equals(PaymentMethod.PREPAID)) {
			model.getInternetPackage().setType(Type.COUNTDOWN);
		}
		model.getInternetPackage().setTime(
				model.getInternetPackage().getTime() * model.getQt());

		System.out.println("Quooota :" + model.getInternetPackage().getQuota());
		System.out.println("Quooota :" + model.getQb());
		model.getInternetPackage().setQuota(
				model.getInternetPackage().getQuota() * model.getQb());

		model.getInternetPackage().setSpeed(
				model.getInternetPackage().getSpeed() * model.getQs());

		model.getInternetPackage().setNextSpeed(
				model.getInternetPackage().getNextSpeed() * model.getQns());

		try {
			service.save(model.getInternetPackage());
		} catch (DataIntegrityViolationException e) {
			addFieldError("internetPackage.code", "Code "
					+ model.getInternetPackage().getCode() + " already exist");
		}

		return redirectToIndex;
	}

	@Override
	public PackageManagerActionModel getModel() {
		return model;
	}

}
