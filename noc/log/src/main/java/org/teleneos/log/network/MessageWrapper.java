/**
 * 
 */
package org.teleneos.log.network;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Dian Aditya
 * 
 */
public class MessageWrapper<T> {
	private String jsonrpc;
	private String id;
	private List<T> result = new ArrayList<T>();

	public String getJsonrpc() {
		return jsonrpc;
	}

	public void setJsonrpc(String jsonrpc) {
		this.jsonrpc = jsonrpc;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public List<T> getResult() {
		return result;
	}

	public void setResult(List<T> result) {
		this.result = result;
	}
}