package org.meruvian.yama.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.bogor.itu.entity.admin.User;

import org.apache.struts2.ServletActionContext;
import org.meruvian.yama.security.user.BackendUser;

public class SessionCredentials {
	public static final String YAMA_SECURITY_USER = "YAMA_SECURITY_USER";

	public static HttpServletRequest request() {
		return ServletActionContext.getRequest();
	}

	public static HttpServletResponse response() {
		return ServletActionContext.getResponse();
	}

	public static HttpSession session() {
		return request().getSession(true);
	}

	public static BackendUser currentUser() {
		return (BackendUser) session().getAttribute(YAMA_SECURITY_USER);
	}
}
