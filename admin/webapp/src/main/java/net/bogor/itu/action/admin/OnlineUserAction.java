package net.bogor.itu.action.admin;

import javax.inject.Inject;

import net.bogor.itu.service.radius.RadacctService;

import org.meruvian.inca.struts2.rest.ActionResult;
import org.meruvian.inca.struts2.rest.annotation.Action;
import org.meruvian.yama.actions.DefaultAction;

import com.opensymphony.xwork2.ModelDriven;

/**
 * @author Dian Aditya
 * 
 */
@Action(name = "/admin/user")
public class OnlineUserAction extends DefaultAction implements
		ModelDriven<OnlineUserActionModel> {
	@Inject
	private RadacctService radacctService;
	private OnlineUserActionModel model = new OnlineUserActionModel();

	@Action(name = "/online")
	public ActionResult onlineStatus() {
		model.setAccts(radacctService.findOnlineUser(model.getQ(),
				model.getMax(), model.getPage() - 1));

		return new ActionResult("freemarker",
				"/view/admin/user/online-user-list.ftl");
	}

	@Override
	public OnlineUserActionModel getModel() {
		return model;
	}
}
