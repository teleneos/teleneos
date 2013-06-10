/**
 * 
 */
package org.teleneos.log.network.performance;

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
public class PerformanceServiceImpl implements PerformanceService {

	@Inject
	private PerformanceRepository performanceRepository;

	@Override
	public List<Performance> findLatestPerformance(String telecentre, int limit) {
		return performanceRepository.findLatestPerformance(telecentre, limit);
	}
}
