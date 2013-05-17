package net.bogor.itu.action.user;

import net.bogor.itu.entity.radius.UserPackage;

public class UserActionModel {
	private String host;
	private String port;
	private String uamService;
	private String res;
	private String nasid;
	private String reason;
	private String userurl;
	private String mac;
	private String sessionid;
	private String uamport;
	private String ip;
	private String uamip;
	private String challenge;
	private String md;
	private String called;
	private UserPackage userPackage = new UserPackage();

	public String getRes() {
		return res;
	}

	public void setRes(String res) {
		this.res = res;
	}

	public String getNasid() {
		return nasid;
	}

	public void setNasid(String nasid) {
		this.nasid = nasid;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getUserurl() {
		return userurl;
	}

	public void setUserurl(String userurl) {
		this.userurl = userurl;
	}

	public String getMac() {
		return mac;
	}

	public void setMac(String mac) {
		this.mac = mac;
	}

	public String getSessionid() {
		return sessionid;
	}

	public void setSessionid(String sessionid) {
		this.sessionid = sessionid;
	}

	public String getUamport() {
		return uamport;
	}

	public void setUamport(String uamport) {
		this.uamport = uamport;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getUamip() {
		return uamip;
	}

	public void setUamip(String uamip) {
		this.uamip = uamip;
	}

	public String getChallenge() {
		return challenge;
	}

	public void setChallenge(String challenge) {
		this.challenge = challenge;
	}

	public String getMd() {
		return md;
	}

	public void setMd(String md) {
		this.md = md;
	}

	public String getCalled() {
		return called;
	}

	public void setCalled(String called) {
		this.called = called;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
	}

	public String getUamService() {
		return uamService;
	}

	public void setUamService(String uamService) {
		this.uamService = uamService;
	}

	public UserPackage getUserPackage() {
		return userPackage;
	}

	public void setUserPackage(UserPackage userPackage) {
		this.userPackage = userPackage;
	}
}
