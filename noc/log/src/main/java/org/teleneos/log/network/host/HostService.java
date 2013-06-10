/**
 * 
 */
package org.teleneos.log.network.host;

import org.meruvian.yama.persistence.EntityListWrapper;

/**
 * @author Dian Aditya
 * 
 */
public interface HostService {
	EntityListWrapper<Host> findLatestClientStatus(String telecentre,
			int limit, int page);
}
