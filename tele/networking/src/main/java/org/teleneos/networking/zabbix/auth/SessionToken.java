/**
 * 
 */
package org.teleneos.networking.zabbix.auth;

/**
 * @author Dian Aditya
 * 
 */
public class SessionToken {
	private String username;
	private String token;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public void clear() {
		this.username = null;
		this.token = null;
	}

	public void setSessionToken(SessionToken token) {
		this.username = token.username;
		this.token = token.token;
	}
}