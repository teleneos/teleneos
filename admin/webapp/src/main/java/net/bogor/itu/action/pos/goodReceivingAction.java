package net.bogor.itu.action.pos;

import javax.inject.Inject;

import net.bogor.itu.entity.pos.GoodReceiving;
import net.bogor.itu.entity.pos.GoodReceivingDetail;
import net.bogor.itu.service.pos.GoodReceivingDetailService;
import net.bogor.itu.service.pos.GoodReceivingService;
import net.bogor.itu.service.pos.InvoiceDetailService;
import net.bogor.itu.service.pos.ItemService;
import net.bogor.itu.service.pos.ItemTypeService;
import net.bogor.itu.service.pos.UnitOfMeasureService;

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
@Action(name = "/pos/goodreceiving")
@Results({ @Result(name = DefaultAction.INPUT, type = "freemarker", location = "/view/pos/goodreceiving/goodreceiving-form.ftl") })
public class goodReceivingAction extends DefaultAction implements
		ModelDriven<goodReceivingActionModel> {
	private goodReceivingActionModel model = new goodReceivingActionModel();
	private ActionResult redirectToIndex = new ActionResult("redirect",
			"/pos/goodreceiving");

	@Inject
	private GoodReceivingService goodReceivingService;

	@Inject
	private GoodReceivingDetailService goodReceivingDetailService;

	@Inject
	private InvoiceDetailService invoiceDetailService;

	@Inject
	private ItemService itemService;

	@Inject
	private ItemTypeService itemTypeService;
	
	@Action
	public ActionResult goodReceivingList() {
		model.setGoodReceivings(goodReceivingService.findByKeyword(
				model.getQ(), null, "ASC", model.getMax(), model.getPage() - 1));

		return new ActionResult("freemarker",
				"/view/pos/goodreceiving/goodreceiving-list.ftl");
	}

	@Action(name = "/add", method = HttpMethod.GET)
	public ActionResult addForm() {
		model.setItemTypes(itemTypeService.findByKeyword("", "ASC", "id", 0, 0));
		
		return new ActionResult("freemarker",
				"/view/pos/goodreceiving/goodreceiving-form.ftl");
	}

	@Action(name = "/edit/{goodReceiving.id}", method = HttpMethod.GET)
	public ActionResult editForm() {
		model.setItemTypes(itemTypeService.findByKeyword("", "ASC", "id", 0, 0));
		
		String id = model.getInvoice().getId();
		if (id == null)
			return redirectToIndex;
		model.setGoodReceiving(goodReceivingService.findById(id));
		if (model.getInvoice() == null)
			return redirectToIndex;

		return new ActionResult("freemarker",
				"/view/pos/goodreceiving/goodreceiving-form.ftl");
	}

	@Action(name = "/edit/{goodReceiving.id}", method = HttpMethod.POST)
	public ActionResult updateRequisition() {
		return addRequisition();
	}

	@Action(name = "/add", method = HttpMethod.POST)
	public ActionResult addRequisition() {
		goodReceivingService.save(model.getGoodReceiving());

		/*GoodReceiving goodReceiving = model.getGoodReceiving();
		return new ActionResult("/pos/goodreceiving/detail/"+ goodReceiving.getId()).setType("redirect");*/
		return redirectToIndex;
	}

	@Action(name = "/detail/{goodReceiving.id}", method = HttpMethod.GET)
	public ActionResult formDetail() {

		model.setGoodReceivingDetails(goodReceivingDetailService.findByKeyword(
				model.getGoodReceiving().getId(), 0, 0));

		model.setGoodReceiving(goodReceivingService.findById(model.getGoodReceiving()
				.getId()));

		model.setInvoiceDetails(invoiceDetailService.findByKeyword(model
				.getInvoice().getId(), 0, 0));

		return new ActionResult("freemarker",
				"/view/pos/goodreceiving/goodreceiving-detail-form.ftl");
	}

	@Action(name = "/detail/{goodReceiving.id}", method = HttpMethod.POST)
	public ActionResult formAddDetail() {

		goodReceivingDetailService.save(model.getGoodReceivingDetail());

		return new ActionResult("/pos/goodreceiving/detail/"
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

		for (int j = 0; j < i; j++) {
			item = is[j].replace(" ", "");
			quantity = qs[j].replace(" ", "");

			GoodReceivingDetail in = new GoodReceivingDetail();

			in.setGoodReceiving(goodReceivingService.findById(model
					.getGoodReceiving().getId()));
			in.setItem(itemService.findById(item));
			in.setQuantity(Integer.parseInt(quantity));

			goodReceivingDetailService.save(in);

		}

		GoodReceiving po = new GoodReceiving();
		po.setId(model.getInvoice().getId());
		po.setStatus(1);
		goodReceivingService.save(po);

		return new ActionResult("/pos/goodreceiving/detail/"
				+ model.getInvoice().getId()).setType("redirect");
	}

	@Override
	public goodReceivingActionModel getModel() {
		return model;
	}

}
