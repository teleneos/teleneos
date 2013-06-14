/**
 * 
 */
package org.teleneos.noc.log;

import java.util.Date;

import org.meruvian.yama.actions.DefaultActionModel;
import org.meruvian.yama.persistence.EntityListWrapper;
import org.teleneos.log.access.AccessLog;
import org.teleneos.log.radius.accounting.RadiusAccounting;

/**
 * @author Dian Aditya
 * 
 */
public class LogActionModel extends DefaultActionModel {
	private RadiusAccounting acct = new RadiusAccounting();
	private EntityListWrapper<RadiusAccounting> accts = new EntityListWrapper<RadiusAccounting>();
	private EntityListWrapper<AccessLog> logs = new EntityListWrapper<AccessLog>();
	private String telecentre;
	private String user;
	private long from;
	private long to;

	public RadiusAccounting getAcct() {
		return acct;
	}

	public void setAcct(RadiusAccounting acct) {
		this.acct = acct;
	}

	public EntityListWrapper<RadiusAccounting> getAccts() {
		return accts;
	}

	public void setAccts(EntityListWrapper<RadiusAccounting> accts) {
		this.accts = accts;
	}

	public EntityListWrapper<AccessLog> getLogs() {
		return logs;
	}

	public void setLogs(EntityListWrapper<AccessLog> logs) {
		this.logs = logs;
	}

	public String getTelecentre() {
		return telecentre;
	}

	public void setTelecentre(String telecentre) {
		this.telecentre = telecentre;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public long getFrom() {
		return from;
	}

	public void setFrom(long from) {
		this.from = from;
	}

	public long getTo() {
		return to;
	}

	public void setTo(long to) {
		this.to = to;
	}

	public Date toDate(long time) {
		return new Date(time);
	}
}
