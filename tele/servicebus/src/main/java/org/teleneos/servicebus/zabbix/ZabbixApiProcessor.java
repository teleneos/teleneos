/**
 * 
 */
package org.teleneos.servicebus.zabbix;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.apache.camel.Message;
import org.teleneos.networking.zabbix.availability.AvailabilityService;
import org.teleneos.networking.zabbix.history.ZHistoryResponse;
import org.teleneos.networking.zabbix.host.ZHostResponse;
import org.teleneos.networking.zabbix.item.ZItem;
import org.teleneos.networking.zabbix.monitoring.MonitoringService;
import org.teleneos.networking.zabbix.performance.PerformanceService;
import org.teleneos.networking.zabbix.utilization.UtilizationService;

/**
 * @author Dian Aditya
 * 
 */
public class ZabbixApiProcessor {
	@Inject
	private AvailabilityService availabilityService;

	@Inject
	private MonitoringService monitoringService;

	@Inject
	private PerformanceService performanceService;

	@Inject
	private UtilizationService utilizationService;

	public ZHostResponse hosts(Message message) {
		return availabilityService.findAllHosts(0);
	}

	public List<ZHistoryResponse> traffic(Message message) {
		List<ZHistoryResponse> historyResponses = new ArrayList<ZHistoryResponse>();
		historyResponses.add(monitoringService.findIncomingTraffic(1));
		historyResponses.add(monitoringService.findOutgoingTraffic(1));

		return historyResponses;
	}

	public ZHistoryResponse performance(Message message) {
		return performanceService.findServerPerformance(1);
	}

	public ZHistoryResponse processor(Message message) {
		return utilizationService.findCPUHistory(1);
	}

	public List<ZItem> disk(Message message) {
		List<ZItem> items = new ArrayList<ZItem>();
		items.add(utilizationService.getFreeDiskSpace());
		items.add(utilizationService.getTotalDiskSpace());
		items.add(utilizationService.getFreeSwapSpace());
		items.add(utilizationService.getTotalSwapSpace());

		return items;
	}
}