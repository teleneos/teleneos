/**
 * 
 */
package org.teleneos.noc.telecentre;

import org.meruvian.yama.persistence.EntityListWrapper;

/**
 * @author Dian Aditya
 * 
 */
public interface TelecentreRepository {
	Telecentre findById(String id);

	EntityListWrapper<Telecentre> findByName(String name, int limit, int page,
			byte[] cookie);

	void persist(Telecentre telecentre);
}
