/**
 * 
 */
package net.bogor.itu.action.user;

import javax.inject.Inject;

import net.bogor.itu.action.admin.OnlineUserActionModel;
import net.bogor.itu.service.admin.UserService;
import net.bogor.itu.service.radius.RadacctService;

import org.meruvian.inca.struts2.rest.ActionResult;
import org.meruvian.inca.struts2.rest.annotation.Action;
import org.meruvian.yama.actions.DefaultAction;
import org.meruvian.yama.security.SessionCredentials;
import org.meruvian.yama.security.user.BackendUser;

import com.opensymphony.xwork2.ModelDriven;

/**
 * @author Dian Aditya
 * 
 */
@Action(name = "/user")
public class UserAction extends DefaultAction implements
		ModelDriven<OnlineUserActionModel> {
	private OnlineUserActionModel model = new OnlineUserActionModel();

	@Inject
	private RadacctService radacctService;

	@Inject
	private UserService userService;

	@Action
	public ActionResult statistic() throws Exception {
		BackendUser user = SessionCredentials.currentUser();
		model.setAccts(radacctService.findByUsername(user.getUsername(),
				model.getMax(), model.getPage() - 1));
		model.setUser(userService.findByUsername(user.getUsername()));
		model.setStatistic(radacctService.findStatistic(model.getQ()));

		return new ActionResult("freemarker",
				"/view/admin/user/user-usage-list.ftl");
	}

	@Override
	public OnlineUserActionModel getModel() {
		return model;
	}
}
