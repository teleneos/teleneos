package net.bogor.itu.action.admin;

import javax.inject.Inject;

import net.bogor.itu.service.admin.GroupService;

import org.meruvian.inca.struts2.rest.ActionResult;
import org.meruvian.inca.struts2.rest.annotation.Action;
import org.meruvian.inca.struts2.rest.annotation.Action.HttpMethod;
import org.meruvian.inca.struts2.rest.annotation.Result;
import org.meruvian.inca.struts2.rest.annotation.Results;
import org.meruvian.yama.actions.DefaultAction;

import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.validator.annotations.RequiredStringValidator;
import com.opensymphony.xwork2.validator.annotations.Validations;

/**
 * @author Dian Aditya
 * 
 */
@Action(name = "/admin/user/group")
@Results({ @Result(name = DefaultAction.INPUT, type = "freemarker", location = "/view/admin/group/group-form.ftl") })
public class GroupAction extends DefaultAction implements
		ModelDriven<GroupActionModel> {
	private GroupActionModel model = new GroupActionModel();

	private ActionResult redirectToIndex = new ActionResult("redirect",
			"/admin/user/group");
	private ActionResult groupForm = new ActionResult("freemarker",
			"/view/admin/group/group-form.ftl");

	@Inject
	private GroupService service;

	@Action
	public ActionResult groupList() {
		model.setGroups(service.findByKeyword(model.getQ(), model.getMax(),
				model.getPage() - 1));

		return new ActionResult("freemarker",
				"/view/admin/group/group-list.ftl");
	}

	@Action(name = "/add", method = HttpMethod.GET)
	public ActionResult addForm() {
		return groupForm;
	}

	@Action(name = "/edit/{group.id}", method = HttpMethod.GET)
	public ActionResult editForm() {
		String id = model.getGroup().getId();
		if (id == null)
			return redirectToIndex;

		model.setGroup(service.findById(id));
		if (model.getGroup() == null)
			return redirectToIndex;

		return groupForm;
	}

	@Action(name = "/edit/{group.id}", method = HttpMethod.POST)
	@Validations(requiredStrings = { @RequiredStringValidator(fieldName = "group.name", trim = true, key = "message.admin.group.name.notnull") })
	public ActionResult updateGroup() {
		return addGroup();
	}

	@Action(name = "/add", method = HttpMethod.POST)
	@Validations(requiredStrings = { @RequiredStringValidator(fieldName = "group.name", trim = true, key = "message.admin.group.name.notnull") })
	public ActionResult addGroup() {
		service.save(model.getGroup());

		return redirectToIndex;
	}

	@Override
	public GroupActionModel getModel() {
		return model;
	}
}