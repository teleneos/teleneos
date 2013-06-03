package org.teleneos.ticket.answer;

import javax.inject.Inject;

import org.meruvian.inca.struts2.rest.annotation.Action;
import org.meruvian.inca.struts2.rest.annotation.Result;
import org.meruvian.inca.struts2.rest.annotation.Results;
import org.meruvian.yama.actions.DefaultAction;

import com.opensymphony.xwork2.ModelDriven;

@Action(name = "/user/faq")
@Results({ @Result(name = DefaultAction.INDEX, type = "freemarker", location = "/view/ticket/premade/faq.ftl") })
public class FaqAction extends DefaultAction implements
		ModelDriven<PremadeAnswerActionModel> {

	private static final long serialVersionUID = 8950693562907725317L;

	private PremadeAnswerActionModel model = new PremadeAnswerActionModel();
	
	@Inject
	private PremadeAnswerService answerService;
	
	@Action
	public String index() {
		model.setAnswers(answerService.findByFaq(model.getQ(), null, "ASC", 0, 0));
		return INDEX;
	}

	@Override
	public PremadeAnswerActionModel getModel() {
		return model;
	}

}
