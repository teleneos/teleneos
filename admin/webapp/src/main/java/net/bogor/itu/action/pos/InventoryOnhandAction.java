package net.bogor.itu.action.pos;

import javax.inject.Inject;

import net.bogor.itu.service.pos.InventoryOnhandService;
import net.bogor.itu.service.pos.ItemService;

import org.meruvian.inca.struts2.rest.ActionResult;
import org.meruvian.inca.struts2.rest.annotation.Action;
import org.meruvian.inca.struts2.rest.annotation.Action.HttpMethod;
import org.meruvian.inca.struts2.rest.annotation.Result;
import org.meruvian.inca.struts2.rest.annotation.Results;
import org.meruvian.yama.actions.DefaultAction;

import com.opensymphony.xwork2.ModelDriven;

@Action(name = "/pos/inventoryonhand")
@Results({ @Result(name = DefaultAction.INPUT, type = "freemarker", location = "/view/pos/inventoryonhand/inventoryonhand-form.ftl") })
public class InventoryOnhandAction extends DefaultAction implements ModelDriven<InventoryOnhandActionModel>{
	private InventoryOnhandActionModel model = new InventoryOnhandActionModel();
	private ActionResult redirectToIndex = new ActionResult("redirect",
			"/pos/inventoryonhand");
	
	@Inject
	private InventoryOnhandService inventoryOnhandService;
	
	@Action
	public ActionResult inventoryOnhandList() {
		model.setInventoryOnhands(inventoryOnhandService.findByKeyword(model.getQ(),null,"ASC", model.getMax(),
				model.getPage() - 1));

		return new ActionResult("freemarker",
				"/view/pos/inventoryonhand/inventoryonhand-list.ftl");
	}

	@Action(name = "/add", method = HttpMethod.GET)
	public ActionResult addForm() {
		return new ActionResult("freemarker",
				"/view/pos/inventoryonhand/inventoryonhand-form.ftl");
	}

	@Action(name = "/edit/{inventoryOnhand.id}", method = HttpMethod.GET)
	public ActionResult editForm() {
		String id = model.getInventoryOnhand().getId();
		if (id == null)
			return redirectToIndex;

		model.setInventoryOnhand(inventoryOnhandService.findById(id));
		if (model.getInventoryOnhand() == null)
			return redirectToIndex;

		return new ActionResult("freemarker",
				"/view/pos/inventoryonhand/inventoryonhand-form.ftl");
	}

	@Action(name = "/edit/{inventoryOnhand.id}", method = HttpMethod.POST)
	public ActionResult updateInventoryOnhand() {
		return addInventoryOnhand();
	}

	@Action(name = "/add", method = HttpMethod.POST)
	public ActionResult addInventoryOnhand() {
		inventoryOnhandService.save(model.getInventoryOnhand());

		return redirectToIndex;
	}
	
	@Override
	public InventoryOnhandActionModel getModel() {
		return model;
	}

}
