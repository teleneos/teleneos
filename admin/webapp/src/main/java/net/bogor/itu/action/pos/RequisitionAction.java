package net.bogor.itu.action.pos;

import javax.inject.Inject;

import net.bogor.itu.entity.pos.Requisition;
import net.bogor.itu.entity.pos.RequisitionDetail;
import net.bogor.itu.service.pos.RequisitionDetailService;
import net.bogor.itu.service.pos.RequisitionService;

import org.meruvian.inca.struts2.rest.ActionResult;
import org.meruvian.inca.struts2.rest.annotation.Action;
import org.meruvian.inca.struts2.rest.annotation.Action.HttpMethod;
import org.meruvian.inca.struts2.rest.annotation.Result;
import org.meruvian.inca.struts2.rest.annotation.Results;
import org.meruvian.yama.actions.DefaultAction;

import com.opensymphony.xwork2.ModelDriven;

@Action(name = "/pos/requisition")
@Results({ @Result(name = DefaultAction.INPUT, type = "freemarker", location = "/view/pos/requisition/requisition-form.ftl") })
public class RequisitionAction extends DefaultAction implements
		ModelDriven<RequisitionActionModel> {
	private RequisitionActionModel model = new RequisitionActionModel();
	private ActionResult redirectToIndex = new ActionResult("redirect",
			"/pos/requisition");

	@Inject
	private RequisitionService requisitionService;

	@Inject
	private RequisitionDetailService requisitionDetailService;

	@Action
	public ActionResult requisitionList() {
		model.setRequisitions(requisitionService.findByKeyword(model.getQ(),
				null, "ASC", model.getMax(), model.getPage() - 1));

		return new ActionResult("freemarker",
				"/view/pos/requisition/requisition-list.ftl");
	}

	@Action(name = "/add", method = HttpMethod.GET)
	public ActionResult addForm() {
		return new ActionResult("freemarker",
				"/view/pos/requisition/requisition-form.ftl");
	}

	@Action(name = "/edit/{requisition.id}", method = HttpMethod.GET)
	public ActionResult editForm() {
		String id = model.getRequisition().getId();
		if (id == null)
			return redirectToIndex;

		model.setRequisition(requisitionService.findById(id));
		if (model.getRequisition() == null)
			return redirectToIndex;

		return new ActionResult("freemarker",
				"/view/pos/requisition/requisition-form.ftl");
	}

	@Action(name = "/edit/{requisition.id}", method = HttpMethod.POST)
	public ActionResult updateRequisition() {
		return addRequisition();
	}

	@Action(name = "/add", method = HttpMethod.POST)
	public ActionResult addRequisition() {
		requisitionService.save(model.getRequisition());

		Requisition requisition = model.getRequisition();

		return new ActionResult("/pos/requisition/detail/"
				+ requisition.getId()).setType("redirect");
	}

	@Action(name = "/detail/{requisition.id}", method = HttpMethod.GET)
	public ActionResult formDetail() {

		model.setRequisitionDetails(requisitionDetailService.findByKeyword(
				model.getRequisition().getId(), 0, model.getPage() - 1));

		model.setRequisition(requisitionService.findById(model.getRequisition()
				.getId()));

		return new ActionResult("freemarker",
				"/view/pos/requisition/requisition-detail-form.ftl");
	}

	@Action(name = "/detail/{requisition.id}", method = HttpMethod.POST)
	public ActionResult formAddDetail() {

		requisitionDetailService.save(model.getRequisitionDetail());

		return formDetail();
	}

	@Override
	public RequisitionActionModel getModel() {
		return model;
	}

}
