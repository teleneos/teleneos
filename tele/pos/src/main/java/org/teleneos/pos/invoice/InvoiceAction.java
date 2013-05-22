package org.teleneos.pos.invoice;

import javax.inject.Inject;

import org.meruvian.inca.struts2.rest.ActionResult;
import org.meruvian.inca.struts2.rest.annotation.Action;
import org.meruvian.inca.struts2.rest.annotation.Action.HttpMethod;
import org.meruvian.inca.struts2.rest.annotation.Result;
import org.meruvian.inca.struts2.rest.annotation.Results;
import org.meruvian.yama.actions.DefaultAction;
import org.teleneos.pos.item.ItemService;
import org.teleneos.pos.po.PurchaseOrderDetailService;

import com.opensymphony.xwork2.ModelDriven;

/**
 * @author Edy Setiawan
 * 
 */
@Action(name = "/pos/invoice")
@Results({ @Result(name = DefaultAction.INPUT, type = "freemarker", location = "/view/pos/invoice/invoice-form.ftl") })
public class InvoiceAction extends DefaultAction implements
		ModelDriven<InvoiceActionModel> {
	private InvoiceActionModel model = new InvoiceActionModel();
	private ActionResult redirectToIndex = new ActionResult("redirect",
			"/pos/invoice");

	@Inject
	private InvoiceService invoiceService;

	@Inject
	private InvoiceDetailService invoiceDetailService;

	@Inject
	private PurchaseOrderDetailService purchaseOrderDetailService;

	@Inject
	private ItemService itemService;

	@Action
	public ActionResult InvoiceList() {
		model.setInvoices(invoiceService.findByKeyword(model.getQ(), null,
				"ASC", model.getMax(), model.getPage() - 1));

		return new ActionResult("freemarker",
				"/view/pos/invoice/invoice-list.ftl");
	}

	@Action(name = "/add", method = HttpMethod.GET)
	public ActionResult addForm() {
		return new ActionResult("freemarker",
				"/view/pos/invoice/invoice-form.ftl");
	}

	@Action(name = "/edit/{invoice.id}", method = HttpMethod.GET)
	public ActionResult editForm() {
		String id = model.getInvoice().getId();
		if (id == null)
			return redirectToIndex;

		model.setInvoice(invoiceService.findById(id));
		if (model.getInvoice() == null)
			return redirectToIndex;

		return new ActionResult("freemarker",
				"/view/pos/invoice/invoice-form.ftl");
	}

	@Action(name = "/edit/{invoice.id}", method = HttpMethod.POST)
	public ActionResult updateRequisition() {
		return addRequisition();
	}

	@Action(name = "/add", method = HttpMethod.POST)
	public ActionResult addRequisition() {
		invoiceService.save(model.getInvoice());

		Invoice invoice = model.getInvoice();

		return new ActionResult("/pos/invoice/detail/" + invoice.getId())
				.setType("redirect");
	}

	@Action(name = "/detail/{invoice.id}", method = HttpMethod.GET)
	public ActionResult formDetail() {

		model.setInvoiceDetails(invoiceDetailService.findByKeyword(model
				.getInvoice().getId(), 0, 0));

		model.setInvoice(invoiceService.findById(model.getInvoice().getId()));

		model.setPurchaseOrderDetails(purchaseOrderDetailService.findByKeyword(
				model.getInvoice().getPurchaseOrder().getId(), 0, 0));

		return new ActionResult("freemarker",
				"/view/pos/invoice/invoice-detail-form.ftl");
	}

	@Action(name = "/detail/{invoice.id}", method = HttpMethod.POST)
	public ActionResult formAddDetail() {

		purchaseOrderDetailService.save(model.getPurchaseOrderDetail());

		return new ActionResult("/pos/invoice/detail/"
				+ model.getInvoice().getId()).setType("redirect");
	}

	@Action(name = "/invoice", method = HttpMethod.POST)
	public ActionResult addInvoice() {

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

			InvoiceDetail in = new InvoiceDetail();

			in.setInvoice(invoiceService.findById(model.getInvoice().getId()));
			in.setItem(itemService.findById(item));
			in.setQuantity(Integer.parseInt(quantity));
			in.setPrice(Long.parseLong(price));

			invoiceDetailService.save(in);

		}

		Invoice po = new Invoice();
		po.setId(model.getInvoice().getId());
		po.setStatus(1);
		invoiceService.save(po);

		return new ActionResult("/pos/invoice/detail/"
				+ model.getInvoice().getId()).setType("redirect");
	}

	@Override
	public InvoiceActionModel getModel() {
		return model;
	}

}
