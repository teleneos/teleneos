package net.bogor.itu.action.ticket;

import javax.inject.Inject;

import net.bogor.itu.service.ticket.DepartmentService;

import org.meruvian.inca.struts2.rest.ActionResult;
import org.meruvian.inca.struts2.rest.annotation.Action;
import org.meruvian.inca.struts2.rest.annotation.Result;
import org.meruvian.inca.struts2.rest.annotation.Results;
import org.meruvian.inca.struts2.rest.annotation.Action.HttpMethod;
import org.meruvian.yama.actions.DefaultAction;

import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.validator.annotations.RequiredStringValidator;
import com.opensymphony.xwork2.validator.annotations.Validations;

@Action(name = "/ticket/department")
@Results({ @Result(name = DefaultAction.INPUT, type = "freemarker", location = "/view/ticket/department/department-form.ftl") })
public class DepartmentAction extends DefaultAction implements
		ModelDriven<DepartmentActionModel> {

	private static final long serialVersionUID = -5576509336668803798L;
	
	private ActionResult redirectToIndex = new ActionResult("redirect",
			"/ticket/department");
	
	@Inject
	private DepartmentService departmentService;

	private DepartmentActionModel model = new DepartmentActionModel();

	@Action
	public ActionResult deptList() {
		model.setDepartments(departmentService.findByKeyword(model.getQ(), null, "ASC", model.getMax(), model.getPage() - 1));
		return new ActionResult("freemarker",
				"/view/ticket/department/department-list.ftl");
	}
	
	@Action(name = "/add", method = HttpMethod.GET)
	public ActionResult addForm() {
		return new ActionResult("freemarker",
				"/view/ticket/department/department-form.ftl");
	}
	
	@Action(name = "/add", method = HttpMethod.POST)
	@Validations(requiredStrings = { @RequiredStringValidator(fieldName = "department.name", trim = true, key = "label.ticket.department.name.notnull") })
	public ActionResult addItemType() {
		departmentService.save(model.getDepartment());
		return redirectToIndex;
	}
	
	@Override
	public DepartmentActionModel getModel() {
		return model;
	}

}
