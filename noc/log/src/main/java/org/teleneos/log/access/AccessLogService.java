/**
 * 
 */
package org.teleneos.log.access;

import org.meruvian.yama.persistence.EntityListWrapper;

/**
 * @author Dian Aditya
 * 
 */
public interface AccessLogService {
	EntityListWrapper<AccessLog> findAll(String telecentre, String user,
			String q, long from, long to, String order, int limit, int page)
			throws Exception;
}
