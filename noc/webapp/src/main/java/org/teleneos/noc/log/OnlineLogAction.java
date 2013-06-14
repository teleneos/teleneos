/**
 * 
 */
package org.teleneos.noc.log;

import javax.inject.Inject;

import org.meruvian.inca.struts2.rest.ActionResult;
import org.meruvian.inca.struts2.rest.annotation.Action;
import org.meruvian.inca.struts2.rest.annotation.InterceptorRef;
import org.meruvian.inca.struts2.rest.annotation.Interceptors;
import org.meruvian.yama.actions.DefaultAction;
import org.teleneos.log.radius.accounting.RadiusAccountingService;
import org.teleneos.noc.monitoring.MonitoringInterceptor;

import com.opensymphony.xwork2.ModelDriven;

/**
 * @author Dian Aditya
 * 
 */
@Action(name = "/log/online")
public class OnlineLogAction extends DefaultAction implements
		ModelDriven<LogActionModel> {

	@Inject
	private RadiusAccountingService acctService;
	private LogActionModel model = new LogActionModel();

	@Action
	@Interceptors({ @InterceptorRef(name = "monitoringStack") })
	public ActionResult index() {
		model.setTelecentre((String) session
				.get(MonitoringInterceptor.CURRENT_TELE));

		model.setAccts(acctService.findAll(model.getTelecentre(),
				model.getUser(), model.getFrom(), model.getTo(),
				model.getOrder(), model.getMax(), model.getPage() - 1));

		return new ActionResult("freemarker", "/view/log/online.ftl");
	}

	@Action(name = "/data")
	public ActionResult data() {

		return new ActionResult("/blank.html");
	}

	@Override
	public LogActionModel getModel() {
		return model;
	}
}
