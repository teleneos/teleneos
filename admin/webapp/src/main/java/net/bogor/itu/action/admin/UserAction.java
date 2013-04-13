/**
 * 
 */
package net.bogor.itu.action.admin;

import javax.inject.Inject;

import net.bogor.itu.service.admin.InternetPackageService;
import net.bogor.itu.service.admin.UserService;

import org.meruvian.inca.struts2.rest.ActionResult;
import org.meruvian.inca.struts2.rest.annotation.Action;
import org.meruvian.inca.struts2.rest.annotation.Action.HttpMethod;
import org.meruvian.inca.struts2.rest.annotation.Result;
import org.meruvian.inca.struts2.rest.annotation.Results;
import org.meruvian.yama.actions.DefaultAction;

import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.validator.annotations.EmailValidator;
import com.opensymphony.xwork2.validator.annotations.RegexFieldValidator;
import com.opensymphony.xwork2.validator.annotations.RequiredFieldValidator;
import com.opensymphony.xwork2.validator.annotations.RequiredStringValidator;
import com.opensymphony.xwork2.validator.annotations.StringLengthFieldValidator;
import com.opensymphony.xwork2.validator.annotations.Validations;

/**
 * @author Dian Aditya
 * 
 */
@Action(name = "/admin/user")
@Results({ @Result(name = DefaultAction.INPUT, type = "freemarker", location = "/view/admin/user/user-form.ftl") })
public class UserAction extends DefaultAction implements
		ModelDriven<UserActionModel> {

	private static final long serialVersionUID = 2413426101731088386L;

	private UserActionModel model = new UserActionModel();

	@Inject
	private UserService userService;

	@Inject
	private InternetPackageService packageService;

	@Action
	public ActionResult index() {
		return new ActionResult("redirect", "/admin/user/list");
	}

	@Action(name = "/list")
	public ActionResult userList() {
		model.setDetails(userService.findDetailByUsername(model.getQ(),
				model.getMax(), model.getPage() - 1));

		return new ActionResult("freemarker", "/view/admin/user/user-list.ftl");
	}

	@Action(name = "/list/nodetail")
	public ActionResult users() {
		model.setUsers(userService.findByUsername(model.getQ(), model.getMax(),
				model.getPage() - 1));

		return new ActionResult("/blank.html");
	}

	@Action(name = "/packages", method = HttpMethod.GET)
	public ActionResult getPackage() {
		model.setPackages(packageService.getAll(model.getQ(), model.getMax(),
				model.getPage() - 1));
		return new ActionResult("/blank.html");
	}

	@Action(name = "/add", method = HttpMethod.GET)
	public ActionResult userForm() {
		return new ActionResult("freemarker", "/view/admin/user/user-form.ftl");
	}

	@Action(name = "/add", method = HttpMethod.POST)
	@Validations(requiredStrings = {
			@RequiredStringValidator(fieldName = "user.user.username", trim = true, key = "message.admin.user.username.notnull"),
			@RequiredStringValidator(fieldName = "pass", trim = true, key = "message.admin.user.password.notnull"),
			@RequiredStringValidator(fieldName = "user.user.password", trim = true, key = "message.admin.user.password.notnull"),
			@RequiredStringValidator(fieldName = "user.name.first", trim = true, key = "message.admin.user.firstname.notnull"),
			@RequiredStringValidator(fieldName = "user.idcard", trim = true, key = "message.admin.user.idcard.notnull"),
			@RequiredStringValidator(fieldName = "user.address.street1", trim = true, key = "message.admin.user.street1.notnull"),
			@RequiredStringValidator(fieldName = "user.user.email", trim = true, key = "message.admin.user.email.notnull") }, regexFields = {
			@RegexFieldValidator(fieldName = "user.user.username", expression = "^[a-z][a-z0-9]+(?:[_][a-z0-9]+)*$", key = "message.admin.user.username.invalidcharacters"),
			@RegexFieldValidator(fieldName = "user.idcard", expression = "^([0-9]*)$", key = "message.admin.user.idcard.length") }, emails = { @EmailValidator(fieldName = "user.user.email", key = "message.admin.user.email.notvalid") }, requiredFields = {
			@RequiredFieldValidator(fieldName = "user.user.logInformation.statusFlag", key = "message.admin.user.flag.notnull"),
			@RequiredFieldValidator(fieldName = "user.birthDate", key = "message.admin.user.birthdate.notvalid") }, stringLengthFields = {
			@StringLengthFieldValidator(fieldName = "user.idcard", key = "message.admin.user.idcard.length", minLength = "13", maxLength = "13", trim = true),
			@StringLengthFieldValidator(fieldName = "user.user.password", key = "message.admin.user.password.length", minLength = "6") })
	public ActionResult userSubmit() {
		model.getUser().setInternetPackage(null);
		model.getUser().getUser().setRole("USER");
		userService.save(model.getUser());

		return new ActionResult("/pos/transaction/addstarter?id="
				+ model.getUser().getId()).setType("redirect");
	}

	@Action(name = "/edit/{q}", method = HttpMethod.GET)
	public ActionResult userEditForm() {
		model.setUser(userService.findByUsername(model.getQ()));

		return new ActionResult("freemarker", "/view/admin/user/user-form.ftl");
	}

	@Action(name = "/edit/{q}", method = HttpMethod.POST)
	@Validations(requiredStrings = {
			@RequiredStringValidator(fieldName = "user.user.username", trim = true, key = "message.admin.user.username.notnull"),
			@RequiredStringValidator(fieldName = "pass", trim = true, key = "message.admin.user.password.notnull"),
			@RequiredStringValidator(fieldName = "user.user.password", trim = true, key = "message.admin.user.password.notnull"),
			@RequiredStringValidator(fieldName = "user.name.first", trim = true, key = "message.admin.user.firstname.notnull"),
			@RequiredStringValidator(fieldName = "user.idcard", trim = true, key = "message.admin.user.idcard.notnull"),
			@RequiredStringValidator(fieldName = "user.address.street1", trim = true, key = "message.admin.user.street1.notnull"),
			@RequiredStringValidator(fieldName = "user.user.email", trim = true, key = "message.admin.user.email.notnull") }, regexFields = {
			@RegexFieldValidator(fieldName = "user.user.username", expression = "^[a-z][a-z0-9]+(?:[_][a-z0-9]+)*$", key = "message.admin.user.username.invalidcharacters"),
			@RegexFieldValidator(fieldName = "user.idcard", expression = "^([0-9]*)$", key = "message.admin.user.idcard.length") }, emails = { @EmailValidator(fieldName = "user.user.email", key = "message.admin.user.email.notvalid") }, requiredFields = {
			@RequiredFieldValidator(fieldName = "user.user.logInformation.statusFlag", key = "message.admin.user.flag.notnull"),
			@RequiredFieldValidator(fieldName = "user.birthDate", key = "message.admin.user.birthdate.notvalid") }, stringLengthFields = {
			@StringLengthFieldValidator(fieldName = "user.idcard", key = "message.admin.user.idcard.length", minLength = "13", maxLength = "13", trim = true),
			@StringLengthFieldValidator(fieldName = "user.user.password", key = "message.admin.user.password.length", minLength = "6") })
	public ActionResult userEditSubmit() {
		userSubmit();

		return new ActionResult("redirect", "/admin/user/edit/" + model.getQ()
				+ "?success");
	}

	@Override
	public UserActionModel getModel() {
		return model;
	}

}
