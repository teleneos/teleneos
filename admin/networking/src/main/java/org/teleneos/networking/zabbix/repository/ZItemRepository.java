/**
 * 
 */
package org.teleneos.networking.zabbix.repository;

import java.util.Collections;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.teleneos.networking.zabbix.entity.ZItem;
import org.teleneos.networking.zabbix.entity.ZItemResponse;
import org.teleneos.networking.zabbix.entity.ZabbixRequestEntity;

/**
 * @author Dian Aditya
 * 
 */
@Repository
public class ZItemRepository {
	@Inject
	private ZabbixRequestRepository requestRepository;

	@Value("${zabbix.server.host_id}")
	private String serverHostId;

	public ZItemResponse findAll(String key, boolean server, int limit) {
		ZabbixRequestEntity request = new ZabbixRequestEntity();
		request.setId("1");
		request.setMethod("item.get");
		request.getParams().put("output", "extend");
		request.getParams().put("limit", limit > 0 ? limit : null);
		request.getParams()
				.put("search", Collections.singletonMap("key_", key));
		if (server)
			request.getParams().put("hostids", serverHostId);

		return requestRepository.exchange(request, ZItemResponse.class)
				.getBody();
	}

	public ZItem findByKey(String key, boolean server) {
		ZabbixRequestEntity request = new ZabbixRequestEntity();
		request.setId("1");
		request.setMethod("item.get");
		request.getParams().put("output", "extend");
		request.getParams()
				.put("filter", Collections.singletonMap("key_", key));
		if (server)
			request.getParams().put("hostids", serverHostId);

		ResponseEntity<ZItemResponse> response = requestRepository.exchange(
				request, ZItemResponse.class);
		ZItemResponse itemResponse = response.getBody();
		if (itemResponse.getResult().size() > 0) {
			return itemResponse.getResult().get(0);
		}

		return null;
	}
}
