/**
 * 
 */
package net.bogor.itu.action.admin;

import javax.inject.Inject;

import net.bogor.itu.service.admin.UserService;

import org.meruvian.inca.struts2.rest.ActionResult;
import org.meruvian.inca.struts2.rest.annotation.Action;
import org.meruvian.inca.struts2.rest.annotation.Action.HttpMethod;
import org.meruvian.inca.struts2.rest.annotation.Result;
import org.meruvian.inca.struts2.rest.annotation.Results;
import org.meruvian.yama.actions.DefaultAction;

import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.validator.annotations.EmailValidator;
import com.opensymphony.xwork2.validator.annotations.RequiredStringValidator;
import com.opensymphony.xwork2.validator.annotations.Validations;

/**
 * @author Dian Aditya
 * 
 */
@Action(name = "/admin/user")
@Results({ @Result(name = DefaultAction.INPUT, type = "freemarker", location = "/view/admin/user/user-form.ftl") })
public class UserAction extends DefaultAction implements
		ModelDriven<UserActionModel> {

	private UserActionModel model = new UserActionModel();

	@Inject
	private UserService userService;

	@Action
	public ActionResult index() {
		return new ActionResult("redirect", "/admin/user/list");
	}

	@Action(name = "/list")
	public ActionResult userList() {
		model.setUsers(userService.findByUsername(model.getQ(), model.getMax(),
				model.getPage() - 1));

		return new ActionResult("freemarker", "/view/admin/user/user-list.ftl");
	}

	@Action(name = "/add", method = HttpMethod.GET)
	public ActionResult userForm() {
		return new ActionResult("freemarker", "/view/admin/user/user-form.ftl");
	}

	@Action(name = "/add", method = HttpMethod.POST)
	@Validations(requiredStrings = {
			@RequiredStringValidator(fieldName = "user.user.username", trim = true, key = "message.admin.user.username.notnull"),
			@RequiredStringValidator(fieldName = "user.user.password", trim = true, key = "message.admin.user.password.notnull"),
			@RequiredStringValidator(fieldName = "user.name.first", trim = true, key = "message.admin.user.firstname.notnull"),
			@RequiredStringValidator(fieldName = "user.user.email", trim = true, key = "message.admin.user.email.notnull") }, emails = { @EmailValidator(fieldName = "user.user.email", key = "message.admin.user.email.notvalid") })
	public ActionResult userSubmit() {
		userService.save(model.getUser());

		return new ActionResult("redirect", "/admin/user");
	}

	@Action(name = "/edit/{q}", method = HttpMethod.GET)
	public ActionResult userEditForm() {
		model.setUser(userService.findByUsername(model.getQ()));

		return new ActionResult("freemarker", "/view/admin/user/user-form.ftl");
	}

	@Action(name = "/edit/{q}", method = HttpMethod.POST)
	@Validations(requiredStrings = {
			@RequiredStringValidator(fieldName = "user.user.username", trim = true, key = "message.admin.user.username.notnull"),
			@RequiredStringValidator(fieldName = "user.user.password", trim = true, key = "message.admin.user.password.notnull"),
			@RequiredStringValidator(fieldName = "user.name.first", trim = true, key = "message.admin.user.firstname.notnull"),
			@RequiredStringValidator(fieldName = "user.user.email", trim = true, key = "message.admin.user.email.notnull") }, emails = { @EmailValidator(fieldName = "user.user.email", key = "message.admin.user.email.notvalid") })
	public ActionResult userEditSubmit() {
		userSubmit();

		return new ActionResult("redirect", "/admin/user");
	}

	@Override
	public UserActionModel getModel() {
		return model;
	}
}
