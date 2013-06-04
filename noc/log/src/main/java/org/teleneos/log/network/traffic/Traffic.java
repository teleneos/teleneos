/**
 * 
 */
package org.teleneos.log.network.traffic;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

import org.teleneos.log.network.History;

/**
 * @author Dian Aditya
 * 
 */
@Entity
@Table(name = "noc_network_traffic")
public class Traffic extends History {
	public enum TrafficType {
		INCOMING, OUTGOING
	}

	private TrafficType trafficType;

	@Column(name = "traffic_type")
	@Enumerated(EnumType.ORDINAL)
	public TrafficType getTrafficType() {
		return trafficType;
	}

	public void setTrafficType(TrafficType trafficType) {
		this.trafficType = trafficType;
	}
}
