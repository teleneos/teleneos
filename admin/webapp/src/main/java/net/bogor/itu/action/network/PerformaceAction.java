/**
 * 
 */
package net.bogor.itu.action.network;

import javax.inject.Inject;

import org.meruvian.inca.struts2.rest.ActionResult;
import org.meruvian.inca.struts2.rest.annotation.Action;
import org.meruvian.yama.actions.DefaultAction;
import org.teleneos.networking.zabbix.service.PerformanceService;

import com.opensymphony.xwork2.ModelDriven;

/**
 * @author Dian Aditya
 * 
 */
@Action(name = "/network/performance")
public class PerformaceAction extends DefaultAction implements
		ModelDriven<NetworkActionModel> {

	private NetworkActionModel model = new NetworkActionModel();

	@Inject
	private PerformanceService performanceService;

	@Action
	public ActionResult index() {
		return new ActionResult("freemarker", "/view/network/performance.ftl");
	}

	@Action(name = "/data")
	public ActionResult data() {
		model.setHistory(performanceService.findServerPerformance(60));

		return new ActionResult("/blank.html");
	}

	@Override
	public NetworkActionModel getModel() {
		return model;
	}

}
