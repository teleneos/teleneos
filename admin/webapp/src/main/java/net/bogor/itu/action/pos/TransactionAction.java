package net.bogor.itu.action.pos;

import javax.inject.Inject;

import net.bogor.itu.entity.admin.User;
import net.bogor.itu.entity.pos.TransactionHeader;
import net.bogor.itu.service.admin.GroupService;
import net.bogor.itu.service.master.PackageManagerService;
import net.bogor.itu.service.pos.ItemService;
import net.bogor.itu.service.pos.TransactionDetailService;
import net.bogor.itu.service.pos.TransactionHeaderService;
import net.bogor.itu.service.radius.RadacctService;

import org.meruvian.inca.struts2.rest.ActionResult;
import org.meruvian.inca.struts2.rest.annotation.Action;
import org.meruvian.inca.struts2.rest.annotation.Action.HttpMethod;
import org.meruvian.inca.struts2.rest.annotation.Result;
import org.meruvian.inca.struts2.rest.annotation.Results;
import org.meruvian.yama.actions.DefaultAction;

import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.validator.annotations.RequiredStringValidator;
import com.opensymphony.xwork2.validator.annotations.Validations;

/**
 * @author Edy Setiawan
 * 
 */
@Action(name = "/pos/transaction")
@Results({ @Result(name = DefaultAction.INPUT, type = "freemarker", location = "/view/pos/transaction/transaction-detail-form.ftl") })
public class TransactionAction extends DefaultAction implements
		ModelDriven<TransactionActionModel> {

	private TransactionActionModel model = new TransactionActionModel();

	@Inject
	private TransactionHeaderService tHeaderService;

	@Inject
	private TransactionDetailService tDetailService;

	@Inject
	private ItemService itemService;

	@Inject
	private RadacctService radacctService;

	@Inject
	private GroupService groupService;

	@Inject
	private PackageManagerService packageManagerService;

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
	@Validations(requiredStrings = { @RequiredStringValidator(fieldName = "id", trim = true) })
	@Results({ @Result(name = DefaultAction.INPUT, type = "freemarker", location = "/view/pos/transaction/transaction-form.ftl") })
	public ActionResult addTransactionHeader() {
		User user = new User();
		user.setId(model.getId());

		TransactionHeader tHeader = model.getTransactionHeader();
		tHeader.setUser(user);

		tHeaderService.save(tHeader);

		return new ActionResult("/pos/transaction/detail/" + tHeader.getId())
				.setType("redirect");
	}

	@Action(name = "/detail/{transactionHeader.id}", method = HttpMethod.GET)
	public ActionResult formDetail() {
		model.setTransactionDetails(tDetailService.findByKeyword(model
				.getTransactionHeader().getId(), 0, model.getPage() - 1));

		model.setTransactionHeader(tHeaderService.findById(model
				.getTransactionHeader().getId()));

		model.setAccts(radacctService.findByUsername(model
				.getTransactionHeader().getUser().getUser().getUsername(),
				model.getMax(), model.getPage() - 1));

		return new ActionResult("freemarker",
				"/view/pos/transaction/transaction-detail-form.ftl");
	}

	@Action(name = "/detail/{transactionHeader.id}", method = HttpMethod.POST)
	public ActionResult addFormDetail() {
		TransactionHeader tHeader = model.getTransactionHeader();
		model.getTransactionDetail().setTransactionHeader(
				tHeaderService.findById(tHeader.getId()));

		if (model.getChange().equalsIgnoreCase("true")) {
			// model.setInternetPackage(null);
			model.setItem(itemService.findById(model.getTransactionDetail()
					.getItem().getId()));
			model.getTransactionDetail().setInternetPackage(null);
			model.getTransactionDetail().setPrice(model.getItem().getPrice());
		} else {
			// model.setItem(null);
			model.setInternetPackage(packageManagerService.findById(model
					.getTransactionDetail().getInternetPackage().getId()));
			model.getTransactionDetail().setItem(null);
			model.getTransactionDetail().setPrice(
					model.getInternetPackage().getPrice());
		}

		tDetailService.save(model.getTransactionDetail());

		return new ActionResult("/pos/transaction/detail/" + tHeader.getId())
				.setType("redirect");
	}
	
	@Action(name = "/remove/{transactionHeader.id}", method = HttpMethod.GET)
	public ActionResult removeTransaction() {
		tDetailService.remove(model.getTransactionDetail());
		return new ActionResult("/pos/transaction/detail/" + model.getTransactionHeader().getId())
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
