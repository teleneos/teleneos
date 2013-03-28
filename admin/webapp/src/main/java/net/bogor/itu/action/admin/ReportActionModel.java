package net.bogor.itu.action.admin;

import org.meruvian.yama.actions.DefaultActionModel;
import org.meruvian.yama.persistence.EntityListWrapper;

public class ReportActionModel extends DefaultActionModel {

	private EntityListWrapper<Object[]> itemReports = new EntityListWrapper<Object[]>();
	private String periodfrom, periodto;

	public EntityListWrapper<Object[]> getItemReports() {
		return itemReports;
	}

	public void setItemReports(EntityListWrapper<Object[]> itemReports) {
		this.itemReports = itemReports;
	}

	public String getPeriodfrom() {
		return periodfrom;
	}

	public void setPeriodfrom(String periodfrom) {
		this.periodfrom = periodfrom;
	}

	public String getPeriodto() {
		return periodto;
	}

	public void setPeriodto(String periodto) {
		this.periodto = periodto;
	}

}
