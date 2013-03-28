package net.bogor.itu.action.admin;

import java.io.IOException;

import javax.inject.Inject;

import net.bogor.itu.radius.RadiusSerivce;
import net.bogor.itu.service.admin.UserService;
import net.bogor.itu.service.radius.RadacctService;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
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
	private static final Log LOG = LogFactory.getLog(OnlineUserAction.class);

	@Inject
	private RadacctService radacctService;

	@Inject
	private UserService userService;

	@Inject
	private RadiusSerivce radiusSerivce;

	private OnlineUserActionModel model = new OnlineUserActionModel();

	@Action
	public ActionResult index() {
		return new ActionResult("redirect", "/admin/user/list");
	}

	@Action(name = "/online")
	public ActionResult onlineStatus() {
		model.setAccts(radacctService.findOnlineUser(model.getQ(),
				model.getMax(), model.getPage() - 1));

		return new ActionResult("freemarker",
				"/view/admin/user/online-user-list.ftl");
	}

	@Action(name = "report/{uid}")
	public ActionResult userReport() {
		model.setAccts(radacctService.findByUsername(model.getUid(),
				model.getMax(), model.getPage() - 1));
		model.setUser(userService.findByUsername(model.getUid()));
		model.setStatistic(radacctService.findStatistic(model.getUid()));
		
		return new ActionResult("freemarker",
				"/view/admin/user/user-usage-report-list.ftl");
	}

	@Action(name = "/disconnect/{q}")
	public ActionResult disconnectUser() throws IOException {
		try {
			radiusSerivce.logout(model.getQ());
		} catch (Exception e) {
			LOG.error("Failed stopping connection", e);
		}

		return new ActionResult("redirect", "/admin/user/online");
	}

	@Override
	public OnlineUserActionModel getModel() {
		return model;
	}
}
