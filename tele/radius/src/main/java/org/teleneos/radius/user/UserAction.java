package org.teleneos.radius.user;

/**
 * 
 */

import javax.inject.Inject;

import org.meruvian.inca.struts2.rest.ActionResult;
import org.meruvian.inca.struts2.rest.annotation.Action;
import org.meruvian.yama.actions.DefaultAction;
import org.meruvian.yama.security.SessionCredentials;
import org.meruvian.yama.security.user.BackendUser;
import org.meruvian.yama.security.user.service.UserService;
import org.teleneos.radius.accounting.RadacctService;
import org.teleneos.radius.statistic.OnlineUserActionModel;
import org.teleneos.radius.userpackage.UserPackageService;

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

	@Inject
	private UserPackageService packageService;

	@Action
	public ActionResult index() throws Exception {
		return statistic();
	}

	@Action(name = "/history")
	public ActionResult statistic() throws Exception {
		BackendUser user = SessionCredentials.currentUser();
		model.setListacc(radacctService.findDetailByUsername(
				user.getUsername(), model.getMax(), model.getPage() - 1));

		model.setUser(userService.findByUsername(user.getUsername()));
		model.setStatistic(radacctService.findStatistic(user.getUsername()));

		return new ActionResult("freemarker", "/view/user/user-usage-list.ftl");
	}

	@Action(name = "/subscription")
	public ActionResult subscription() {
		model.setQ(SessionCredentials.currentUser().getUsername());

		model.setUserPackages(packageService.findPackageByUser(model.getQ(),
				model.getMax(), model.getPage() - 1));
		model.setUser(userService.findByUsername(model.getQ()));
		model.setStatistic(radacctService.findStatistic(model.getQ()));
		model.setUserPackage(packageService.findActivePackage(model.getQ()));

		return new ActionResult("freemarker",
				"/view/user/user-subscription-list.ftl");
	}

	@Override
	public OnlineUserActionModel getModel() {
		return model;
	}
}
