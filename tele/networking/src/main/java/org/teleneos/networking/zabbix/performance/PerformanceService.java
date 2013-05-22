/**
 * 
 */
package org.teleneos.networking.zabbix.performance;

import org.teleneos.networking.zabbix.history.ZHistoryResponse;
import org.teleneos.networking.zabbix.item.ZItem;

/**
 * @author Dian Aditya
 * 
 */
public interface PerformanceService {
	ZHistoryResponse findServerPerformance(int limit);

	ZItem findLastProcessedValue();
}
