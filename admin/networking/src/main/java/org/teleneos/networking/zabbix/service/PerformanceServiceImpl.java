/**
 * 
 */
package org.teleneos.networking.zabbix.service;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.teleneos.networking.zabbix.entity.ZHistoryResponse;
import org.teleneos.networking.zabbix.entity.ZItem;
import org.teleneos.networking.zabbix.repository.ZHistoryRepository;
import org.teleneos.networking.zabbix.repository.ZItemRepository;

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
