/**
 * 
 */
package org.teleneos.networking.zabbix.service;

import org.teleneos.networking.zabbix.entity.ZHostResponse;

/**
 * @author Dian Aditya
 * 
 */
public interface AvailabilityService {
	ZHostResponse findAllHosts(int limit);

	ZHostResponse findAvailableHosts(int limit);

	ZHostResponse findUnavailableHosts(int limit);
}
