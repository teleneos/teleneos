/**
 * 
 */
package net.bogor.itu.action.network;

import javax.inject.Inject;

import org.meruvian.inca.struts2.rest.ActionResult;
import org.meruvian.inca.struts2.rest.annotation.Action;
import org.meruvian.yama.actions.DefaultAction;
import org.teleneos.networking.zabbix.service.AvailabilityService;

import com.opensymphony.xwork2.ModelDriven;

/**
 * @author Dian Aditya
 * 
 */
@Action(name = "/network/availability")
public class AvailabilityAction extends DefaultAction implements
		ModelDriven<NetworkActionModel> {

	private NetworkActionModel model = new NetworkActionModel();

	@Inject
	private AvailabilityService availabilityService;

	@Action
	public ActionResult index() {
		model.setHosts(availabilityService.findAllHosts(0));

		return new ActionResult("freemarker", "/view/network/availability.ftl");
	}

	@Override
	public NetworkActionModel getModel() {
		return model;
	}
}
