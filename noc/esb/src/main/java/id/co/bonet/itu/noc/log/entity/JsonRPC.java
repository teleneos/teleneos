/**
 * 
 */
package id.co.bonet.itu.noc.log.entity;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Dian Aditya
 * 
 */
public class JsonRPC {
	private String jsonrpc = "2.0";
	private String method;
	private Map<String, String> params = new HashMap<String, String>();
	private String id;
	private String auth = null;

	public String getJsonrpc() {
		return jsonrpc;
	}

	public void setJsonrpc(String jsonrpc) {
		this.jsonrpc = jsonrpc;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public Map<String, String> getParams() {
		return params;
	}

	public void setParams(Map<String, String> params) {
		this.params = params;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAuth() {
		return auth;
	}

	public void setAuth(String auth) {
		this.auth = auth;
	}

}
