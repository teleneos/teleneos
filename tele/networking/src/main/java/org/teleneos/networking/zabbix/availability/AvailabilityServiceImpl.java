/**
 * 
 */
package org.teleneos.networking.zabbix.availability;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.teleneos.networking.zabbix.host.ZHostRepository;
import org.teleneos.networking.zabbix.host.ZHostResponse;

/**
 * @author Dian Aditya
 * 
 */
@Service
public class AvailabilityServiceImpl implements AvailabilityService {

	@Inject
	private ZHostRepository hostRepository;

	@Override
	public ZHostResponse findAllHosts(int limit) {
		return hostRepository.findAll(-1, limit);
	}

	@Override
	public ZHostResponse findAvailableHosts(int limit) {
		return hostRepository.findAll(1, limit);
	}

	@Override
	public ZHostResponse findUnavailableHosts(int limit) {
		return hostRepository.findAll(0, limit);
	}

}
