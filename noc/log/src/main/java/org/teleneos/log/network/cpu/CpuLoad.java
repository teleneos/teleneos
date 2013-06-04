/**
 * 
 */
package org.teleneos.log.network.cpu;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.teleneos.log.network.History;

/**
 * @author Dian Aditya
 * 
 */
@Entity
@Table(name = "noc_network_cpu")
public class CpuLoad extends History {

}
