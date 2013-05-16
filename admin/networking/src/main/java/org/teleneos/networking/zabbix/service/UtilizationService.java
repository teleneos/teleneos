/**
 * 
 */
package org.teleneos.networking.zabbix.service;

import org.teleneos.networking.zabbix.entity.ZHistoryResponse;
import org.teleneos.networking.zabbix.entity.ZItem;
import org.teleneos.networking.zabbix.entity.ZItemResponse;

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
