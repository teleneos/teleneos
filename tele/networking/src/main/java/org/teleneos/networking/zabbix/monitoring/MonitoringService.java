/**
 * 
 */
package org.teleneos.networking.zabbix.monitoring;

import org.teleneos.networking.zabbix.history.ZHistoryResponse;
import org.teleneos.networking.zabbix.item.ZItem;

/**
 * @author Dian Aditya
 * 
 */
public interface MonitoringService {
	ZHistoryResponse findIncomingTraffic(int limit);

	ZHistoryResponse findOutgoingTraffic(int limit);

	ZItem getLastIncomingTraffic();

	ZItem getLastOutgoingTraffic();
}
