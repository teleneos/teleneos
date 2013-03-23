package net.bogor.itu.action.pos;

import javax.inject.Inject;

import net.bogor.itu.entity.pos.PurchaseOrder;
import net.bogor.itu.service.pos.PurchaseOrderDetailService;
import net.bogor.itu.service.pos.PurchaseOrderService;

import org.meruvian.inca.struts2.rest.ActionResult;
import org.meruvian.inca.struts2.rest.annotation.Action;
import org.meruvian.inca.struts2.rest.annotation.Action.HttpMethod;
import org.meruvian.inca.struts2.rest.annotation.Result;
import org.meruvian.inca.struts2.rest.annotation.Results;
import org.meruvian.yama.actions.DefaultAction;

import com.opensymphony.xwork2.ModelDriven;
/**
 * @author Edy Setiawan
 * 
 */
@Action(name = "/pos/po")
@Results({ @Result(name = DefaultAction.INPUT, type = "freemarker", location = "/view/pos/po/po-form.ftl") })
public class PurchaseOrderAction extends DefaultAction implements
ModelDriven<PurchaseOrderActionModel> {
private PurchaseOrderActionModel model = new PurchaseOrderActionModel();
private ActionResult redirectToIndex = new ActionResult("redirect",
	"/pos/po");

@Inject
private PurchaseOrderService purchaseOrderService;

@Inject
private PurchaseOrderDetailService purchaseOrderDetailService;

@Action
public ActionResult purchaseOrderList() {
	model.setPurchaseOrders(purchaseOrderService.findByKeyword(model.getQ(),
			null, "ASC", model.getMax(), model.getPage() - 1));

	return new ActionResult("freemarker",
			"/view/pos/po/po-list.ftl");
}

@Action(name = "/add", method = HttpMethod.GET)
public ActionResult addForm() {
	return new ActionResult("freemarker",
			"/view/pos/po/po-form.ftl");
}

@Action(name = "/edit/{purchaseOrder.id}", method = HttpMethod.GET)
public ActionResult editForm() {
	String id = model.getPurchaseOrder().getId();
	if (id == null)
		return redirectToIndex;

	model.setPurchaseOrder(purchaseOrderService.findById(id));
	if (model.getPurchaseOrder() == null)
		return redirectToIndex;

	return new ActionResult("freemarker",
			"/view/pos/po/po-form.ftl");
}

@Action(name = "/edit/{purchaseOrder.id}", method = HttpMethod.POST)
public ActionResult updateRequisition() {
	return addRequisition();
}

@Action(name = "/add", method = HttpMethod.POST)
public ActionResult addRequisition() {
	purchaseOrderService.save(model.getPurchaseOrder());

	PurchaseOrder purchaseOrder= model.getPurchaseOrder();

	return new ActionResult("/pos/po/detail/"
			+ purchaseOrder.getId()).setType("redirect");
}

@Action(name = "/detail/{purchaseOrder.id}", method = HttpMethod.GET)
public ActionResult formDetail() {

	model.setPurchaseOrderDetails(purchaseOrderDetailService.findByKeyword(
			model.getPurchaseOrder().getId(), 0, model.getPage() - 1));

	model.setPurchaseOrder(purchaseOrderService.findById(model.getPurchaseOrder()
			.getId()));

	return new ActionResult("freemarker",
			"/view/pos/po/po-detail-form.ftl");
}

@Action(name = "/detail/{purchaseOrder.id}", method = HttpMethod.POST)
public ActionResult formAddDetail() {

	purchaseOrderDetailService.save(model.getPurchaseOrderDetail());

	return formDetail();
}

@Override
public PurchaseOrderActionModel getModel() {
	return model;
}

}
