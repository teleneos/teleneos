/**
 * 
 */
package org.teleneos.networking.zabbix.service;

import org.teleneos.networking.zabbix.entity.ZHistoryResponse;
import org.teleneos.networking.zabbix.entity.ZItem;

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
