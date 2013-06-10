/**
 * 
 */
package org.teleneos.log.network.performance;

import java.util.List;

/**
 * @author Dian Aditya
 * 
 */
public interface PerformanceService {
	List<Performance> findLatestPerformance(String telecentre, int limit);
}
