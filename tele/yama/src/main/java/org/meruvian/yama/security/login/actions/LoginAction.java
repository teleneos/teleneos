/**
 * Copyright 2012 BlueOxygen Technology
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
package org.meruvian.yama.security.login.actions;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.swing.JOptionPane;

import org.meruvian.inca.struts2.rest.ActionResult;
import org.meruvian.inca.struts2.rest.annotation.Action;
import org.meruvian.inca.struts2.rest.annotation.Action.HttpMethod;
import org.meruvian.yama.actions.DefaultAction;
import org.meruvian.yama.security.SessionCredentials;
import org.meruvian.yama.security.user.BackendUser;
import org.springframework.beans.factory.annotation.Value;

import com.opensymphony.xwork2.ModelDriven;

/**
 * @author Dian Aditya
 * 
 */
@Action
public class LoginAction extends DefaultAction implements
		ModelDriven<Map<String, Object>> {
	
	private static final long serialVersionUID = 1048880830327706161L;

	private Map<String, Object> model = new HashMap<String, Object>();

	@Action(method = HttpMethod.GET)
	public ActionResult loginform() throws Exception {
		BackendUser user = SessionCredentials.currentUser();
		if (user == null) {
			return new ActionResult("freemarker", "/view/security/login.ftl");
		} else {
			if ("USER".equals(user.getRole())) {
				return new ActionResult("redirect", "/user");
			}
			if ("MASTER".equals(user.getRole())) {
				return new ActionResult("redirect", "/master");
			}
			return new ActionResult("freemarker",
					"/view/admin/backend-index.ftl");
		}
	}

	@Override
	public Map<String, Object> getModel() {
		return model;
	}	
}
