package org.teleneos.radius.user;

import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;
import org.meruvian.inca.struts2.rest.ActionResult;
import org.meruvian.inca.struts2.rest.annotation.Action;
import org.meruvian.inca.struts2.rest.annotation.Action.HttpMethod;
import org.meruvian.yama.actions.DefaultAction;
import org.springframework.beans.factory.annotation.Value;
import org.teleneos.radius.userpackage.UserPackageService;

import com.opensymphony.xwork2.ModelDriven;

/**
 * @author Dian Aditya
 * 
 */
@Action(name = "/user/signin")
public class UserLoginAction extends DefaultAction implements
		ModelDriven<UserActionModel> {
	@Value("${chilli.controller.host}")
	private String host;

	@Value("${chilli.controller.port}")
	private String port;

	@Value("${chilli.controller.uamservice.url}")
	private String uamService;

	@Inject
	private UserPackageService packageService;

	private UserActionModel model = new UserActionModel();

	@Action(method = HttpMethod.GET)
	public ActionResult signinForm() {
		model.setHost(host);
		model.setPort(port);
		model.setUamService(uamService);

		return new ActionResult("/view/security/user/user-login-form.ftl");
	}

	@Action(name = "/status")
	public ActionResult status() {
		model.setHost(host);
		model.setPort(port);
		model.setUamService(uamService);

		if (!StringUtils.isBlank(model.getUserurl()))
			model.setUserPackage(packageService.findActivePackage(model
					.getUserurl()));

		return new ActionResult("/view/security/user/user-status-popup.ftl");
	}

	@Override
	public UserActionModel getModel() {
		return model;
	}
}
