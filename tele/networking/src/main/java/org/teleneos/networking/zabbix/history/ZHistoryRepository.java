/**
 * 
 */
package org.teleneos.networking.zabbix.history;

import javax.inject.Inject;

import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Repository;
import org.teleneos.networking.zabbix.ZabbixRequestEntity;
import org.teleneos.networking.zabbix.ZabbixRequestRepository;

/**
 * @author Dian Aditya
 * 
 */
@Repository
public class ZHistoryRepository {
	@Inject
	private ZabbixRequestRepository requestRepository;

	public ZHistoryResponse findAll(String itemids, int history,
			String sortfield, String sortorder, int limit) {
		sortfield = ObjectUtils.defaultIfNull(sortfield, "clock");
		sortorder = ObjectUtils.defaultIfNull(sortorder, "DESC");

		ZabbixRequestEntity request = new ZabbixRequestEntity();
		request.setId("1");
		request.setMethod("history.get");
		request.getParams().put("output", "extend");
		if (history >= 0)
			request.getParams().put("history", history);
		request.getParams().put("itemids", itemids);
		request.getParams().put("sortfield", sortfield);
		request.getParams().put("sortorder", sortorder);
		request.getParams().put("limit", limit > 0 ? limit : null);

		return requestRepository.exchange(request, ZHistoryResponse.class)
				.getBody();
	}
}
