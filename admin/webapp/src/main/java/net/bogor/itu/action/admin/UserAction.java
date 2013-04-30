/**
 * 
 */
package net.bogor.itu.action.admin;

import javax.inject.Inject;

import net.bogor.itu.service.admin.InternetPackageService;
import net.bogor.itu.service.admin.UserService;

import org.apache.commons.lang.StringUtils;
import org.meruvian.inca.struts2.rest.ActionResult;
import org.meruvian.inca.struts2.rest.annotation.Action;
import org.meruvian.inca.struts2.rest.annotation.Action.HttpMethod;
import org.meruvian.inca.struts2.rest.annotation.Result;
import org.meruvian.inca.struts2.rest.annotation.Results;
import org.meruvian.yama.actions.DefaultAction;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.ldap.NameAlreadyBoundException;

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
	public ActionResult userList() throws Exception {
		users();

		return new ActionResult("freemarker", "/view/admin/user/user-list.ftl");
	}

	@Action(name = "/list/nodetail")
	public ActionResult users() throws Exception {
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
			@RegexFieldValidator(fieldName = "user.idcard", expression = "^([0-9]*)$", key = "message.admin.user.idcard.length"),
			@RegexFieldValidator(fieldName = "user.phone", expression = "^([0-9]*)$", key = "message.admin.user.phone.notvalid") }, emails = { @EmailValidator(fieldName = "user.user.email", key = "message.admin.user.email.notvalid") }, requiredFields = {
			@RequiredFieldValidator(fieldName = "user.logInformation.statusFlag", key = "message.admin.user.flag.notnull"),
			@RequiredFieldValidator(fieldName = "user.birthDate", key = "message.admin.user.birthdate.notvalid") }, stringLengthFields = {
			@StringLengthFieldValidator(fieldName = "user.idcard", key = "message.admin.user.idcard.length", minLength = "13", maxLength = "13", trim = true),
			@StringLengthFieldValidator(fieldName = "user.user.password", key = "message.admin.user.password.length", minLength = "6") })
	public ActionResult userSubmit() throws Exception {
		boolean isCreate = StringUtils.isBlank(model.getUser().getId());

		model.getUser().getUser().setRole("USER");

		try {
			userService.save(model.getUser());
		} catch (DataIntegrityViolationException e) {
			if (isCreate) {
				model.getUser().setId(null);
				model.getUser().getUser().setPassword(null);
			}

			if (e.getMessage().contains("username"))
				addFieldError("user.user.username",
						getText("message.admin.user.username.inuse"));

			if (e.getMessage().contains("email"))
				addFieldError("user.user.email",
						getText("message.admin.user.email.inuse"));

			return new ActionResult("freemarker",
					"/view/admin/user/user-form.ftl");
		} catch (NameAlreadyBoundException e) {
			model.getUser().getUser().setPassword(null);

			addFieldError("user.user.username",
					getText("message.admin.user.username.inuse"));

			return new ActionResult("freemarker",
					"/view/admin/user/user-form.ftl");
		}

		return new ActionResult("/pos/transaction/addstarter?id="
				+ model.getUser().getUser().getUsername()).setType("redirect");
	}

	@Action(name = "/edit/{q}", method = HttpMethod.GET)
	public ActionResult userEditForm() throws Exception {
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
			@RegexFieldValidator(fieldName = "user.idcard", expression = "^([0-9]*)$", key = "message.admin.user.idcard.length"),
			@RegexFieldValidator(fieldName = "user.phone", expression = "^([0-9]*)$", key = "message.admin.user.phone.notvalid") }, emails = { @EmailValidator(fieldName = "user.user.email", key = "message.admin.user.email.notvalid") }, requiredFields = {
			@RequiredFieldValidator(fieldName = "user.logInformation.statusFlag", key = "message.admin.user.flag.notnull"),
			@RequiredFieldValidator(fieldName = "user.birthDate", key = "message.admin.user.birthdate.notvalid") }, stringLengthFields = {
			@StringLengthFieldValidator(fieldName = "user.idcard", key = "message.admin.user.idcard.length", minLength = "13", maxLength = "13", trim = true),
			@StringLengthFieldValidator(fieldName = "user.user.password", key = "message.admin.user.password.length", minLength = "6") })
	public ActionResult userEditSubmit() throws Exception {
		ActionResult result = userSubmit();
		if (result.getType().equals("freemarker"))
			return result;

		return new ActionResult("redirect", "/admin/user/edit/" + model.getQ()
				+ "?success");
	}

	@Override
	public UserActionModel getModel() {
		return model;
	}

}
