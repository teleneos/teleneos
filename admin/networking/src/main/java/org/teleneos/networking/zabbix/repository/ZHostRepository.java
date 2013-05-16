/**
 * 
 */
package org.teleneos.networking.zabbix.repository;

import java.util.Collections;

import javax.inject.Inject;

import org.springframework.stereotype.Repository;
import org.teleneos.networking.zabbix.entity.ZHostResponse;
import org.teleneos.networking.zabbix.entity.ZabbixRequestEntity;

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
