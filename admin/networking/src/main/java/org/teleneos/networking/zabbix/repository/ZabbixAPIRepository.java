/**
 * 
 */
package org.teleneos.networking.zabbix.repository;

import java.util.Collections;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.teleneos.networking.zabbix.entity.ZabbixRequestEntity;

/**
 * @author Dian Aditya
 * 
 */
public class ZabbixAPIRepository extends RestTemplate {
	private String zabbixUrl;

	public <T> ResponseEntity<T> exchange(ZabbixRequestEntity requestEntity,
			Class<T> responseType) throws RestClientException {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

		HttpEntity<ZabbixRequestEntity> entity = new HttpEntity<ZabbixRequestEntity>(
				requestEntity, headers);

		return super.exchange(zabbixUrl, HttpMethod.POST, entity, responseType);
	}

	public void setZabbixUrl(String zabbixUrl) {
		this.zabbixUrl = zabbixUrl;
	}
}
