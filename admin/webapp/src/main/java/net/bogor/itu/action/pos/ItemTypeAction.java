package net.bogor.itu.action.pos;

import javax.inject.Inject;

import net.bogor.itu.service.pos.ItemTypeService;

import org.meruvian.inca.struts2.rest.ActionResult;
import org.meruvian.inca.struts2.rest.annotation.Action;
import org.meruvian.inca.struts2.rest.annotation.Action.HttpMethod;
import org.meruvian.inca.struts2.rest.annotation.Result;
import org.meruvian.inca.struts2.rest.annotation.Results;
import org.meruvian.yama.actions.DefaultAction;

import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.validator.annotations.RequiredStringValidator;
import com.opensymphony.xwork2.validator.annotations.Validations;

@Action(name = "/pos/itemtype")
@Results({ @Result(name = DefaultAction.INPUT, type = "freemarker", location = "/view/pos/itemtype/itemtype-form.ftl") })
public class ItemTypeAction extends DefaultAction implements ModelDriven<ItemTypeActionModel>{
	private ItemTypeActionModel model = new ItemTypeActionModel();
	private ActionResult redirectToIndex = new ActionResult("redirect",
			"/pos/itemtype");
	
	@Inject
	private ItemTypeService itemTypeService;

	@Action
	public ActionResult itemTypeList() {
		model.setItemTypes(itemTypeService.findByKeyword(model.getQ(),null,"ASC", model.getMax(),
				model.getPage() - 1));

		return new ActionResult("freemarker",
				"/view/pos/itemtype/itemtype-list.ftl");
	}

	@Action(name = "/add", method = HttpMethod.GET)
	public ActionResult addForm() {
		return new ActionResult("freemarker",
				"/view/pos/itemtype/itemtype-form.ftl");
	}

	@Action(name = "/edit/{itemType.id}", method = HttpMethod.GET)
	public ActionResult editForm() {
		String id = model.getItemType().getId();
		if (id == null)
			return redirectToIndex;

		model.setItemType(itemTypeService.findById(id));
		if (model.getItemType() == null)
			return redirectToIndex;

		return new ActionResult("freemarker",
				"/view/pos/itemtype/itemtype-form.ftl");
	}

	@Action(name = "/edit/{itemType.id}", method = HttpMethod.POST)
	@Validations(requiredStrings = { @RequiredStringValidator(fieldName = "itemType.name", trim = true, key = "message.admin.itemtype.name.notnull")})
	public ActionResult updateItemType() {
		return addItemType();
	}

	@Action(name = "/add", method = HttpMethod.POST)
	@Validations(requiredStrings = { @RequiredStringValidator(fieldName = "itemType.name", trim = true, key = "message.admin.itemtype.name.notnull")})
	public ActionResult addItemType() {
		itemTypeService.save(model.getItemType());

		return redirectToIndex;
	}
	
	@Override
	public ItemTypeActionModel getModel() {
		return model;
	}

}
