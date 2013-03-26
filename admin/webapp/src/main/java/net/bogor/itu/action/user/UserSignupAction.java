package net.bogor.itu.action.user;

import org.meruvian.inca.struts2.rest.ActionResult;
import org.meruvian.inca.struts2.rest.annotation.Action;
import org.meruvian.inca.struts2.rest.annotation.Action.HttpMethod;
import org.meruvian.yama.actions.DefaultAction;

import com.opensymphony.xwork2.validator.annotations.EmailValidator;
import com.opensymphony.xwork2.validator.annotations.RequiredStringValidator;
import com.opensymphony.xwork2.validator.annotations.Validations;

/**
 * @author Dian Aditya
 * 
 */
@Action(name = "/user/signup")
public class UserSignupAction extends DefaultAction {
	@Action(method = HttpMethod.GET)
	public ActionResult signupForm() {
		return new ActionResult("/view/security/user/user-signup-form.ftl");
	}

	@Action(method = HttpMethod.POST)
	@Validations(requiredStrings = {
			@RequiredStringValidator(fieldName = "user.user.username", trim = true, key = "message.admin.user.username.notnull"),
			@RequiredStringValidator(fieldName = "user.user.password", trim = true, key = "message.admin.user.password.notnull"),
			@RequiredStringValidator(fieldName = "user.name.first", trim = true, key = "message.admin.user.firstname.notnull"),
			@RequiredStringValidator(fieldName = "user.user.email", trim = true, key = "message.admin.user.email.notnull") }, emails = { @EmailValidator(fieldName = "user.user.email", key = "message.admin.user.email.notvalid") })
	public ActionResult signupSubmit() {
		
		
		return new ActionResult("freemarker",
				"/view/security/user/user-signup-success.ftl");
	}
}
