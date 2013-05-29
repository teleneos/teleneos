package org.teleneos.pos.report;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.inject.Inject;

import org.apache.commons.lang.StringUtils;
import org.joda.time.DateTimeConstants;
import org.joda.time.LocalDate;
import org.meruvian.inca.struts2.rest.ActionResult;
import org.meruvian.inca.struts2.rest.annotation.Action;
import org.meruvian.inca.struts2.rest.annotation.Action.HttpMethod;
import org.meruvian.yama.actions.DefaultAction;
import org.teleneos.radius.accounting.RadacctService;

import com.opensymphony.xwork2.ModelDriven;

@Action(name = "/report")
public class InternetStatisticReportAction extends DefaultAction implements
		ModelDriven<InternetStatisticReportActionModel> {

	private static final long serialVersionUID = -1475004257620984583L;

	private InternetStatisticReportActionModel model = new InternetStatisticReportActionModel();

	@Inject
	private RadacctService radacctService;

	@Action(method = HttpMethod.GET, name = "statistic/daily")
	public ActionResult daily() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		if (!StringUtils.isEmpty(model.getDate())) {
			model.setStatistics(radacctService.daily(model.getDate(), model.getMax(), model.getPage() - 1));
		} else {
			model.setStatistics(radacctService.daily(
					dateFormat.format(new Date()), model.getMax(), model.getPage() - 1));
			model.setDate(dateFormat.format(new Date()));
		}
		return new ActionResult("freemarker",
				"/view/report/daily/list-statistic.ftl");
	}
	
	@Action(method = HttpMethod.GET, name = "statistic/weekly")
	public ActionResult weekly() {
		LocalDate startDate = LocalDate.fromDateFields(radacctService.getFirstConnection());
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
			model.setStatistics(radacctService.weekly(model.getDate(), model.getMax(), model.getPage() - 1));
		}else{
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			model.setStatistics(radacctService.weekly(format.format(model.getDates().get(model.getDates().size()-1)), model.getMax(), model.getPage() - 1));
		}
		return new ActionResult("freemarker", "/view/report/weekly/list-statistic.ftl");
	}
	
	@Action(method = HttpMethod.GET, name = "statistic/monthly")
	public ActionResult monthly() {
		if (!StringUtils.isEmpty(model.getDate())) {
			model.setStatistics(radacctService.monthly(model
					.getDate(), model.getMax(), model.getPage() - 1));
		}else{
			SimpleDateFormat format = new SimpleDateFormat("MM/yyyy");
			model.setStatistics(radacctService.monthly(format.format(new Date()), model.getMax(), model.getPage() - 1));
			model.setDate(format.format(new Date()));
		}
		return new ActionResult("freemarker", "/view/report/monthly/list-statistic.ftl");
	}
	
	@Override
	public InternetStatisticReportActionModel getModel() {
		return model;
	}

}
