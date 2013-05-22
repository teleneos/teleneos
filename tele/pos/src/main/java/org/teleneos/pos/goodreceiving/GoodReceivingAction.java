package org.teleneos.pos.goodreceiving;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.inject.Inject;

import org.apache.commons.lang.StringUtils;
import org.meruvian.inca.struts2.rest.ActionResult;
import org.meruvian.inca.struts2.rest.annotation.Action;
import org.meruvian.inca.struts2.rest.annotation.Action.HttpMethod;
import org.meruvian.inca.struts2.rest.annotation.Result;
import org.meruvian.inca.struts2.rest.annotation.Results;
import org.meruvian.yama.actions.DefaultAction;
import org.teleneos.pos.item.ItemService;
import org.teleneos.pos.uom.InvaidUnitOfMeasurementException;
import org.teleneos.pos.uom.UnitOfMeasureService;

import com.opensymphony.xwork2.ModelDriven;

/**
 * @author Edy Setiawan
 * 
 */
@Action(name = "/pos/goodreceiving")
@Results({ @Result(name = DefaultAction.INPUT, type = "freemarker", location = "/view/pos/goodreceiving/goodreceiving-form.ftl") })
public class GoodReceivingAction extends DefaultAction implements
		ModelDriven<GoodReceivingActionModel> {

	private static final long serialVersionUID = 1363531721180014337L;

	private GoodReceivingActionModel model = new GoodReceivingActionModel();
	private ActionResult redirectToIndex = new ActionResult("redirect",
			"/pos/goodreceiving");

	@Inject
	private GoodReceivingService goodReceivingService;

	@Inject
	private GoodReceivingDetailService goodReceivingDetailService;

	@Inject
	private ItemService itemService;

	@Inject
	private UnitOfMeasureService uomService;

	@Action
	public ActionResult goodReceivingList() {
		model.setGoodReceivings(goodReceivingService.findByKeyword(
				model.getQ(), null, "ASC", model.getMax(), model.getPage() - 1));

		return new ActionResult("freemarker",
				"/view/pos/goodreceiving/goodreceiving-list.ftl");
	}

	@Action(name = "/add", method = HttpMethod.GET)
	public ActionResult addForm() {
		return new ActionResult("freemarker",
				"/view/pos/goodreceiving/goodreceiving-form-first.ftl");
	}

	@Action(name = "/edit/{goodReceiving.id}", method = HttpMethod.GET)
	public ActionResult editForm() {

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
		if (StringUtils.isEmpty(model.getGoodReceiving().getInvoiceNo())) {
			addFieldError("goodReceiving.invoiceNo",
					"Invoice Number cannot be empty");
		}
		if (StringUtils.isEmpty(model.getDate())) {
			addFieldError("date", "Date cannot be empty");
		}
		if (StringUtils.isEmpty(model.getGoodReceiving().getBusinessPartner()
				.getId())) {
			addFieldError("goodReceiving.businessPartner.id",
					"Business Partner cannot be empty");
		}
		if (hasFieldErrors()) {
			return new ActionResult("freemarker",
					"/view/pos/goodreceiving/goodreceiving-form-first.ftl");
		}
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		try {
			model.getGoodReceiving().setDate(dateFormat.parse(model.getDate()));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		goodReceivingService.save(model.getGoodReceiving());
		return new ActionResult("/pos/goodreceiving/detail/"
				+ model.getGoodReceiving().getId()).setType("redirect");
	}

	@Action(name = "/detail/{goodReceiving.id}", method = HttpMethod.GET)
	public ActionResult formDetail() {
		model.setGoodReceiving(goodReceivingService.findById(model
				.getGoodReceiving().getId()));
		model.setUoms(uomService.findByKeyword("", null, null, 0, 0));
		model.setGoodReceivingDetails(goodReceivingDetailService.findByParent(
				model.getGoodReceiving().getId(), 0, 0));
		return new ActionResult("freemarker",
				"/view/pos/goodreceiving/goodreceiving-form.ftl");
	}

	@Action(name = "/detail/{goodReceiving.id}", method = HttpMethod.POST)
	public ActionResult formAddDetail() {
		if (StringUtils.isEmpty(model.getGoodReceivingDetail().getItem()
				.getId())) {
			addFieldError("goodReceivingDetail.item.id", "Item cannot be empty");
		}
		if (StringUtils.isEmpty(model.getGoodReceivingDetail().getItem()
				.getId())) {
			addFieldError("goodReceivingDetail.quantity",
					"Quantity cannot be empty");
		}
		if (StringUtils.isEmpty(model.getGoodReceivingDetail().getItem()
				.getId())) {
			addFieldError("goodReceivingDetail.uom.id",
					"Unit of Measurement cannot be empty");
		}
		if (hasFieldErrors()) {
			init();
			return new ActionResult("freemarker",
					"/view/pos/goodreceiving/goodreceiving-form.ftl");
		}
		model.getGoodReceivingDetail().setGoodReceiving(
				model.getGoodReceiving());
		try {
			goodReceivingDetailService.save(model.getGoodReceivingDetail());
		} catch (InvaidUnitOfMeasurementException e) {
			model.setErroruom(true);
			init();
			return new ActionResult("freemarker",
					"/view/pos/goodreceiving/goodreceiving-form.ftl");
		}

		return new ActionResult("/pos/goodreceiving/detail/"
				+ model.getGoodReceiving().getId()).setType("redirect");
	}

	private void init() {
		model.setGoodReceiving(goodReceivingService.findById(model
				.getGoodReceiving().getId()));
		model.setUoms(uomService.findByKeyword("", null, null, 0, 0));
		model.setGoodReceivingDetails(goodReceivingDetailService.findByParent(
				model.getGoodReceiving().getId(), 0, 0));
	}

	@Action(name = "/inventory", method = HttpMethod.POST)
	public ActionResult inventory() {
		goodReceivingDetailService
				.toInventory(model.getGoodReceiving().getId());
		return new ActionResult("/pos/goodreceiving/detail/"
				+ model.getGoodReceiving().getId()).setType("redirect");
	}

	@Override
	public GoodReceivingActionModel getModel() {
		return model;
	}
}
