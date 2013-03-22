package net.bogor.itu.action.pos;

import javax.inject.Inject;

import net.bogor.itu.service.pos.UnitOfMeasureService;

import org.meruvian.inca.struts2.rest.ActionResult;
import org.meruvian.inca.struts2.rest.annotation.Action;
import org.meruvian.inca.struts2.rest.annotation.Action.HttpMethod;
import org.meruvian.inca.struts2.rest.annotation.Result;
import org.meruvian.inca.struts2.rest.annotation.Results;
import org.meruvian.yama.actions.DefaultAction;

import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.validator.annotations.RequiredStringValidator;
import com.opensymphony.xwork2.validator.annotations.Validations;

@Action(name = "/pos/uom")
@Results({ @Result(name = DefaultAction.INPUT, type = "freemarker", location = "/view/pos/uom/uom-form.ftl") })
public class UnitOfMeasureAction extends DefaultAction implements ModelDriven<UnitOfMeasureActionModel>{
	private UnitOfMeasureActionModel model = new UnitOfMeasureActionModel();
	private ActionResult redirectToIndex = new ActionResult("redirect",
			"/pos/uom");
	
	@Inject
	private UnitOfMeasureService uomService;

	@Action
	public ActionResult uomList() {
		model.setUoms(uomService. findByKeyword(model.getQ(),null,"ASC", model.getMax(),
				model.getPage() - 1));

		return new ActionResult("freemarker",
				"/view/pos/uom/uom-list.ftl");
	}

	@Action(name = "/add", method = HttpMethod.GET)
	public ActionResult addForm() {
		return new ActionResult("freemarker",
				"/view/pos/uom/uom-form.ftl");
	}

	@Action(name = "/edit/{uom.id}", method = HttpMethod.GET)
	public ActionResult editForm() {
		String id = model.getUom().getId();
		if (id == null)
			return redirectToIndex;

		model.setUom(uomService.findById(id));
		if (model.getUom() == null)
			return redirectToIndex;

		return new ActionResult("freemarker",
				"/view/pos/uom/uom-form.ftl");
	}

	@Action(name = "/edit/{uom.id}", method = HttpMethod.POST)
	@Validations(requiredStrings = { @RequiredStringValidator(fieldName = "uom.name", trim = true, key = "message.admin.uom.name.notnull")})
	public ActionResult updateUom() {
		return addUom();
	}

	@Action(name = "/add", method = HttpMethod.POST)
	@Validations(requiredStrings = { @RequiredStringValidator(fieldName = "uom.name", trim = true, key = "message.admin.uom.name.notnull")})
	public ActionResult addUom() {
		uomService.save(model.getUom());

		return redirectToIndex;
	}
	
	@Override
	public UnitOfMeasureActionModel getModel() {
		return model;
	}

}
