/**
 * 
 */
package org.teleneos.log.network.cpu;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Dian Aditya
 * 
 */
@Service
@Transactional(readOnly = true)
public class CpuLoadServiceImpl implements CpuLoadService {

	@Inject
	private CpuLoadRepository cpuLoadRepository;

	@Override
	public List<CpuLoad> findLatestCpuLoad(String telecentre, int limit) {
		return cpuLoadRepository.findLatestPerformance(telecentre, limit);
	}
}
