package org.meruvian.yama.configuration;

import javax.inject.Inject;

import org.meruvian.inca.struts2.rest.ActionResult;
import org.meruvian.inca.struts2.rest.annotation.Action;
import org.meruvian.inca.struts2.rest.annotation.Action.HttpMethod;
import org.meruvian.inca.struts2.rest.annotation.Result;
import org.meruvian.inca.struts2.rest.annotation.Results;
import org.meruvian.yama.actions.DefaultAction;

import com.opensymphony.xwork2.ModelDriven;

@Action(name = "/admin/configuration")
@Results({ @Result(name = DefaultAction.INDEX, type = "freemarker", location = "/view/admin/configuration/configuration-form.ftl") })
public class ConfigurationAction extends DefaultAction implements
		ModelDriven<ConfigurationActionModel> {

	private static final long serialVersionUID = -2164087272193259057L;

	@Inject
	private ConfigurationService configurationService;

	private ConfigurationActionModel model = new ConfigurationActionModel();
	
	@Action
	public String form() {
		model.getConfigurations().add(configurationService.findByKey("noc.configuration.report.availability.refreshinterfal"));
		model.getConfigurations().add(configurationService.findByKey("noc.configuration.report.monitoring.refreshinterfal"));
		model.getConfigurations().add(configurationService.findByKey("noc.configuration.report.performance.refreshinterfal"));
		model.getConfigurations().add(configurationService.findByKey("noc.configuration.report.utilization.refreshinterfal"));
		model.getConfigurations().add(configurationService.findByKey("noc.configuration.report.log.refreshinterfal"));
		return INDEX;
	}
	
	@Action(name = "add", method = HttpMethod.POST)
	public ActionResult addAnswer() {
		for (Configuration c : model.getConfigurations()) {
			configurationService.save(c);
		}
		return new ActionResult("redirect", "/admin/configuration");
	}
	
	@Override
	public ConfigurationActionModel getModel() {
		return model;
	}

}
