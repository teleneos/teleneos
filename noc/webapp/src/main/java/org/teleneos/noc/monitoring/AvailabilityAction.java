/**
 * 
 */
package org.teleneos.noc.monitoring;

import javax.inject.Inject;

import org.meruvian.inca.struts2.rest.ActionResult;
import org.meruvian.inca.struts2.rest.annotation.Action;
import org.meruvian.yama.actions.DefaultAction;
import org.teleneos.log.availability.PingService;
import org.teleneos.log.network.host.HostService;
import org.teleneos.noc.telecentre.TelecentreService;

import com.opensymphony.xwork2.ModelDriven;

/**
 * @author Dian Aditya
 * 
 */
@Action(name = "/monitoring/availability")
public class AvailabilityAction extends DefaultAction implements
		ModelDriven<MonitoringActionModel> {

	@Inject
	private PingService pingService;

	@Inject
	private HostService hostService;

	@Inject
	private TelecentreService teleService;

	private MonitoringActionModel model = new MonitoringActionModel();

	@Action
	public ActionResult index() {
		model.setAvailabilities(pingService.findLatestStatus(model.getQ(),
				model.getMax(), model.getPage() - 1));

		return new ActionResult("freemarker",
				"/view/monitoring/availability.ftl");
	}

	@Action(name = "/clients/{q}")
	public ActionResult clientList() {
		model.setTele(teleService.findById(model.getQ()));
		model.setHosts(hostService.findLatestClientStatus(model.getQ(), 0, 0));

		return new ActionResult("freemarker",
				"/view/monitoring/availability-client.ftl");
	}

	@Override
	public MonitoringActionModel getModel() {
		return model;
	}

}
