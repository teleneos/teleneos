/**
 * 
 */
package org.teleneos.noc.monitoring;

import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

import org.meruvian.inca.struts2.rest.ActionResult;
import org.meruvian.inca.struts2.rest.annotation.Action;
import org.meruvian.inca.struts2.rest.annotation.InterceptorRef;
import org.meruvian.inca.struts2.rest.annotation.Interceptors;
import org.meruvian.yama.actions.DefaultAction;
import org.teleneos.log.network.cpu.CpuLoadService;
import org.teleneos.log.network.disk.DiskSpace;
import org.teleneos.log.network.disk.DiskSpaceService;

import com.opensymphony.xwork2.ModelDriven;

/**
 * @author Dian Aditya
 * 
 */
@Action(name = "/monitoring/utilization")
public class UtilizationAction extends DefaultAction implements
		ModelDriven<MonitoringActionModel> {

	@Inject
	private DiskSpaceService diskService;

	@Inject
	private CpuLoadService cpuService;

	private MonitoringActionModel model = new MonitoringActionModel();

	@Action
	@Interceptors({ @InterceptorRef(name = "monitoringStack") })
	public ActionResult index() {
		return new ActionResult("freemarker",
				"/view/monitoring/utilization.ftl");
	}

	@Action(name = "/data")
	public ActionResult data() {
		List<DiskSpace> spaces = model.getDiskSpaces();
		String telecentre = (String) session
				.get(MonitoringInterceptor.CURRENT_TELE);

		spaces.addAll(Arrays.asList(diskService.findDiskSpace(telecentre)));
		spaces.addAll(Arrays.asList(diskService.findSwapSpace(telecentre)));

		model.setHistories1(cpuService.findLatestCpuLoad(telecentre,
				model.getMax()));

		return new ActionResult("/blank.html");
	}

	@Override
	public MonitoringActionModel getModel() {
		return model;
	}

}
