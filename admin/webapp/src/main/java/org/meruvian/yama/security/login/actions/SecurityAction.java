/**
 * Copyright 2012 Meruvian
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

import javax.inject.Inject;

import net.bogor.itu.service.admin.UserService;

import org.meruvian.inca.struts2.rest.ActionResult;
import org.meruvian.inca.struts2.rest.annotation.Action;
import org.meruvian.inca.struts2.rest.annotation.Action.HttpMethod;
import org.meruvian.yama.actions.DefaultAction;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.ldap.userdetails.InetOrgPerson;

/**
 * @author Dian Aditya
 * 
 */
@Action(name = "/security")
public class SecurityAction extends DefaultAction {
	private String password = "";

	@Inject
	private UserService userService;

	@Action(name = "/password", method = HttpMethod.POST)
	public ActionResult changePassword() {
		if (!password.trim().equalsIgnoreCase("")) {
			Object principal = SecurityContextHolder.getContext()
					.getAuthentication().getPrincipal();
			if (principal instanceof User) {
				User user = (User) principal;
				userService.changePassword(user.getUsername(), password);
			} else if (principal instanceof InetOrgPerson) {
				InetOrgPerson person = (InetOrgPerson) principal;
				userService.changePassword(person.getUsername(), password);
			}
		} else {
			return new ActionResult("freemarker",
					"/view/security/change-password-form.ftl");
		}

		return new ActionResult("freemarker",
				"/view/security/change-password-success.ftl");
	}

	@Action(name = "password", method = HttpMethod.GET)
	public ActionResult changePasswordForm() {
		return new ActionResult("freemarker",
				"/view/security/change-password-form.ftl");
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
