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
public interface PerformanceService {
	ZHistoryResponse findServerPerformance(int limit);

	ZItem findLastProcessedValue();
}
