package net.bogor.itu.action.master;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.inject.Inject;

import net.bogor.itu.entity.master.InternetPackage.Status;
import net.bogor.itu.entity.master.InternetPackage.Type;
import net.bogor.itu.service.master.PackageManagerService;

import org.meruvian.inca.struts2.rest.ActionResult;
import org.meruvian.inca.struts2.rest.annotation.Action;
import org.meruvian.inca.struts2.rest.annotation.Action.HttpMethod;
import org.meruvian.inca.struts2.rest.annotation.Result;
import org.meruvian.inca.struts2.rest.annotation.Results;
import org.meruvian.yama.actions.DefaultAction;

import com.opensymphony.xwork2.ModelDriven;

@Action(name = "/master/packages")
@Results({ 
	@Result(name = DefaultAction.INPUT, type = "freemarker", location = "/view/master/packagemanager/packagemanager-form.ftl"), 
	@Result(name = DefaultAction.INDEX, type = "freemarker", location = "/view/master/packagemanager/packagemanager-list.ftl")
	})
public class PackageManagerAction extends DefaultAction implements
		ModelDriven<PackageManagerActionModel> {

	private static final long serialVersionUID = 735436507412605816L;
	
	private PackageManagerActionModel model = new PackageManagerActionModel();
	private ActionResult redirectToIndex = new ActionResult("redirect", "/master/packages");
	
	@Inject 
	private PackageManagerService service;
	
	@Action
	public String packageList() {
		model.setInternetPackages(service.findByName(model.getQ(),null,"ASC", model.getMax(),
				model.getPage() - 1));
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
				"/view/master/packagemanager/packagemanager-list.ftl");
	}
	
	@Action(name = "/edit/{internetPackage.id}", method = HttpMethod.POST)
//	@Validations(
//			requiredStrings = { 
//				@RequiredStringValidator(fieldName = "internetPackage.name", trim = true, key = "message.master.package.name.notnull"),
//				@RequiredStringValidator(fieldName = "internetPackage.type", trim = true, key = "message.master.package.type.notnull")}, 
//			requiredFields = {
//				@RequiredFieldValidator(fieldName = "internetPackage.variable", key = "message.master.package.variable.notnull"),
//				@RequiredFieldValidator(fieldName = "internetPackage.price", key = "message.master.package.price.notnull")
//			})
	public ActionResult updateService() {
		return addService();
	}

	@Action(name = "/add", method = HttpMethod.POST)
//	@Validations(
//			requiredStrings = { 
//				@RequiredStringValidator(fieldName = "internetPackage.name", trim = true, key = "message.master.package.name.notnull")}, 
//			requiredFields = {
//				@RequiredFieldValidator(fieldName = "internetPackage.variable", key = "message.master.package.variable.notnull"),
//				@RequiredFieldValidator(fieldName = "internetPackage.price", key = "message.master.package.price.notnull"),
//				@RequiredFieldValidator(fieldName = "internetPackage.type", key = "message.master.package.type.notnull")
//			})
	public ActionResult addService() {
		model.getInternetPackage().setStatus(model.getStatus() == 0 ? Status.ENABLE : Status.DISABLE);
		model.getInternetPackage().setType(model.getType() == 0 ? Type.COUNTDOWN : Type.FIXTIME);
		if (model.getType() == 1) {
			SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
			try {
				model.getInternetPackage().setVariable(format.parse(model.getVariable()).getTime());
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}else{
			model.getInternetPackage().setVariable(Long.parseLong(model.getVariable()));
		}
		service.save(model.getInternetPackage());
		return redirectToIndex;
	}
	
	@Override
	public PackageManagerActionModel getModel() {
		return model;
	}

}
