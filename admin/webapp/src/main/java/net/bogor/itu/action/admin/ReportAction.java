package net.bogor.itu.action.admin;

import javax.inject.Inject;

import net.bogor.itu.service.pos.TransactionDetailService;

import org.meruvian.inca.struts2.rest.annotation.Action;
import org.meruvian.inca.struts2.rest.annotation.Action.HttpMethod;
import org.meruvian.inca.struts2.rest.annotation.Result;
import org.meruvian.inca.struts2.rest.annotation.Results;
import org.meruvian.yama.actions.DefaultAction;

import com.opensymphony.xwork2.ModelDriven;

@Action(name = "/report")
@Results({ @Result(name = DefaultAction.INDEX+"1", type = "freemarker", location = "/view/report/store.ftl"),
	@Result(name = DefaultAction.INDEX+"2", type = "freemarker", location = "/view/report/internet.ftl")})
public class ReportAction extends DefaultAction implements
		ModelDriven<ReportActionModel> {

	private static final long serialVersionUID = 1937268651442177012L;

	private ReportActionModel model = new ReportActionModel();
	
	@Inject
	private TransactionDetailService transactionDetailService;
	
	@Action(method = HttpMethod.GET, name="store")
	public String index() {
		return DefaultAction.INDEX+"1";
	}
	
	@Action(method = HttpMethod.POST, name="store")
	public String report(){
		model.setItemReports(transactionDetailService.report(model.getPeriodfrom(), model.getPeriodto()));
		return DefaultAction.INDEX+"1";
	}
	
	@Action(method = HttpMethod.GET, name="internet")
	public String indexNet() {
		return DefaultAction.INDEX+"2";
	}
	
	@Action(method = HttpMethod.POST, name="internet")
	public String reportNet(){
		model.setItemReports(transactionDetailService.internet(model.getPeriodfrom(), model.getPeriodto()));
		return DefaultAction.INDEX+"2";
	}
	@Override
	public ReportActionModel getModel() {
		return model;
	}

}
