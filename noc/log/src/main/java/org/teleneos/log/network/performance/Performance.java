/**
 * 
 */
package org.teleneos.log.network.performance;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.teleneos.log.network.History;

/**
 * @author Dian Aditya
 * 
 */
@Entity
@Table(name = "noc_network_performance")
public class Performance extends History {

}
