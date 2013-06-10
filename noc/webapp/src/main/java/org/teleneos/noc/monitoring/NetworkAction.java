/**
 * 
 */
package org.teleneos.noc.monitoring;

import javax.inject.Inject;

import org.meruvian.inca.struts2.rest.ActionResult;
import org.meruvian.inca.struts2.rest.annotation.Action;
import org.meruvian.inca.struts2.rest.annotation.InterceptorRef;
import org.meruvian.inca.struts2.rest.annotation.Interceptors;
import org.meruvian.yama.actions.DefaultAction;
import org.teleneos.log.network.traffic.TrafficService;

import com.opensymphony.xwork2.ModelDriven;

/**
 * @author Dian Aditya
 * 
 */
@Action(name = "/monitoring/network")
public class NetworkAction extends DefaultAction implements
		ModelDriven<MonitoringActionModel> {

	@Inject
	private TrafficService trafficService;
	private MonitoringActionModel model = new MonitoringActionModel();

	@Action
	@Interceptors({ @InterceptorRef(name = "monitoringStack") })
	public ActionResult index() {
		return new ActionResult("freemarker", "/view/monitoring/network.ftl");
	}

	@Action(name = "/data")
	public ActionResult data() {
		model.setTelecentre((String) session
				.get(MonitoringInterceptor.CURRENT_TELE));

		model.setHistories1(trafficService.findLatestOutgoing(
				model.getTelecentre(), 60));
		model.setHistories2(trafficService.findLatestIncoming(
				model.getTelecentre(), 60));

		return new ActionResult("/blank.html");
	}

	@Override
	public MonitoringActionModel getModel() {
		return model;
	}

}
