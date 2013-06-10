/**
 * 
 */
package org.teleneos.noc.monitoring;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.meruvian.yama.actions.DefaultActionModel;
import org.meruvian.yama.persistence.EntityListWrapper;
import org.teleneos.log.network.History;
import org.teleneos.log.network.disk.DiskSpace;
import org.teleneos.log.network.host.Host;
import org.teleneos.noc.telecentre.Telecentre;

/**
 * @author Dian Aditya
 * 
 */
public class MonitoringActionModel extends DefaultActionModel {
	private String telecentre;
	private Telecentre tele = new Telecentre();
	private EntityListWrapper<Object[]> availabilities = new EntityListWrapper<Object[]>();
	private EntityListWrapper<Host> hosts = new EntityListWrapper<Host>();
	private List<DiskSpace> diskSpaces = new ArrayList<DiskSpace>();
	private List<? extends History> histories1 = new ArrayList<History>();
	private List<? extends History> histories2 = new ArrayList<History>();

	public String getTelecentre() {
		return telecentre;
	}

	public void setTelecentre(String telecentre) {
		this.telecentre = telecentre;
	}

	public Telecentre getTele() {
		return tele;
	}

	public void setTele(Telecentre tele) {
		this.tele = tele;
	}

	public EntityListWrapper<Object[]> getAvailabilities() {
		return availabilities;
	}

	public void setAvailabilities(EntityListWrapper<Object[]> availabilities) {
		this.availabilities = availabilities;
	}

	public EntityListWrapper<Host> getHosts() {
		return hosts;
	}

	public void setHosts(EntityListWrapper<Host> hosts) {
		this.hosts = hosts;
	}

	public List<DiskSpace> getDiskSpaces() {
		return diskSpaces;
	}

	public void setDiskSpaces(List<DiskSpace> diskSpaces) {
		this.diskSpaces = diskSpaces;
	}

	public List<? extends History> getHistories1() {
		return histories1;
	}

	public void setHistories1(List<? extends History> histories1) {
		this.histories1 = histories1;
	}

	public List<? extends History> getHistories2() {
		return histories2;
	}

	public void setHistories2(List<? extends History> histories2) {
		this.histories2 = histories2;
	}

	public Date getDate(int minute) {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.MINUTE, minute);

		return calendar.getTime();
	}
}
