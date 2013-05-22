/**
 * 
 */
package org.teleneos.networking.zabbix;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Dian Aditya
 * 
 */
public class ZabbixRequestEntity {
	private String jsonrpc = "2.0";
	private Map<String, Object> params = new HashMap<String, Object>();
	private String auth;
	private String id;
	private String method;

	public String getJsonrpc() {
		return jsonrpc;
	}

	public void setJsonrpc(String jsonrpc) {
		this.jsonrpc = jsonrpc;
	}

	public Map<String, Object> getParams() {
		return params;
	}

	public void setParams(Map<String, Object> params) {
		this.params = params;
	}

	public String getAuth() {
		return auth;
	}

	public void setAuth(String auth) {
		this.auth = auth;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}
}