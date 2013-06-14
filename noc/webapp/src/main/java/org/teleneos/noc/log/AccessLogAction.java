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
import org.teleneos.log.access.AccessLogService;
import org.teleneos.noc.monitoring.MonitoringInterceptor;

import com.opensymphony.xwork2.ModelDriven;

/**
 * @author Dian Aditya
 * 
 */
@Action(name = "/log/access")
public class AccessLogAction extends DefaultAction implements
		ModelDriven<LogActionModel> {

	@Inject
	private AccessLogService logService;
	private LogActionModel model = new LogActionModel();

	@Action
	@Interceptors({ @InterceptorRef(name = "monitoringStack") })
	public ActionResult index() throws Exception {
		model.setTelecentre((String) session
				.get(MonitoringInterceptor.CURRENT_TELE));

		model.setLogs(logService.findAll(model.getTelecentre(),
				model.getUser(), model.getQ(), model.getFrom(), model.getTo(),
				model.getOrder(), model.getMax(), model.getPage() - 1));

		return new ActionResult("freemarker", "/view/log/access.ftl");
	}

	@Action(name = "/data")
	public ActionResult data() throws Exception {

		return new ActionResult("/blank.html");
	}

	@Override
	public LogActionModel getModel() {
		return model;
	}
}
