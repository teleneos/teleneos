/**
 * 
 */
package org.teleneos.networking.zabbix.entity;

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
