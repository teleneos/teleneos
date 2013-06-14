/**
 * 
 */
package org.teleneos.log.radius.accounting;

import org.meruvian.yama.persistence.EntityListWrapper;

/**
 * @author Dian Aditya
 * 
 */
public interface RadiusAccountingService {
	EntityListWrapper<RadiusAccounting> findAll(String telecentre, String user,
			long from, long to, String order, int limit, int page);
}
