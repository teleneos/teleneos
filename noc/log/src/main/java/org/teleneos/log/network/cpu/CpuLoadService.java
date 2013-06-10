/**
 * 
 */
package org.teleneos.log.network.cpu;

import java.util.List;

/**
 * @author Dian Aditya
 * 
 */
public interface CpuLoadService {
	List<CpuLoad> findLatestCpuLoad(String telecentre, int limit);
}
