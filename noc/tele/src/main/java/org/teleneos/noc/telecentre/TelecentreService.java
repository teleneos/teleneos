/**
 * 
 */
package org.teleneos.noc.telecentre;

import org.meruvian.yama.persistence.EntityListWrapper;

/**
 * @author Dian Aditya
 * 
 */
public interface TelecentreService {
	Telecentre save(Telecentre telecentre);

	Telecentre findById(String id);

	EntityListWrapper<Telecentre> findAll();

	EntityListWrapper<Telecentre> findByName(String tele, int limit, int page,
			byte[] cookie);

	Telecentre remove(Telecentre telecentre);

	Telecentre changePassword(String id);
}
