package net.bogor.itu.action.admin;

import java.io.IOException;
import java.io.StringWriter;

import javax.inject.Inject;

import net.bogor.itu.service.radius.RadacctService;

import org.apache.commons.io.IOUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.meruvian.inca.struts2.rest.ActionResult;
import org.meruvian.inca.struts2.rest.annotation.Action;
import org.meruvian.yama.actions.DefaultAction;
import org.springframework.beans.factory.annotation.Value;

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
	private OnlineUserActionModel model = new OnlineUserActionModel();

	@Value("${radius.client.remote_intet_address}")
	private String radiusInetAddress;

	@Value("${radius.client.shared_secret}")
	private String radiusSharedSecret;

	@Value("${radius.server.coa_port}")
	private String radiusCoAPort;

	@Value("${radius.client.disconnect_shell_location}")
	private String disconnectSh;

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

	@Action(name = "/disconnect/{q}")
	public ActionResult disconnectUser() throws IOException {
		try {
			ProcessBuilder builder = new ProcessBuilder(disconnectSh,
					model.getQ(), radiusInetAddress, radiusCoAPort,
					radiusSharedSecret);
			Process process = builder.start();
			StringWriter writer = new StringWriter();
			IOUtils.copy(process.getInputStream(), writer);

			LOG.info(writer.toString());
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
