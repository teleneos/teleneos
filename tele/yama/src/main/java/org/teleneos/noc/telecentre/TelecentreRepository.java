/**
 * 
 */
package org.teleneos.noc.telecentre;


/**
 * @author Dian Aditya
 * 
 */
public interface TelecentreRepository {
	Telecentre findById(String id);

	void persist(Telecentre telecentre);
}
