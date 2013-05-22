package org.teleneos.pos.item;

import javax.inject.Inject;

import org.meruvian.inca.struts2.rest.ActionResult;
import org.meruvian.inca.struts2.rest.annotation.Action;
import org.meruvian.inca.struts2.rest.annotation.Action.HttpMethod;
import org.meruvian.inca.struts2.rest.annotation.Result;
import org.meruvian.inca.struts2.rest.annotation.Results;
import org.meruvian.yama.actions.DefaultAction;

import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.validator.annotations.RequiredFieldValidator;
import com.opensymphony.xwork2.validator.annotations.RequiredStringValidator;
import com.opensymphony.xwork2.validator.annotations.Validations;

/**
 * @author Edy Setiawan
 * 
 */
@Action(name = "/pos/item")
@Results({ @Result(name = DefaultAction.INPUT, type = "freemarker", location = "/view/pos/item/item-form.ftl") })
public class ItemAction extends DefaultAction implements
		ModelDriven<ItemActionModel> {
	private ItemActionModel model = new ItemActionModel();
	private ActionResult redirectToIndex = new ActionResult("redirect",
			"/pos/item");

	@Inject
	private ItemService itemService;

	@Action
	public ActionResult itemList() {
		model.setItems(itemService.findByKeyword(model.getQ(), null, "ASC",
				model.getMax(), model.getPage() - 1));

		return new ActionResult("freemarker", "/view/pos/item/item-list.ftl");
	}

	@Action(name = "/add", method = HttpMethod.GET)
	public ActionResult addForm() {
		return new ActionResult("freemarker", "/view/pos/item/item-form.ftl");
	}

	@Action(name = "/edit/{item.id}", method = HttpMethod.GET)
	public ActionResult editForm() {
		String id = model.getItem().getId();
		if (id == null)
			return redirectToIndex;

		model.setItem(itemService.findById(id));
		if (model.getItem() == null)
			return redirectToIndex;

		return new ActionResult("freemarker", "/view/pos/item/item-form.ftl");
	}

	@Action(name = "/add", method = HttpMethod.POST)
	@Validations(requiredStrings = {
			@RequiredStringValidator(fieldName = "item.code", trim = true, key = "message.pos.item.code.notnull"),
			@RequiredStringValidator(fieldName = "item.name", trim = true, key = "message.pos.item.name.notnull"), }, requiredFields = {
			@RequiredFieldValidator(fieldName = "item.price", key = "message.pos.item.price.notnull"),
			@RequiredFieldValidator(fieldName = "item.uom.name", key = "message.pos.item.uom.notnull"),
			@RequiredFieldValidator(fieldName = "item.category.name", key = "message.pos.item.category.notnull"), })
	public ActionResult updateService() {
		return addService();
	}

	@Action(name = "/add", method = HttpMethod.POST)
	@Validations(requiredStrings = {
			@RequiredStringValidator(fieldName = "item.code", trim = true, key = "message.pos.item.code.notnull"),
			@RequiredStringValidator(fieldName = "item.name", trim = true, key = "message.pos.item.name.notnull"), }, requiredFields = {
			@RequiredFieldValidator(fieldName = "item.price", key = "message.pos.item.price.notnull"),
			@RequiredFieldValidator(fieldName = "item.uom.name", key = "message.pos.item.uom.notnull"),
			@RequiredFieldValidator(fieldName = "item.category.name", key = "message.pos.item.category.notnull"), })
	public ActionResult addService() {
		itemService.save(model.getItem());

		return redirectToIndex;
	}

	@Override
	public ItemActionModel getModel() {
		return model;
	}

}
