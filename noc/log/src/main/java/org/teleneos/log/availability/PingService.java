/**
 * 
 */
package org.teleneos.log.availability;

import org.meruvian.yama.persistence.EntityListWrapper;

/**
 * @author Dian Aditya
 * 
 */
public interface PingService {
	EntityListWrapper<Object[]> findLatestStatus(String name, int limit,
			int page);
}
