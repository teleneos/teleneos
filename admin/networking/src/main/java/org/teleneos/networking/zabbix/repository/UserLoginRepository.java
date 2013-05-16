/**
 * 
 */
package org.teleneos.networking.zabbix.repository;

import java.io.IOException;

import org.codehaus.jackson.JsonNode;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.teleneos.networking.zabbix.entity.LoginRequestEntity;
import org.teleneos.networking.zabbix.entity.SessionToken;

/**
 * @author Dian Aditya
 * 
 */
public class UserLoginRepository extends ZabbixAPIRepository {
	@Value("${zabbix.admin.username}")
	private String zabbixUsername;

	@Value("${zabbix.admin.password}")
	private String zabbixPassword;

	public SessionToken authenticate() throws IOException {
		LoginRequestEntity entity = new LoginRequestEntity(zabbixUsername,
				zabbixPassword);
		entity.setId("1");

		ResponseEntity<JsonNode> response = exchange(entity, JsonNode.class);

		if (response.getStatusCode().equals(HttpStatus.OK)) {
			JsonNode node = response.getBody();
			if (node.get("result") != null) {
				SessionToken token = new SessionToken();
				token.setUsername(zabbixUsername);
				token.setToken(node.get("result").asText());

				return token;
			}
		}

		return null;
	}
}
