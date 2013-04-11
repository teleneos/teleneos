/**
 * 
 */
package net.bogor.itu.action.packages;

import net.bogor.itu.action.master.PackageManagerActionModel;

import org.meruvian.inca.struts2.rest.ActionResult;
import org.meruvian.inca.struts2.rest.annotation.Action;
import org.meruvian.yama.actions.DefaultAction;

import com.opensymphony.xwork2.ModelDriven;

/**
 * @author Dian Aditya
 * 
 */
@Action(name = "/package")
public class PackageAction extends DefaultAction implements
		ModelDriven<PackageManagerActionModel> {

	private PackageManagerActionModel model = new PackageManagerActionModel();

	@Action(name = "/prepaid")
	public ActionResult prepaid() {
		return new ActionResult("/view/package/prepaid-form.ftl");
	}

	@Action(name = "/postpaid")
	public ActionResult postpaid() {
		return new ActionResult("/view/package/postpaid-form.ftl");
	}

	@Action(name = "/reward")
	public ActionResult reward() {
		return new ActionResult("/view/package/reward-form.ftl");
	}

	@Action(name = "/subscribe")
	public ActionResult subscribe() {
		return new ActionResult("/view/package/subscribe-form.ftl");
	}

	@Override
	public PackageManagerActionModel getModel() {
		return model;
	}
}
