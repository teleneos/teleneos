/**
 * 
 */
package org.teleneos.networking.zabbix.performance;

import javax.inject.Inject;

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
public class PerformanceServiceImpl implements PerformanceService {
	@Inject
	private ZHistoryRepository historyRepository;

	@Inject
	private ZItemRepository itemRepository;

	@Override
	public ZHistoryResponse findServerPerformance(int limit) {
		return historyRepository.findAll(findLastProcessedValue().getItemid(),
				0, null, null, limit);
	}

	@Override
	public ZItem findLastProcessedValue() {
		return itemRepository.findByKey("zabbix[wcache,values]", true);
	}
}
