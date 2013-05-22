package org.teleneos.networking.zabbix.utilization;

/**
 * 
 */

import java.io.IOException;
import java.util.List;

import javax.inject.Inject;

import org.meruvian.inca.struts2.rest.ActionResult;
import org.meruvian.inca.struts2.rest.annotation.Action;
import org.meruvian.yama.actions.DefaultAction;
import org.teleneos.networking.zabbix.NetworkActionModel;
import org.teleneos.networking.zabbix.item.ZItem;

import com.opensymphony.xwork2.ModelDriven;

/**
 * @author Dian Aditya
 * 
 */
@Action(name = "/network/utilization")
public class UtilizationAction extends DefaultAction implements
		ModelDriven<NetworkActionModel> {

	private NetworkActionModel model = new NetworkActionModel();

	@Inject
	private UtilizationService utilizationService;

	@Action
	public ActionResult index() {
		return new ActionResult("freemarker", "/view/network/utilization.ftl");
	}

	@Action(name = "/processor")
	public ActionResult processor() {
		model.setHistory(utilizationService.findCPUHistory(60));

		return new ActionResult("/blank.html");
	}

	@Action(name = "/swap")
	public ActionResult swap() throws IOException {
		List<ZItem> items = model.getItems();
		items.add(utilizationService.getFreeSwapSpace());
		items.add(utilizationService.getTotalSwapSpace());

		return new ActionResult("/blank.html");
	}

	@Action(name = "/disk")
	public ActionResult disk() throws IOException {
		List<ZItem> items = model.getItems();
		items.add(utilizationService.getFreeDiskSpace());
		items.add(utilizationService.getUsedDiskSpace());
		items.add(utilizationService.getTotalDiskSpace());

		return new ActionResult("/blank.html");
	}

	@Action(name = "items")
	public ActionResult items() throws IOException {
		model.setItems(utilizationService.getItems().getResult());

		return new ActionResult("/blank.html");
	}

	@Override
	public NetworkActionModel getModel() {
		return model;
	}

}
