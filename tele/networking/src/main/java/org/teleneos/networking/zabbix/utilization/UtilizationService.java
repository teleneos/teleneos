/**
 * 
 */
package org.teleneos.networking.zabbix.utilization;

import org.teleneos.networking.zabbix.history.ZHistoryResponse;
import org.teleneos.networking.zabbix.item.ZItem;
import org.teleneos.networking.zabbix.item.ZItemResponse;

/**
 * @author Dian Aditya
 * 
 */
public interface UtilizationService {
	ZItem getFreeSwapSpace();

	ZItem getTotalSwapSpace();

	ZItem getFreeDiskSpace();

	ZItem getTotalDiskSpace();

	ZItem getUsedDiskSpace();

	ZHistoryResponse findCPUHistory(int limit);

	ZItem getCPULoadAvg1Min();

	ZItem getCPULoadAvg5Min();

	ZItem getCPULoadAvg15Min();

	ZItemResponse getItems();
}
