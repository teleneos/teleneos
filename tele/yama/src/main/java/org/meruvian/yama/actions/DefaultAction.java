/**
 * Copyright 2011 Meruvian
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0 
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.meruvian.yama.actions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.meruvian.yama.security.SessionCredentials;
import org.meruvian.yama.security.user.BackendUser;

import com.opensymphony.xwork2.ActionSupport;

/**
 * @author Dian Aditya
 * 
 */
public class DefaultAction extends ActionSupport implements
		ServletRequestAware, ServletResponseAware {
	public static final String INDEX = "INDEX";
	public static final String CREATE = "CREATE";
	public static final String NEW = "NEW";
	public static final String EDIT = "EDIT";
	public static final String SHOW = "SHOW";
	public static final String DELETE = "DELETE";

	protected HttpServletResponse response;
	protected HttpServletRequest request;

	@Override
	public void setServletResponse(HttpServletResponse response) {
		this.response = response;
	}

	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}
	
	public BackendUser getCurrentUser() {
		return SessionCredentials.currentUser();
	}
}
