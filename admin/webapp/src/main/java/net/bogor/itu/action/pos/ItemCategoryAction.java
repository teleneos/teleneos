package net.bogor.itu.action.pos;

import javax.inject.Inject;

import net.bogor.itu.service.pos.ItemCategoryService;

import org.meruvian.inca.struts2.rest.ActionResult;
import org.meruvian.inca.struts2.rest.annotation.Action;
import org.meruvian.inca.struts2.rest.annotation.Action.HttpMethod;
import org.meruvian.inca.struts2.rest.annotation.Result;
import org.meruvian.inca.struts2.rest.annotation.Results;
import org.meruvian.yama.actions.DefaultAction;

import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.validator.annotations.RequiredStringValidator;
import com.opensymphony.xwork2.validator.annotations.Validations;

@Action(name = "/pos/itemcategory")
@Results({ @Result(name = DefaultAction.INPUT, type = "freemarker", location = "/view/pos/itemcategory/itemcategory-form.ftl") })
public class ItemCategoryAction extends DefaultAction implements ModelDriven<ItemCategoryActionModel>{
	private ItemCategoryActionModel model = new ItemCategoryActionModel();
	private ActionResult redirectToIndex = new ActionResult("redirect",
			"/pos/itemcategory");
	
	@Inject
	private ItemCategoryService itemCategoryService;

	@Action
	public ActionResult itemCategoryList() {
		model.setItemCategories(itemCategoryService.findByKeyword(model.getQ(),null,"ASC", model.getMax(),
				model.getPage() - 1));

		return new ActionResult("freemarker",
				"/view/pos/itemcategory/itemcategory-list.ftl");
	}

	@Action(name = "/add", method = HttpMethod.GET)
	public ActionResult addForm() {
		return new ActionResult("freemarker",
				"/view/pos/itemcategory/itemcategory-form.ftl");
	}

	@Action(name = "/edit/{itemCategory.id}", method = HttpMethod.GET)
	public ActionResult editForm() {
		String id = model.getItemCategory().getId();
		if (id == null)
			return redirectToIndex;

		model.setItemCategory(itemCategoryService.findById(id));
		if (model.getItemCategory() == null)
			return redirectToIndex;

		return new ActionResult("freemarker",
				"/view/pos/itemcategory/itemcategory-form.ftl");
	}

	@Action(name = "/edit/{itemCategory.id}", method = HttpMethod.POST)
	@Validations(requiredStrings = { @RequiredStringValidator(fieldName = "itemCategory.name", trim = true, key = "message.admin.itemcategory.name.notnull")})
	public ActionResult updateService() {
		return addService();
	}

	@Action(name = "/add", method = HttpMethod.POST)
	@Validations(requiredStrings = { @RequiredStringValidator(fieldName = "itemCategory.name", trim = true, key = "message.admin.itemcategory.name.notnull")})
	public ActionResult addService() {
		itemCategoryService.save(model.getItemCategory());

		return redirectToIndex;
	}
	
	@Override
	public ItemCategoryActionModel getModel() {
		return model;
	}

}
