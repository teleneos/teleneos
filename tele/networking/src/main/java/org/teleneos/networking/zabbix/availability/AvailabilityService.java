/**
 * 
 */
package org.teleneos.networking.zabbix.availability;

import org.teleneos.networking.zabbix.host.ZHostResponse;

/**
 * @author Dian Aditya
 * 
 */
public interface AvailabilityService {
	ZHostResponse findAllHosts(int limit);

	ZHostResponse findAvailableHosts(int limit);

	ZHostResponse findUnavailableHosts(int limit);
}
