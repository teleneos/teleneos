package id.co.bonet.itu.noc.action;

import id.co.bonet.itu.noc.service.TelecentreService;

import javax.inject.Inject;

import org.meruvian.inca.struts2.rest.ActionResult;
import org.meruvian.inca.struts2.rest.annotation.Action;
import org.meruvian.inca.struts2.rest.annotation.Result;
import org.meruvian.inca.struts2.rest.annotation.Results;
import org.meruvian.inca.struts2.rest.annotation.Action.HttpMethod;
import org.meruvian.yama.actions.DefaultAction;

import com.opensymphony.xwork2.ModelDriven;

@Action(name = "telecentre")
@Results({
		@Result(name = DefaultAction.INDEX, type = "freemarker", location = "/view/telecentre/telecentre-list.ftl"),
		@Result(name = DefaultAction.INPUT, type = "freemarker", location = "/view/telecentre/telecentre-form.ftl") })
public class TelecenterFormAction extends DefaultAction implements
		ModelDriven<TelecentreActionModel> {

	private static final long serialVersionUID = -7617113241846604052L;

	private TelecentreActionModel model = new TelecentreActionModel();
	
	@Inject
	private TelecentreService service;

	@Action(name = "manage", method = HttpMethod.GET)
	public String index() {
		model.setTelecentres(service.all(0, 0));
		return DefaultAction.INDEX;
	}

	@Action(name = "edit/{telecentre.id}", method = HttpMethod.GET)
	public String form() {
		model.setTelecentre(service.findById(model.getTelecentre().getId()));
		return DefaultAction.INPUT;
	}
	
	@Action(name = "edit/{telecentre.id}", method = HttpMethod.POST)
	public ActionResult update() {
		service.save(model.getTelecentre());
		return new ActionResult("redirect", "/telecentre/edit/"+model.getTelecentre().getId());
	}
	
	@Override
	public TelecentreActionModel getModel() {
		return model;
	}

}
