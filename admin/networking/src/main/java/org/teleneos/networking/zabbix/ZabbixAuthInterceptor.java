/**
 * 
 */
package org.teleneos.networking.zabbix;

import java.io.IOException;

import javax.inject.Inject;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.teleneos.networking.zabbix.entity.SessionToken;
import org.teleneos.networking.zabbix.entity.ZabbixRequestEntity;
import org.teleneos.networking.zabbix.repository.UserLoginRepository;

/**
 * @author Dian Aditya
 * 
 */
public class ZabbixAuthInterceptor implements ClientHttpRequestInterceptor {

	@Inject
	private UserLoginRepository loginRepository;

	@Inject
	private SessionToken sessionToken;

	@Inject
	private ObjectMapper objectMapper;

	@Override
	public ClientHttpResponse intercept(HttpRequest request, byte[] body,
			ClientHttpRequestExecution execution) throws IOException {
		if (sessionToken.getToken() == null) {
			sessionToken.setSessionToken(loginRepository.authenticate());
		}

		ZabbixRequestEntity requestBody = objectMapper.readValue(body,
				ZabbixRequestEntity.class);
		requestBody.setAuth(sessionToken.getToken());

		ClientHttpResponse response = execution.execute(request,
				objectMapper.writeValueAsBytes(requestBody));

		// JsonNode jsonResponse = objectMapper.readTree(response.getBody());
		// if (jsonResponse.get("error") != null) {
		// sessionToken.clear();
		// }

		return response;
	}
}