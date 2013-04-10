package id.co.bonet.itu.noc.action;

import id.co.bonet.itu.noc.service.TelecentreService;

import javax.inject.Inject;

import org.meruvian.inca.struts2.rest.ActionResult;
import org.meruvian.inca.struts2.rest.annotation.Action;
import org.meruvian.inca.struts2.rest.annotation.Action.HttpMethod;
import org.meruvian.yama.actions.DefaultAction;

import com.opensymphony.xwork2.ModelDriven;

@Action(name = "/telecentre")
public class TelecentreAction extends DefaultAction implements
ModelDriven<TelecentreActionModel>{
	
	private static final long serialVersionUID = -7146969747428132843L;

	@Inject
	private TelecentreService telecentreService;
	
	private TelecentreActionModel model = new TelecentreActionModel();
	
	@Action(name = "/map", method = HttpMethod.GET)
	public ActionResult getAll() {
		model.setTelecentres(telecentreService.all(model.getMax(), model.getPage() - 1));
		return new ActionResult("freemarker",
				"/view/telecentre/map.ftl");
	}
	
	@Override
	public TelecentreActionModel getModel() {
		return this.model;
	}

}
