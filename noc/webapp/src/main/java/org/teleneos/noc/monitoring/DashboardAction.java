/**
 * 
 */
package org.teleneos.noc.monitoring;

import org.meruvian.inca.struts2.rest.ActionResult;
import org.meruvian.inca.struts2.rest.annotation.Action;
import org.meruvian.yama.actions.DefaultAction;

/**
 * @author Dian Aditya
 * 
 */
@Action(name = "/dashboard")
public class DashboardAction extends DefaultAction {

	@Action
	public ActionResult index() {
		return new ActionResult("freemarker", "/view/telecentre/map.ftl");
	}
}
