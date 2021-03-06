package org.meruvian.yama.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.meruvian.yama.security.user.BackendUser;
import org.meruvian.yama.security.user.User;

public class SessionCredentials {
	public static final String YAMA_SECURITY_USER = "YAMA_SECURITY_USER";
	public static final String YAMA_SECURITY_USER_DETAIL = "YAMA_SECURITY_USER_DETAIL";

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
	
	public static User currentUserDetail() {
		return (User) session().getAttribute(YAMA_SECURITY_USER_DETAIL);
	}
}
