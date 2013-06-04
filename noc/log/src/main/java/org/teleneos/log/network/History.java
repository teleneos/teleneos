/**
 * 
 */
package org.teleneos.log.network;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;

import org.meruvian.yama.persistence.NocDefaultPersistence;

/**
 * @author Dian Aditya
 * 
 */
@MappedSuperclass
public class History extends NocDefaultPersistence {
	private List<String> maintenances = new ArrayList<String>();
	private String itemid;
	private String clock;
	private String value;
	private String ns;

	@Transient
	public List<String> getMaintenances() {
		return maintenances;
	}

	public void setMaintenances(List<String> maintenances) {
		this.maintenances = maintenances;
	}

	public String getItemid() {
		return itemid;
	}

	public void setItemid(String itemid) {
		this.itemid = itemid;
	}

	public String getClock() {
		return clock;
	}

	public void setClock(String clock) {
		this.clock = clock;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getNs() {
		return ns;
	}

	public void setNs(String ns) {
		this.ns = ns;
	}
}
