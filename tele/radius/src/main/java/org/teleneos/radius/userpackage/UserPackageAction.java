/**
 * 
 */
package org.teleneos.radius.userpackage;

import javax.inject.Inject;

import org.meruvian.inca.struts2.rest.ActionResult;
import org.meruvian.inca.struts2.rest.annotation.Action;
import org.meruvian.yama.actions.DefaultAction;
import org.meruvian.yama.security.user.service.UserService;
import org.teleneos.radius.accounting.RadacctService;

import com.opensymphony.xwork2.ModelDriven;

/**
 * @author Dian Aditya
 * 
 */
@Action(name = "/admin/user")
public class UserPackageAction extends DefaultAction implements
		ModelDriven<UserPackageActionModel> {
	private UserPackageActionModel model = new UserPackageActionModel();

	@Inject
	private UserPackageService packageService;

	@Inject
	private UserService userService;

	@Inject
	private RadacctService radacctService;

	@Action(name = "postpaid")
	public ActionResult postpaid() {
		model.setUserPackages(packageService.findPostpaidUser(model.getQ(), model.getMax(),
				model.getPage() - 1));
		return new ActionResult("freemarker", "/blank.ftl");
	}

	@Action(name = "/subscription/{q}")
	public ActionResult packages() {
		model.setUserPackages(packageService.findPackageByUser(model.getQ(),
				model.getMax(), model.getPage() - 1));
		model.setUser(userService.findByUsername(model.getQ()));
		model.setStatistic(radacctService.findStatistic(model.getQ()));
		model.setUserPackage(packageService.findActivePackage(model.getQ()));

		return new ActionResult("freemarker",
				"/view/admin/user/user-package-list.ftl");
	}

	@Override
	public UserPackageActionModel getModel() {
		return model;
	}

}
