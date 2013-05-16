package net.bogor.itu.action.ticket;

import javax.inject.Inject;

import net.bogor.itu.service.ticket.TicketCategoryService;

import org.meruvian.inca.struts2.rest.ActionResult;
import org.meruvian.inca.struts2.rest.annotation.Action;
import org.meruvian.inca.struts2.rest.annotation.Action.HttpMethod;
import org.meruvian.inca.struts2.rest.annotation.Result;
import org.meruvian.inca.struts2.rest.annotation.Results;
import org.meruvian.yama.actions.DefaultAction;

import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.validator.annotations.RequiredStringValidator;
import com.opensymphony.xwork2.validator.annotations.Validations;

@Action(name = "/ticket/category")
@Results({ @Result(name = DefaultAction.INPUT, type = "freemarker", location = "/view/ticket/category/category-form.ftl") })
public class TicketCategoryAnswerAction extends DefaultAction implements
		ModelDriven<TicketCategoryActionModel> {

	private static final long serialVersionUID = -5576509336668803798L;
	
	private ActionResult redirectToIndex = new ActionResult("redirect",
			"/ticket/category");
	
	@Inject
	private TicketCategoryService ticketCategoryService;

	private TicketCategoryActionModel model = new TicketCategoryActionModel();
	
	@Action
	public ActionResult answeList() {
		model.setCategories(ticketCategoryService.findByKeyword(model.getQ(), null, "ASC", model.getMax(), model.getPage() - 1));
		return new ActionResult("freemarker",
				"/view/ticket/category/category-list.ftl");
	}
	
	@Action(name = "/add", method = HttpMethod.GET)
	public ActionResult addForm() {
		return new ActionResult("freemarker",
				"/view/ticket/category/category-form.ftl");
	} 
	
	@Action(name = "/add", method = HttpMethod.POST)
	@Validations(requiredStrings = { 
			@RequiredStringValidator(fieldName = "category.name", trim = true, key = "label.ticket.category.name.notnull")
		})
	public ActionResult addAnswer() {
		ticketCategoryService.save(model.getCategory());
		return redirectToIndex;
	}
	
	@Action(name = "/remove/{category.id}", method = HttpMethod.POST)
	public ActionResult removeAnswer() {
		ticketCategoryService.remove(ticketCategoryService.findById(model.getCategory().getId()));
		return redirectToIndex;
	}
	
	@Action(name = "/edit/{category.id}", method = HttpMethod.GET)
	public ActionResult editForm() {
		String id = model.getCategory().getId();
		if (id == null)
			return redirectToIndex;
		model.setCategory(ticketCategoryService.findById(id));
		if (model.getCategory() == null)
			return redirectToIndex;

		return new ActionResult("freemarker",
				"/view/ticket/category/category-form.ftl");
	}

	@Action(name = "/edit/{category.id}", method = HttpMethod.POST)
	@Validations(requiredStrings = { 
			@RequiredStringValidator(fieldName = "category.name", trim = true, key = "label.ticket.category.name.notnull")
		})
	public ActionResult updateService() {
		return addAnswer();
	}
	
	@Override
	public TicketCategoryActionModel getModel() {
		return model;
	}

}
