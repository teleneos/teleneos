package org.meruvian.yama.configuration;

import javax.inject.Inject;

import org.meruvian.inca.struts2.rest.ActionResult;
import org.meruvian.inca.struts2.rest.annotation.Action;
import org.meruvian.inca.struts2.rest.annotation.Action.HttpMethod;
import org.meruvian.inca.struts2.rest.annotation.Result;
import org.meruvian.inca.struts2.rest.annotation.Results;
import org.meruvian.yama.actions.DefaultAction;
import org.springframework.beans.factory.annotation.Value;
import org.teleneos.noc.telecentre.Telecentre;
import org.teleneos.noc.telecentre.TelecentreService;

import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.validator.annotations.EmailValidator;
import com.opensymphony.xwork2.validator.annotations.RegexFieldValidator;
import com.opensymphony.xwork2.validator.annotations.RequiredStringValidator;
import com.opensymphony.xwork2.validator.annotations.Validations;

@Action(name = "/admin/configuration")
@Results({ @Result(name = DefaultAction.INDEX, type = "freemarker", location = "/view/admin/configuration/configuration-form.ftl"),
	@Result(name = DefaultAction.INPUT, type = "freemarker", location = "/view/admin/configuration/configuration-form.ftl")})
public class ConfigurationAction extends DefaultAction implements
		ModelDriven<ConfigurationActionModel> {

	private static final long serialVersionUID = -2164087272193259057L;

	@Inject
	private ConfigurationService configurationService;

	private ConfigurationActionModel model = new ConfigurationActionModel();
	
	@Inject
	private TelecentreService telecentreService;
	
	@Value("${telecentre.public_key}")
	private String telecentrePublic;
	
	@Action
	public String form() {
		model.setTelecentre(telecentreService.findById(telecentrePublic));
		model.getConfigurations().add(configurationService.findByKey("noc.configuration.report.availability.refreshinterfal"));
		model.getConfigurations().add(configurationService.findByKey("noc.configuration.report.monitoring.refreshinterfal"));
		model.getConfigurations().add(configurationService.findByKey("noc.configuration.report.performance.refreshinterfal"));
		model.getConfigurations().add(configurationService.findByKey("noc.configuration.report.utilization.refreshinterfal"));
		model.getConfigurations().add(configurationService.findByKey("noc.configuration.report.log.refreshinterfal"));
		return INDEX;
	}
	
	@Action(name = "add", method = HttpMethod.POST)
	@Validations(
			requiredStrings = {
			@RequiredStringValidator(fieldName = "telecentre.name", trim = true, key = "message.telecentre.name.notnull"),
			@RequiredStringValidator(fieldName = "telecentre.phone", trim = true, key = "message.telecentre.phone.notnull"),
			@RequiredStringValidator(fieldName = "telecentre.email", trim = true, key = "message.telecentre.email.notnull"),
			@RequiredStringValidator(fieldName = "telecentre.address.zip", trim = true, key = "message.telecentre.zip.notnull"),
			@RequiredStringValidator(fieldName = "telecentre.address.street1", trim = true, key = "message.telecentre.address.notnull") }, 
			regexFields = { 
					@RegexFieldValidator(fieldName = "configurations[0].value", expression = "^([0-9]*)$", key = "message.telecentre.availability"), 
					@RegexFieldValidator(fieldName = "configurations[1].value", expression = "^([0-9]*)$", key = "message.telecentre.monitoring"), 
					@RegexFieldValidator(fieldName = "configurations[2].value", expression = "^([0-9]*)$", key = "message.telecentre.performance"), 
					@RegexFieldValidator(fieldName = "configurations[3].value", expression = "^([0-9]*)$", key = "message.telecentre.utilization"), 
					@RegexFieldValidator(fieldName = "configurations[4].value", expression = "^([0-9]*)$", key = "message.telecentre.log"), 
			@RegexFieldValidator(fieldName = "telecentre.address.zip", expression = "^([0-9]*)$", key = "message.telecentre.address.zip.notvalid"), 
			@RegexFieldValidator(fieldName = "telecentre.phone", expression = "^([0-9]*)$", key = "message.telecentre.phone.notvalid") }, 
			emails = { @EmailValidator(fieldName = "telecentre.email", key = "message.telecentre.email.notvalid") })
	public ActionResult addAnswer() {
		
		Telecentre telecentre = model.getTelecentre();
		telecentreService.save(telecentre);
		
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
