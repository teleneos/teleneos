package org.teleneos.pos.report;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.meruvian.yama.actions.DefaultActionModel;
import org.meruvian.yama.persistence.EntityListWrapper;

public class InternetStatisticReportActionModel extends DefaultActionModel {

	private EntityListWrapper<Object[]> statistics = new EntityListWrapper<Object[]>();
	private List<Date> dates = new ArrayList<Date>();
	private String date;

	public EntityListWrapper<Object[]> getStatistics() {
		return statistics;
	}

	public void setStatistics(EntityListWrapper<Object[]> statistics) {
		this.statistics = statistics;
	}
	
	public List<Date> getDates() {
		return dates;
	}

	public void setDates(List<Date> dates) {
		this.dates = dates;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

}
