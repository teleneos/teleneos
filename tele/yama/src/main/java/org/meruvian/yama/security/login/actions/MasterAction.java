package org.meruvian.yama.security.login.actions;

import org.meruvian.inca.struts2.rest.ActionResult;
import org.meruvian.inca.struts2.rest.annotation.Action;
import org.meruvian.inca.struts2.rest.annotation.Action.HttpMethod;

@Action(name = "/master")
public class MasterAction {

	@Action(method = HttpMethod.GET)
	public ActionResult loginform() throws Exception {

		return new ActionResult("freemarker", "/view/admin/master-index.ftl");

	}
}
