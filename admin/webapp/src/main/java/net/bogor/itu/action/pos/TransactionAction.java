package net.bogor.itu.action.pos;

import javax.inject.Inject;
import javax.swing.JOptionPane;

import net.bogor.itu.entity.pos.Item;
import net.bogor.itu.entity.pos.TransactionHeader;
import net.bogor.itu.service.admin.UserService;
import net.bogor.itu.service.pos.ItemService;
import net.bogor.itu.service.pos.TransactionDetailService;
import net.bogor.itu.service.pos.TransactionHeaderService;

import org.apache.commons.lang.StringUtils;
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
@Action(name = "/pos/transaction")
@Results({ @Result(name = DefaultAction.INPUT, type = "freemarker", location = "/view/pos/transaction/transaction-detail-form.ftl") })
public class TransactionAction extends DefaultAction implements
		ModelDriven<TransactionActionModel> {

	private TransactionActionModel model = new TransactionActionModel();
	private ActionResult redirectToIndex = new ActionResult("redirect",
			"/pos/transaction");

	@Inject
	private TransactionHeaderService tHeaderService;

	@Inject
	private TransactionDetailService tDetailService;

	@Inject
	private UserService userService;

	@Inject
	private ItemService itemService;

	@Action
	public ActionResult transactionList() {
		model.setTransactionHeaders(tHeaderService.findByKeyword(model.getQ(),
				null, "ASC", model.getMax(), model.getPage() - 1));

		return new ActionResult("freemarker",
				"/view/pos/transaction/transaction-list.ftl");
	}

	@Action(name = "/add", method = HttpMethod.GET)
	public ActionResult addForm() {
		return new ActionResult("freemarker",
				"/view/pos/transaction/transaction-form.ftl");
	}

	@Action(name = "/add", method = HttpMethod.POST)
	public ActionResult addTransactionHeader() {

		model.getTransactionHeader().setUser(
				userService.findById(model.getId()));

		tHeaderService.save(model.getTransactionHeader());

		TransactionHeader tHeader = model.getTransactionHeader();

		return new ActionResult("/pos/transaction/detail/" + tHeader.getId())
				.setType("redirect");
	}

	@Action(name = "/detail/{transactionHeader.id}", method = HttpMethod.GET)
	public ActionResult formDetail() {

		model.setTransactionDetails(tDetailService.findByKeyword(model
				.getTransactionHeader().getId(), 0, model.getPage() - 1));

		model.setTransactionHeader(tHeaderService.findById(model
				.getTransactionHeader().getId()));

		return new ActionResult("freemarker",
				"/view/pos/transaction/transaction-detail-form.ftl");
	}
	
	@Action(name = "/detail/{transactionHeader.id}.htm", method = HttpMethod.GET)
	public ActionResult printDetail() {

		model.setTransactionDetails(tDetailService.findByKeyword(model
				.getTransactionHeader().getId(), 0, model.getPage() - 1));

		model.setTransactionHeader(tHeaderService.findById(model
				.getTransactionHeader().getId()));

		return new ActionResult("freemarker",
				"/view/pos/transaction/transaction-detail-form.ftl");
	}

	@Action(name = "/detail/{transactionHeader.id}", method = HttpMethod.POST)
	public ActionResult addFormDetail() {
		TransactionHeader tHeader = model.getTransactionHeader();
		model.getTransactionDetail().setTransactionHeader(
				tHeaderService.findById(tHeader.getId()));
		
		model.setItem(itemService.findById(model.getTransactionDetail().getItem().getId()));
		model.getTransactionDetail().setPrice(model.getItem().getPrice());
		
		tDetailService.save(model.getTransactionDetail());

		return new ActionResult("/pos/transaction/detail/" + tHeader.getId())
				.setType("redirect");
	}

	@Action(name = "/cash", method = HttpMethod.POST)
	public ActionResult addCashTransaction() {
		TransactionHeader tHeader = model.getTransactionHeader();
		model.getTransactionHeader().setId(tHeader.getId());
		tHeaderService.save(model.getTransactionHeader());

		return new ActionResult("/pos/transaction/detail/" + tHeader.getId())
				.setType("redirect");
	}

	@Override
	public TransactionActionModel getModel() {
		return model;
	}

}
