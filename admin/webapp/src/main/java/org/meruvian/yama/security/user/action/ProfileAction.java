/**
 * Copyright 2013 Meruvian
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
package org.meruvian.yama.security.user.action;

import javax.inject.Inject;

import org.meruvian.inca.struts2.rest.ActionResult;
import org.meruvian.inca.struts2.rest.annotation.Action;
import org.meruvian.inca.struts2.rest.annotation.Action.HttpMethod;
import org.meruvian.yama.actions.DefaultAction;
import org.meruvian.yama.security.SessionCredentials;
import org.meruvian.yama.security.user.BackendUser;
import org.meruvian.yama.security.user.service.BackendUserService;

import com.opensymphony.xwork2.ModelDriven;

/**
 * @author Dian Aditya
 * 
 */
@Action(name = "/security/profile")
public class ProfileAction extends DefaultAction implements
		ModelDriven<BackendUser> {

	@Inject
	private BackendUserService userService;
	private BackendUser user = new BackendUser();

	@Action(name = "/edit", method = HttpMethod.GET)
	public ActionResult edit() {
		user = userService.getByUsername(SessionCredentials.currentUser()
				.getUsername());

		return new ActionResult("freemarker",
				"/view/security/edit-profile-form.ftl");
	}

	@Action(name = "/edit", method = HttpMethod.POST)
	public ActionResult editSubmit() {
		userService.save(user);

		return new ActionResult("redirect", "/security/profile/edit?success");
	}

	@Override
	public BackendUser getModel() {
		return user;
	}
}