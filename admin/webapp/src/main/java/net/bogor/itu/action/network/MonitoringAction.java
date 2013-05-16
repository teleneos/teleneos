/**
 * 
 */
package net.bogor.itu.action.network;

import javax.inject.Inject;

import org.meruvian.inca.struts2.rest.ActionResult;
import org.meruvian.inca.struts2.rest.annotation.Action;
import org.meruvian.yama.actions.DefaultAction;
import org.teleneos.networking.zabbix.service.MonitoringService;

import com.opensymphony.xwork2.ModelDriven;

/**
 * @author Dian Aditya
 * 
 */
@Action(name = "/network/monitoring")
public class MonitoringAction extends DefaultAction implements
		ModelDriven<NetworkActionModel> {

	private NetworkActionModel model = new NetworkActionModel();

	@Inject
	private MonitoringService monitoringService;

	@Action
	public ActionResult index() {
		return new ActionResult("freemarker", "/view/network/monitoring.ftl");
	}

	@Action(name = "/traffic")
	public ActionResult incomingTraffic() {
		model.getHistories().add(monitoringService.findIncomingTraffic(60));
		model.getHistories().add(monitoringService.findOutgoingTraffic(60));

		return new ActionResult("/blank.html");
	}

	@Override
	public NetworkActionModel getModel() {
		return model;
	}

}
