/**
 * 
 */
package org.teleneos.log.availability;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.meruvian.yama.persistence.NocDefaultPersistence;

/**
 * @author Dian Aditya
 * 
 */
@Entity
@Table(name = "noc_availability_ping")
public class Ping extends NocDefaultPersistence {

}
