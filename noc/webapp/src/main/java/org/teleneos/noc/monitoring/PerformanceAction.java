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
import org.teleneos.log.network.performance.PerformanceService;

import com.opensymphony.xwork2.ModelDriven;

/**
 * @author Dian Aditya
 * 
 */
@Action(name = "/monitoring/performance")
public class PerformanceAction extends DefaultAction implements
		ModelDriven<MonitoringActionModel> {

	@Inject
	private PerformanceService performanceService;
	private MonitoringActionModel model = new MonitoringActionModel();

	@Action
	@Interceptors({ @InterceptorRef(name = "monitoringStack") })
	public ActionResult index() {
		return new ActionResult("freemarker",
				"/view/monitoring/performance.ftl");
	}

	@Action(name = "/data")
	public ActionResult data() {
		model.setTelecentre((String) session
				.get(MonitoringInterceptor.CURRENT_TELE));

		model.setHistories1(performanceService.findLatestPerformance(
				model.getTelecentre(), model.getMax()));

		return new ActionResult("/blank.html");
	}

	@Override
	public MonitoringActionModel getModel() {
		return model;
	}

}
