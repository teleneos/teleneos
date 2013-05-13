package net.bogor.itu.action.admin;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.meruvian.yama.actions.DefaultActionModel;
import org.meruvian.yama.persistence.EntityListWrapper;

public class ReportActionModel extends DefaultActionModel {

	private EntityListWrapper<Object[]> itemReports = new EntityListWrapper<Object[]>();
	private String periodfrom, periodto, date;
	private DateFormat format = new SimpleDateFormat("MM/dd/yyyy");
	private List<Date> dates = new ArrayList<Date>();

	public EntityListWrapper<Object[]> getItemReports() {
		return itemReports;
	}

	public void setItemReports(EntityListWrapper<Object[]> itemReports) {
		this.itemReports = itemReports;
	}

	public String getPeriodfrom() {
		return StringUtils.defaultIfBlank(periodfrom,
				format.format(new Date(System.currentTimeMillis())));
	}

	public void setPeriodfrom(String periodfrom) {
		this.periodfrom = periodfrom;
	}

	public String getPeriodto() {
		return StringUtils.defaultIfBlank(periodto,
				format.format(new Date(System.currentTimeMillis())));
	}

	public void setPeriodto(String periodto) {
		this.periodto = periodto;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public List<Date> getDates() {
		return dates;
	}

	public void setDates(List<Date> dates) {
		this.dates = dates;
	}

}
