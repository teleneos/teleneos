/**
 * 
 */
package org.teleneos.networking.zabbix.monitoring;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.teleneos.networking.zabbix.history.ZHistoryRepository;
import org.teleneos.networking.zabbix.history.ZHistoryResponse;
import org.teleneos.networking.zabbix.item.ZItem;
import org.teleneos.networking.zabbix.item.ZItemRepository;

/**
 * @author Dian Aditya
 * 
 */
@Service
public class MonitoringServiceImpl implements MonitoringService {

	@Inject
	private ZHistoryRepository historyRepository;

	@Inject
	private ZItemRepository itemRepository;

	@Value("${zabbix.monitored.network_interface}")
	private String networkInterface;

	@Override
	public ZHistoryResponse findIncomingTraffic(int limit) {
		return historyRepository.findAll(getLastIncomingTraffic().getItemid(),
				-1, null, null, limit);
	}

	@Override
	public ZHistoryResponse findOutgoingTraffic(int limit) {
		return historyRepository.findAll(getLastOutgoingTraffic().getItemid(),
				-1, null, null, limit);
	}

	@Override
	public ZItem getLastIncomingTraffic() {
		return itemRepository.findByKey("net.if.in[" + networkInterface + "]",
				false);
	}

	@Override
	public ZItem getLastOutgoingTraffic() {
		return itemRepository.findByKey("net.if.out[" + networkInterface + "]",
				false);
	}
}
