package org.teleneos.noc;

import java.util.Date;

public class TelecentreLog {
	private String log;
	private Date logDate;
	private String appName;
	
	public TelecentreLog(){
	}
	
	public TelecentreLog(String log, Date logDate, String appName){
		this.log = log;
		this.logDate = logDate;
		this.appName = appName;
	}
	
	public String getLog() {
		return log;
	}
	public void setLog(String log) {
		this.log = log;
	}
	public Date getLogDate() {
		return logDate;
	}
	public void setLogDate(Date logDate) {
		this.logDate = logDate;
	}
	public String getAppName() {
		return appName;
	}
	public void setAppName(String appName) {
		this.appName = appName;
	}
	
}
