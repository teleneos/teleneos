package org.teleneos.pos.report;

import javax.inject.Inject;

import org.meruvian.inca.struts2.rest.annotation.Action;
import org.meruvian.inca.struts2.rest.annotation.Result;
import org.meruvian.inca.struts2.rest.annotation.Results;
import org.meruvian.inca.struts2.rest.annotation.Action.HttpMethod;
import org.meruvian.yama.actions.DefaultAction;
import org.teleneos.pos.transaction.TransactionDetailService;

import com.opensymphony.xwork2.ModelDriven;

@Action(name = "/report")
@Results({ @Result(name = DefaultAction.INDEX, type = "freemarker", location = "/view/report/postpaid.ftl") })
public class PostpaidReportAction extends DefaultAction implements
		ModelDriven<PostpaidReportActionModel> {

	private PostpaidReportActionModel model = new PostpaidReportActionModel();
	
	@Inject
	private TransactionDetailService detailService;
	
	@Action(method = HttpMethod.GET, name = "postpaid")
	public String index() {
		model.setTransactionDetails(detailService.generatePostpaidReport(model.getQ(), model.getMax(), model.getPage() - 1));
		return DefaultAction.INDEX;
	}

	@Override
	public PostpaidReportActionModel getModel() {
		return model;
	}

}
