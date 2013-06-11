/**
 * 
 */
package org.teleneos.noc.telecentre;


/**
 * @author Dian Aditya
 * 
 */
public interface TelecentreService {
	Telecentre save(Telecentre telecentre);

	Telecentre findById(String id);

	Telecentre remove(Telecentre telecentre);

	Telecentre changePassword(String id);
}
