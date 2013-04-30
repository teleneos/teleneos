/**
 * 
 */
package net.bogor.itu.action.admin;

import javax.inject.Inject;

import net.bogor.itu.service.admin.UserService;
import net.bogor.itu.service.radius.RadacctService;
import net.bogor.itu.service.radius.UserPackageService;

import org.meruvian.inca.struts2.rest.ActionResult;
import org.meruvian.inca.struts2.rest.annotation.Action;
import org.meruvian.yama.actions.DefaultAction;

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
