/**
 * 
 */
package org.teleneos.log.network.traffic;

import java.util.List;

/**
 * @author Dian Aditya
 * 
 */
public interface TrafficService {
	public List<Traffic> findLatestIncoming(String telecentre, int limit);

	public List<Traffic> findLatestOutgoing(String telecentre, int limit);
}
