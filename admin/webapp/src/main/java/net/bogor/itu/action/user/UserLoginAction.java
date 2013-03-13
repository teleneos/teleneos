package net.bogor.itu.action.user;

import org.meruvian.inca.struts2.rest.ActionResult;
import org.meruvian.inca.struts2.rest.annotation.Action;
import org.meruvian.inca.struts2.rest.annotation.Action.HttpMethod;
import org.meruvian.yama.actions.DefaultAction;

/**
 * @author Dian Aditya
 * 
 */
@Action(name = "/user/signin")
public class UserLoginAction extends DefaultAction {
	@Action(method = HttpMethod.GET)
	public ActionResult signinForm() {
		return new ActionResult("/view/security/user/user-login-form.ftl");
	}
}
