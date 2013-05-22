/**
 * 
 */
package org.teleneos.networking.zabbix.utilization;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.teleneos.networking.zabbix.history.ZHistoryRepository;
import org.teleneos.networking.zabbix.history.ZHistoryResponse;
import org.teleneos.networking.zabbix.item.ZItem;
import org.teleneos.networking.zabbix.item.ZItemRepository;
import org.teleneos.networking.zabbix.item.ZItemResponse;

/**
 * @author Dian Aditya
 * 
 */
@Service
public class UtilizationServiceImpl implements UtilizationService {
	@Inject
	private ZHistoryRepository utilizationRepository;

	@Inject
	private ZItemRepository itemRepository;

	@Override
	public ZItem getFreeSwapSpace() {
		return itemRepository.findByKey("system.swap.size[,free]", true);
	}

	@Override
	public ZItem getTotalSwapSpace() {
		return itemRepository.findByKey("system.swap.size[,total]", true);
	}

	@Override
	public ZItem getFreeDiskSpace() {
		return itemRepository.findByKey("vfs.fs.size[/,free]", true);
	}

	@Override
	public ZItem getTotalDiskSpace() {
		return itemRepository.findByKey("vfs.fs.size[/,total]", true);
	}

	@Override
	public ZItem getUsedDiskSpace() {
		return itemRepository.findByKey("vfs.fs.size[/,used]", true);
	}

	@Override
	public ZHistoryResponse findCPUHistory(int limit) {
		ZItem item = getCPULoadAvg1Min();

		return utilizationRepository.findAll(item.getItemid(), 0, null, null,
				limit);
	}

	@Override
	public ZItem getCPULoadAvg1Min() {
		return itemRepository.findByKey("system.cpu.load[percpu,avg1]", true);
	}

	@Override
	public ZItem getCPULoadAvg5Min() {
		return itemRepository.findByKey("system.cpu.load[percpu,avg5]", true);
	}

	@Override
	public ZItem getCPULoadAvg15Min() {
		return itemRepository.findByKey("system.cpu.load[percpu,avg15]", true);
	}

	@Override
	public ZItemResponse getItems() {
		return itemRepository.findAll("system.cpu", true, 0);
	}
}
