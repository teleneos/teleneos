package net.bogor.itu.action.admin;

import javax.inject.Inject;

import net.bogor.itu.service.pos.TransactionDetailService;

import org.apache.commons.lang.StringUtils;
import org.joda.time.DateTimeConstants;
import org.joda.time.LocalDate;
import org.meruvian.inca.struts2.rest.ActionResult;
import org.meruvian.inca.struts2.rest.annotation.Action;
import org.meruvian.inca.struts2.rest.annotation.Action.HttpMethod;
import org.meruvian.inca.struts2.rest.annotation.Result;
import org.meruvian.inca.struts2.rest.annotation.Results;
import org.meruvian.yama.actions.DefaultAction;

import com.opensymphony.xwork2.ModelDriven;

@Action(name = "/report")
@Results({
		@Result(name = DefaultAction.INDEX + "1", type = "freemarker", location = "/view/report/store.ftl"),
		@Result(name = DefaultAction.INDEX + "2", type = "freemarker", location = "/view/report/internet.ftl") })
public class ReportAction extends DefaultAction implements
		ModelDriven<ReportActionModel> {

	private static final long serialVersionUID = 1937268651442177012L;

	private ReportActionModel model = new ReportActionModel();

	@Inject
	private TransactionDetailService transactionDetailService;

	@Action(method = HttpMethod.GET, name = "store/daily")
	public ActionResult daily() {
		if (!StringUtils.isEmpty(model.getDate())) {
			model.setItemReports(transactionDetailService.daily(model.getDate()));
		}
		return new ActionResult("freemarker", "/view/report/daily/list.ftl");
	}

	@Action(method = HttpMethod.GET, name = "store/weekly")
	public ActionResult weekly() {
		LocalDate startDate = LocalDate.fromDateFields(transactionDetailService.getFirstTransaction());
		if (startDate.getDayOfWeek() != 1) {
			startDate = startDate.minusDays(startDate.getDayOfWeek() + 1);
		}
		if (startDate != null) {
			LocalDate endDate = LocalDate.now();
			for (LocalDate date = startDate; date.isBefore(endDate); date = date.plusDays(1)) {
				if (date.getDayOfWeek() == DateTimeConstants.SUNDAY) {
					model.getDates().add(date.toDate());
				}
			}
		}
		if (!StringUtils.isEmpty(model.getDate())) {
			model.setItemReports(transactionDetailService.weekly(model.getDate()));
		}
		return new ActionResult("freemarker", "/view/report/weekly/list.ftl");
	}

	@Action(method = HttpMethod.GET, name = "store/monthly")
	public ActionResult monthly() {
		if (!StringUtils.isEmpty(model.getDate())) {
			model.setItemReports(transactionDetailService.monthly(model
					.getDate()));
		}
		return new ActionResult("freemarker", "/view/report/monthly/list.ftl");
	}

	@Action(method = HttpMethod.GET, name = "store")
	public String index() {
		model.setItemReports(transactionDetailService.report(
				model.getPeriodfrom(), model.getPeriodto()));

		return DefaultAction.INDEX + "1";
	}

	@Action(method = HttpMethod.POST, name = "store")
	public String report() {
		model.setItemReports(transactionDetailService.report(
				model.getPeriodfrom(), model.getPeriodto()));

		return DefaultAction.INDEX + "1";
	}

	@Action(method = HttpMethod.GET, name = "internet")
	public String indexNet() {
		model.setItemReports(transactionDetailService.internet(
				model.getPeriodfrom(), model.getPeriodto()));

		return DefaultAction.INDEX + "2";
	}

	@Action(method = HttpMethod.POST, name = "internet")
	public String reportNet() {
		model.setItemReports(transactionDetailService.internet(
				model.getPeriodfrom(), model.getPeriodto()));

		return DefaultAction.INDEX + "2";
	}

	@Override
	public ReportActionModel getModel() {
		return model;
	}

}
