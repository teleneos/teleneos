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
	public ActionResult index() {
		return new ActionResult("freemarker", "/view/admin/backend-index.ftl");
	}

	@Action(name = "/statistic")
	public ActionResult statistic() {
		model.setAccts(radacctService.findByUsername(model.getQ(),
				model.getMax(), model.getPage() - 1));
		model.setUser(userService.findByUsername(model.getQ()));
		model.setStatistic(radacctService.findStatistic(model.getQ()));

		return new ActionResult("freemarker",
				"/view/admin/user/user-usage-report-list.ftl");
	}

	@Override
	public OnlineUserActionModel getModel() {
		return model;
	}
}
