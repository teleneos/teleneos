package net.bogor.itu.action.user;

import org.meruvian.inca.struts2.rest.ActionResult;
import org.meruvian.inca.struts2.rest.annotation.Action;
import org.meruvian.inca.struts2.rest.annotation.Action.HttpMethod;
import org.meruvian.yama.actions.DefaultAction;

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
	public ActionResult signupSubmit() {
		
		
		return new ActionResult("freemarker",
				"/view/security/user/user-signup-success.ftl");
	}
}
