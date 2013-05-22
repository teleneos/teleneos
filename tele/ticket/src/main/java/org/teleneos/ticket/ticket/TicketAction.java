package org.teleneos.ticket.ticket;


import javax.inject.Inject;

import org.meruvian.inca.struts2.rest.ActionResult;
import org.meruvian.inca.struts2.rest.annotation.Action;
import org.meruvian.inca.struts2.rest.annotation.Action.HttpMethod;
import org.meruvian.inca.struts2.rest.annotation.Result;
import org.meruvian.inca.struts2.rest.annotation.Results;
import org.meruvian.yama.actions.DefaultAction;
import org.meruvian.yama.persistence.LogInformation.StatusFlag;
import org.meruvian.yama.security.SessionCredentials;
import org.teleneos.ticket.answer.PremadeAnswerService;

import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.validator.annotations.RequiredStringValidator;
import com.opensymphony.xwork2.validator.annotations.Validations;

@Action(name = "/ticket")
@Results({ @Result(name = DefaultAction.INPUT, type = "freemarker", location = "/view/ticket/ticket-form.ftl") })
public class TicketAction extends DefaultAction implements
		ModelDriven<TicketActionModel> {

	private static final long serialVersionUID = 711220812950459766L;

	private TicketActionModel model = new TicketActionModel();
	
	private ActionResult redirectToIndex = new ActionResult("redirect",
			"/ticket/user/status");
	
	@Inject
	private TicketService ticketService;
	
	@Inject
	private TicketThreadService threadService;
	
	@Inject
	private PremadeAnswerService answerService;
	
	@Inject
	private TicketCategoryService ticketCategoryService;
	
	@Action
	public ActionResult ticketList() {
		model.setTickets(ticketService.findByKeyword(model.getQ(), null, "ASC",
				model.getMax(), model.getPage() - 1));
		return new ActionResult("freemarker", "/view/ticket/ticket-list.ftl");
	}
	
	@Action(name = "/user/submit", method = HttpMethod.GET)
	public ActionResult addForm() {
		return new ActionResult("freemarker",
				"/view/ticket/ticket-form.ftl");
	}
		
	@Action(name = "/user/submit", method = HttpMethod.POST)
	@Validations(requiredStrings = { 
			@RequiredStringValidator(fieldName = "ticket.subject", trim = true, key = "label.ticket.subject.notnull"),
			@RequiredStringValidator(fieldName = "ticket.message", trim = true, key = "label.ticket.message.notnull"),
			@RequiredStringValidator(fieldName = "ticket.category.id", trim = true, key = "label.ticket.category.notnull")
		})
	public ActionResult addTicket() {
		ticketService.save(model.getTicket());
		return redirectToIndex;
	}
	
	@Action(name = "/user/status", method = HttpMethod.GET)
	public ActionResult userList() {
		model.setTickets(ticketService.findByMe(model.getQ(), SessionCredentials.currentUser().getUsername(), null, "ASC",
				model.getMax(), model.getPage() - 1));
		return new ActionResult("freemarker",
				"/view/ticket/user-ticket-status.ftl");
	}
	
	@Action(name = "/answer", method = HttpMethod.GET)
	public ActionResult answerList() {
		model.setAnswers(answerService.findByKeyword(model.getQ(), null, "ASC", model.getMax(), model.getPage() - 1));
		return new ActionResult("freemarker",
				"/blank.ftl");
	}
	
	@Action(name = "/detail/{ticket.id}", method = HttpMethod.GET)
	public ActionResult ticketDetail() {
		model.setTicket(ticketService.findById(model.getTicket().getId()));
		model.setTicketThreads(threadService.findByTicket(model.getTicket()
				.getId(), "i.logInformation.createDate", "ASC", 0, 0));
		model.setAnswers(answerService.findByKeyword("", null, "ASC", 0, 0));
		return new ActionResult("freemarker",
				"/view/ticket/ticket-detail.ftl");
	}
	
	@Action(name = "/detail/{ticket.id}", method = HttpMethod.POST)
	public ActionResult ticketThread() {
		if(model.isClose()){
			Ticket ticket = ticketService.findById(model.getTicket().getId());
			ticket.getLogInformation().setStatusFlag(StatusFlag.INACTIVE);
			ticketService.save(ticket);
		}
		model.getTicketThread().setTicket(model.getTicket());
		threadService.save(model.getTicketThread());
		return new ActionResult("redirect",
				"/ticket/detail/"+model.getTicket().getId());
	}
	
	@Action(name = "/user/detail/{ticket.id}", method = HttpMethod.GET)
	public ActionResult ticketDetailUser() {
		model.setTicket(ticketService.findById(model.getTicket().getId()));
		model.setTicketThreads(threadService.findByTicket(model.getTicket()
				.getId(), "i.logInformation.createDate", "ASC", 0, 0));
		return new ActionResult("freemarker",
				"/view/ticket/ticket-detail.ftl");
	}
	
	@Action(name = "/user/detail/{ticket.id}", method = HttpMethod.POST)
	public ActionResult ticketThreadUser() {
		if(model.isClose()){
			Ticket ticket = ticketService.findById(model.getTicket().getId());
			ticket.getLogInformation().setStatusFlag(StatusFlag.INACTIVE);
			ticketService.save(ticket);
		}
		model.getTicketThread().setTicket(model.getTicket());
		threadService.save(model.getTicketThread());
		return new ActionResult("redirect",
				"/ticket/user/detail/"+model.getTicket().getId());
	}
	
	@Action(name = "/user/category")
	public ActionResult answeListUser() {
		model.setCategories(ticketCategoryService.findByKeyword(model.getQ(),
				null, "ASC", model.getMax(), model.getPage() - 1));
		return new ActionResult("freemarker",
				"/view/ticket/category/category-list.ftl");
	}
	
	@Override
	public TicketActionModel getModel() {
		return model;
	}
}
