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

import net.bogor.itu.entity.admin.User;
import net.bogor.itu.service.admin.UserService;

import org.meruvian.inca.struts2.rest.ActionResult;
import org.meruvian.inca.struts2.rest.annotation.Action;
import org.meruvian.inca.struts2.rest.annotation.Action.HttpMethod;
import org.meruvian.inca.struts2.rest.annotation.Result;
import org.meruvian.inca.struts2.rest.annotation.Results;
import org.meruvian.yama.actions.DefaultAction;
import org.meruvian.yama.security.SessionCredentials;

import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.ognl.OgnlValueStack;
import com.opensymphony.xwork2.validator.annotations.EmailValidator;
import com.opensymphony.xwork2.validator.annotations.RegexFieldValidator;
import com.opensymphony.xwork2.validator.annotations.RequiredFieldValidator;
import com.opensymphony.xwork2.validator.annotations.RequiredStringValidator;
import com.opensymphony.xwork2.validator.annotations.StringLengthFieldValidator;
import com.opensymphony.xwork2.validator.annotations.Validations;

/**
 * @author Dian Aditya
 * 
 */
@Action(name = "/security/profile")
public class ProfileAction extends DefaultAction implements ModelDriven<User> {

	@Inject
	private UserService userService;
	private User user = new User();

	@Action(name = "/edit", method = HttpMethod.GET)
	public ActionResult edit() {
		user = userService.findByUsername(SessionCredentials.currentUser()
				.getUsername());

		return new ActionResult("freemarker",
				"/view/security/edit-profile-form.ftl");
	}

	@Action(name = "/edit", method = HttpMethod.POST)
	@Results({ @Result(name = INPUT, type = "freemarker", location = "/view/security/edit-profile-form.ftl") })
	@Validations(requiredStrings = {
			@RequiredStringValidator(fieldName = "model.user.username", trim = true, key = "message.admin.user.username.notnull"),
			@RequiredStringValidator(fieldName = "model.name.first", trim = true, key = "message.admin.user.firstname.notnull"),
			@RequiredStringValidator(fieldName = "model.idcard", trim = true, key = "message.admin.user.idcard.notnull"),
			@RequiredStringValidator(fieldName = "model.address.street1", trim = true, key = "message.admin.user.street1.notnull"),
			@RequiredStringValidator(fieldName = "model.user.email", trim = true, key = "message.admin.user.email.notnull") }, regexFields = {
			@RegexFieldValidator(fieldName = "model.user.username", expression = "^[a-z][a-z0-9]+(?:[_][a-z0-9]+)*$", key = "message.admin.user.username.invalidcharacters"),
			@RegexFieldValidator(fieldName = "model.idcard", expression = "^([0-9]*)$", key = "message.admin.user.idcard.length"),
			@RegexFieldValidator(fieldName = "model.phone", expression = "^([0-9]*)$", key = "message.admin.user.phone.notvalid") }, emails = { @EmailValidator(fieldName = "model.user.email", key = "message.admin.user.email.notvalid") }, requiredFields = { @RequiredFieldValidator(fieldName = "model.birthDate", key = "message.admin.user.birthdate.notvalid") }, stringLengthFields = { @StringLengthFieldValidator(fieldName = "model.idcard", key = "message.admin.user.idcard.length", minLength = "13", maxLength = "13", trim = true) })
	public ActionResult editSubmit() {
		userService.save(user);

		return new ActionResult("redirect", "/security/profile/edit?success");
	}

	@Override
	public User getModel() {
		return user;
	}
}