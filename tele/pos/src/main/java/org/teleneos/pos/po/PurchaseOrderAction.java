package org.teleneos.pos.po;

import javax.inject.Inject;

import org.meruvian.inca.struts2.rest.ActionResult;
import org.meruvian.inca.struts2.rest.annotation.Action;
import org.meruvian.inca.struts2.rest.annotation.Action.HttpMethod;
import org.meruvian.inca.struts2.rest.annotation.Result;
import org.meruvian.inca.struts2.rest.annotation.Results;
import org.meruvian.yama.actions.DefaultAction;
import org.teleneos.pos.item.ItemService;
import org.teleneos.pos.requisition.RequisitionDetailService;

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
	private RequisitionDetailService requisitionDetailService;

	@Inject
	private PurchaseOrderDetailService purchaseOrderDetailService;

	@Inject
	private ItemService itemService;

	@Action
	public ActionResult purchaseOrderList() {
		model.setPurchaseOrders(purchaseOrderService.findByKeyword(
				model.getQ(), null, "ASC", model.getMax(), model.getPage() - 1));

		return new ActionResult("freemarker", "/view/pos/po/po-list.ftl");
	}

	@Action(name = "/add", method = HttpMethod.GET)
	public ActionResult addForm() {
		return new ActionResult("freemarker", "/view/pos/po/po-form.ftl");
	}

	@Action(name = "/edit/{purchaseOrder.id}", method = HttpMethod.GET)
	public ActionResult editForm() {
		String id = model.getPurchaseOrder().getId();
		if (id == null)
			return redirectToIndex;

		model.setPurchaseOrder(purchaseOrderService.findById(id));
		if (model.getPurchaseOrder() == null)
			return redirectToIndex;

		return new ActionResult("freemarker", "/view/pos/po/po-form.ftl");
	}

	@Action(name = "/edit/{purchaseOrder.id}", method = HttpMethod.POST)
	public ActionResult updateRequisition() {
		return addRequisition();
	}

	@Action(name = "/add", method = HttpMethod.POST)
	public ActionResult addRequisition() {
		purchaseOrderService.save(model.getPurchaseOrder());

		PurchaseOrder purchaseOrder = model.getPurchaseOrder();

		return new ActionResult("/pos/po/detail/" + purchaseOrder.getId())
				.setType("redirect");
	}

	@Action(name = "/detail/{purchaseOrder.id}", method = HttpMethod.GET)
	public ActionResult formDetail() {

		model.setPurchaseOrderDetails(purchaseOrderDetailService.findByKeyword(
				model.getPurchaseOrder().getId(), 0, 0));

		model.setPurchaseOrder(purchaseOrderService.findById(model
				.getPurchaseOrder().getId()));

		model.setRequisitionDetails(requisitionDetailService.findByKeyword(
				model.getPurchaseOrder().getRequisition().getId(), 0, 0));

		return new ActionResult("freemarker", "/view/pos/po/po-detail-form.ftl");
	}

	@Action(name = "/detail/{purchaseOrder.id}", method = HttpMethod.POST)
	public ActionResult formAddDetail() {

		requisitionDetailService.save(model.getRequisitionDetail());

		return new ActionResult("/pos/po/detail/"
				+ model.getPurchaseOrder().getId()).setType("redirect");
	}

	@Action(name = "/purchaseorder", method = HttpMethod.POST)
	public ActionResult addPurchaseOrder() {

		String item = model.getItem();
		int i = (item.split(",").length);
		String is[] = new String[i];
		is = item.split(",");

		String quantity = model.getQuantity();
		int q = (quantity.split(",").length);
		String qs[] = new String[q];
		qs = quantity.split(",");

		String price = model.getPrice();
		int p = (price.split(",").length);
		String ps[] = new String[p];
		ps = price.split(",");

		for (int j = 0; j < i; j++) {
			item = is[j].replace(" ", "");
			quantity = qs[j].replace(" ", "");
			price = ps[j].replace(" ", "");

			PurchaseOrderDetail poD = new PurchaseOrderDetail();

			poD.setPurchaseOrder(purchaseOrderService.findById(model
					.getPurchaseOrder().getId()));
			poD.setItem(itemService.findById(item));
			poD.setQuantity(Integer.parseInt(quantity));
			poD.setPrice(Long.parseLong(price));

			purchaseOrderDetailService.save(poD);

		}

		PurchaseOrder po = new PurchaseOrder();
		po.setId(model.getPurchaseOrder().getId());
		po.setStatus(1);
		purchaseOrderService.save(po);

		return new ActionResult("/pos/po/detail/"
				+ model.getPurchaseOrder().getId()).setType("redirect");
	}

	@Override
	public PurchaseOrderActionModel getModel() {
		return model;
	}

}
