/**
 * 
 */
package org.teleneos.networking.zabbix.host;

import java.util.Collections;

import javax.inject.Inject;

import org.springframework.stereotype.Repository;
import org.teleneos.networking.zabbix.ZabbixRequestEntity;
import org.teleneos.networking.zabbix.ZabbixRequestRepository;

/**
 * @author Dian Aditya
 * 
 */
@Repository
public class ZHostRepository {

	@Inject
	private ZabbixRequestRepository requestRepository;

	public ZHostResponse findAll(int availability, int limit) {
		ZabbixRequestEntity request = new ZabbixRequestEntity();
		request.setId("1");
		request.setMethod("host.get");
		request.getParams().put("output", "extend");
		request.getParams().put("limit", limit > 0 ? limit : null);
		if (availability >= 0)
			request.getParams().put("filter",
					Collections.singletonMap("available", availability));

		return requestRepository.exchange(request, ZHostResponse.class)
				.getBody();
	}
}
