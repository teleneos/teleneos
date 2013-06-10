/**
 * 
 */
package org.teleneos.noc.telecentre;

import java.util.Map;

import javax.inject.Inject;

import org.apache.struts2.interceptor.SessionAware;
import org.meruvian.inca.struts2.rest.ActionResult;
import org.meruvian.inca.struts2.rest.annotation.Action;
import org.meruvian.inca.struts2.rest.annotation.Action.HttpMethod;
import org.meruvian.inca.struts2.rest.annotation.Result;
import org.meruvian.inca.struts2.rest.annotation.Results;
import org.meruvian.yama.actions.DefaultAction;
import org.meruvian.yama.persistence.NocEntityListWrapper;

import com.opensymphony.xwork2.ModelDriven;
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
@Action(name = "/tele")
@Results({ @Result(name = DefaultAction.INPUT, type = "freemarker", location = "/view/telecentre/telecentre-form.ftl") })
public class TelecentreAction extends DefaultAction implements
		ModelDriven<TelecentreActionModel>, SessionAware {

	private static final String NEXT_COOKIE = "NEXT_COOKIE";
	private static final String PREV_COOKIE = "PREV_COOKIE";

	private TelecentreActionModel model = new TelecentreActionModel();
	private Map<String, Object> session;

	@Inject
	private TelecentreService teleService;

	@Action
	public ActionResult index() {
		byte[] cookie = null;
		if (model.getPage() == 0)
			cookie = (byte[]) session.get(NEXT_COOKIE);

		if (model.getPage() == -1)
			cookie = (byte[]) session.get(PREV_COOKIE);

		model.setPage(2);

		model.setTelecentres((NocEntityListWrapper<Telecentre>) teleService
				.findByName(model.getQ(), model.getMax(), model.getPage(),
						cookie));

		session.put(PREV_COOKIE, cookie);
		session.put(NEXT_COOKIE, model.getTelecentres().getCookie());

		return new ActionResult("freemarker",
				"/view/telecentre/telecentre-list.ftl");
	}

	@Action(name = "/add", method = HttpMethod.GET)
	public String add() {
		return INPUT;
	}

	@Action(name = "/add", method = HttpMethod.POST)
	@Validations(requiredStrings = {
			@RequiredStringValidator(fieldName = "telecentre.name", trim = true, key = "message.telecentre.name.notnull"),
			@RequiredStringValidator(fieldName = "telecentre.phone", trim = true, key = "message.telecentre.phone.notnull"),
			@RequiredStringValidator(fieldName = "telecentre.email", trim = true, key = "message.telecentre.email.notnull"),
			@RequiredStringValidator(fieldName = "telecentre.address.street1", trim = true, key = "message.telecentre.address.notnull") }, regexFields = { @RegexFieldValidator(fieldName = "telecentre.phone", expression = "^([0-9]*)$", key = "message.telecentre.phone.notvalid") }, emails = { @EmailValidator(fieldName = "telecentre.email", key = "message.telecentre.email.notvalid") })
	public ActionResult submit() {
		Telecentre telecentre = model.getTelecentre();
		telecentre = teleService.save(telecentre);

		return new ActionResult("redirect", "/tele/edit/" + telecentre.getId()
				+ "?success");
	}

	@Action(name = "/edit/{q}", method = HttpMethod.GET)
	public String edit() {
		model.setTelecentre(teleService.findById(model.getQ()));

		return INPUT;
	}

	@Action(name = "/edit/{q}", method = HttpMethod.POST)
	@Validations(requiredStrings = {
			@RequiredStringValidator(fieldName = "telecentre.name", trim = true, key = "message.telecentre.name.notnull"),
			@RequiredStringValidator(fieldName = "telecentre.phone", trim = true, key = "message.telecentre.phone.notnull"),
			@RequiredStringValidator(fieldName = "telecentre.email", trim = true, key = "message.telecentre.email.notnull"),
			@RequiredStringValidator(fieldName = "telecentre.address.street1", trim = true, key = "message.telecentre.address.notnull") }, regexFields = { @RegexFieldValidator(fieldName = "telecentre.phone", expression = "^([0-9]*)$", key = "message.telecentre.phone.notvalid") }, emails = { @EmailValidator(fieldName = "telecentre.email", key = "message.telecentre.email.notvalid") })
	public ActionResult editSubmit() {
		return submit();
	}

	@Override
	public TelecentreActionModel getModel() {
		return model;
	}

	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

}