/**
 * 
 */
package org.teleneos.log.access;

import org.apache.solr.client.solrj.beans.Field;

/**
 * @author Dian Aditya
 * 
 */
public class AccessLog {
	@Field
	private String id;
	@Field
	private String tele;
	@Field
	private long time;
	@Field
	private int elapsed;
	@Field
	private String user;
	@Field
	private String status;
	@Field
	private int bytes;
	@Field
	private String method;
	@Field
	private String url;
	@Field
	private String rfc93;
	@Field
	private String peerhost;
	@Field
	private String type;
	@Field
	private String ip;
	@Field
	private String mac;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTele() {
		return tele;
	}

	public void setTele(String tele) {
		this.tele = tele;
	}

	public long getTime() {
		return time;
	}

	public void setTime(long time) {
		this.time = time;
	}

	public int getElapsed() {
		return elapsed;
	}

	public void setElapsed(int elapsed) {
		this.elapsed = elapsed;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getBytes() {
		return bytes;
	}

	public void setBytes(int bytes) {
		this.bytes = bytes;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getRfc93() {
		return rfc93;
	}

	public void setRfc93(String rfc93) {
		this.rfc93 = rfc93;
	}

	public String getPeerhost() {
		return peerhost;
	}

	public void setPeerhost(String peerhost) {
		this.peerhost = peerhost;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getMac() {
		return mac;
	}

	public void setMac(String mac) {
		this.mac = mac;
	}
}
