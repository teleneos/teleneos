/**
 * 
 */
package org.teleneos.networking.zabbix.auth;

import org.teleneos.networking.zabbix.ZabbixRequestEntity;

/**
 * @author Dian Aditya
 * 
 */
public class LoginRequestEntity extends ZabbixRequestEntity {
	public LoginRequestEntity(String username, String password) {
		super();

		getParams().put("user", username);
		getParams().put("password", password);
		setMethod("user.login");
	}
}
