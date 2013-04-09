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
import org.meruvian.yama.security.user.BackendUser;
import org.meruvian.yama.security.user.service.BackendUserService;

import com.opensymphony.xwork2.ModelDriven;

/**
 * @author Dian Aditya
 * 
 */
//@Action(name = "/admin/user")
public class UserAdminAction extends DefaultAction implements
		ModelDriven<UserAdminActionModel> {
	@Inject
	private BackendUserService userService;

	private UserAdminActionModel model = new UserAdminActionModel();

	@Action
	public ActionResult users() {
		model.setUsers(userService.find(model.q, model.limit, model.page - 1));

		return new ActionResult("freemarker", "/view/admin/user-list.ftl");
	}

	@Action(name = "/add", method = HttpMethod.GET)
	public ActionResult add() {
		return new ActionResult("freemarker", "/view/admin/user-form.ftl");
	}

	@Action(name = "/add", method = HttpMethod.POST)
	public ActionResult addSubmit() {
		editSubmit();

		return new ActionResult("redirect", "/admin/user/edit/"
				+ model.getUser().getId());
	}

	@Action(name = "/edit/{q}", method = HttpMethod.GET)
	public ActionResult edit() {
		model.setUser(userService.getById(model.q));

		return new ActionResult("freemarker", "/view/admin/user-form.ftl");
	}

	@Action(name = "/edit/{user.id}", method = HttpMethod.POST)
	public ActionResult editSubmit() {
		BackendUser user = model.getUser();
		user = userService.save(user);

		return new ActionResult("redirect", "/admin/user/edit/" + user.getId()
				+ "?success");
	}

	@Override
	public UserAdminActionModel getModel() {
		return this.model;
	}
}