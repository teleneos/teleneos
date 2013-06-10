package org.teleneos.radius.bandwidth;

import org.teleneos.radius.accounting.Radacct;
import org.teleneos.radius.history.ConnectionHistory;

public class UserInformation {
	private Radacct radacct;
	private ConnectionHistory connectionHistory;

	public UserInformation(Radacct radacct, ConnectionHistory connectionHistory) {
		this.radacct = radacct;
		this.connectionHistory = connectionHistory;
	}

	public Radacct getRadacct() {
		return radacct;
	}

	public void setRadacct(Radacct radacct) {
		this.radacct = radacct;
	}

	public ConnectionHistory getConnectionHistory() {
		return connectionHistory;
	}

	public void setConnectionHistory(ConnectionHistory connectionHistory) {
		this.connectionHistory = connectionHistory;
	}

}
