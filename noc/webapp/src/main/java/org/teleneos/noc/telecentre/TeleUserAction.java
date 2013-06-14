/**
 * 
 */
package org.teleneos.noc.telecentre;

import javax.inject.Inject;

import org.meruvian.inca.struts2.rest.ActionResult;
import org.meruvian.inca.struts2.rest.annotation.Action;
import org.meruvian.inca.struts2.rest.annotation.InterceptorRef;
import org.meruvian.inca.struts2.rest.annotation.Interceptors;
import org.meruvian.yama.actions.DefaultAction;
import org.meruvian.yama.security.user.service.UserService;
import org.teleneos.noc.monitoring.MonitoringInterceptor;

import com.opensymphony.xwork2.ModelDriven;

/**
 * @author Dian Aditya
 * 
 */
@Action(name = "/tele/user")
public class TeleUserAction extends DefaultAction implements
		ModelDriven<TeleUserActionModel> {

	@Inject
	private UserService userService;
	private TeleUserActionModel model = new TeleUserActionModel();

	@Action
	@Interceptors({ @InterceptorRef(name = "monitoringStack") })
	public ActionResult index() {
		String telecentre = (String) session
				.get(MonitoringInterceptor.CURRENT_TELE);
		if (telecentre != null) {
			try {
				model.setUsers(userService.findByTelecentre(telecentre, "", 0,
						0));
			} catch (Exception e) {
				// do nothing
			}
		}

		return new ActionResult("freemarker", "/view/telecentre/user-list.ftl");
	}

	@Override
	public TeleUserActionModel getModel() {
		return model;
	}
}
