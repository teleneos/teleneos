package net.bogor.itu.action.ticket;

import javax.inject.Inject;

import net.bogor.itu.service.ticket.PremadeAnswerService;

import org.meruvian.inca.struts2.rest.ActionResult;
import org.meruvian.inca.struts2.rest.annotation.Action;
import org.meruvian.inca.struts2.rest.annotation.Action.HttpMethod;
import org.meruvian.inca.struts2.rest.annotation.Result;
import org.meruvian.inca.struts2.rest.annotation.Results;
import org.meruvian.yama.actions.DefaultAction;

import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.validator.annotations.RequiredStringValidator;
import com.opensymphony.xwork2.validator.annotations.Validations;

@Action(name = "/ticket/premade")
@Results({ @Result(name = DefaultAction.INPUT, type = "freemarker", location = "/view/ticket/premade/premade-form.ftl") })
public class PremadeAnswerAction extends DefaultAction implements
		ModelDriven<PremadeAnswerActionModel> {

	private static final long serialVersionUID = -5576509336668803798L;
	
	private ActionResult redirectToIndex = new ActionResult("redirect",
			"/ticket/premade");
	
	@Inject
	private PremadeAnswerService premadeAnswerService;

	private PremadeAnswerActionModel model = new PremadeAnswerActionModel();

	@Action
	public ActionResult answeList() {
		model.setAnswers(premadeAnswerService.findByKeyword(model.getQ(), null, "ASC", model.getMax(), model.getPage() - 1));
		return new ActionResult("freemarker",
				"/view/ticket/premade/premade-list.ftl");
	}
	
	@Action(name = "/add", method = HttpMethod.GET)
	public ActionResult addForm() {
		return new ActionResult("freemarker",
				"/view/ticket/premade/premade-form.ftl");
	}
	
	@Action(name = "/add", method = HttpMethod.POST)
	@Validations(requiredStrings = { 
			@RequiredStringValidator(fieldName = "answer.title", trim = true, key = "label.ticket.premade.title.notnull") ,
			@RequiredStringValidator(fieldName = "answer.content", trim = true, key = "label.ticket.premade.content.notnull")
		})
	public ActionResult addAnswer() {
		premadeAnswerService.save(model.getAnswer());
		return redirectToIndex;
	}
	
	@Action(name = "/edit/{answer.id}", method = HttpMethod.GET)
	public ActionResult editForm() {
		String id = model.getAnswer().getId();
		if (id == null)
			return redirectToIndex;
		model.setAnswer(premadeAnswerService.findById(id));
		if (model.getAnswer() == null)
			return redirectToIndex;

		return new ActionResult("freemarker",
				"/view/ticket/premade/premade-form.ftl");
	}

	@Action(name = "/edit/{answer.id}", method = HttpMethod.POST)
	@Validations(requiredStrings = { 
			@RequiredStringValidator(fieldName = "answer.title", trim = true, key = "label.ticket.premade.title.notnull") ,
			@RequiredStringValidator(fieldName = "answer.content", trim = true, key = "label.ticket.premade.content.notnull")
		})
	
	public ActionResult updateService() {
		return addAnswer();
	}
	
	@Override
	public PremadeAnswerActionModel getModel() {
		return model;
	}

}
