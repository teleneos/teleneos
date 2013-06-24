package org.teleneos.pos.transaction;

import java.util.Date;

import javax.inject.Inject;

import org.meruvian.inca.struts2.rest.ActionResult;
import org.meruvian.inca.struts2.rest.annotation.Action;
import org.meruvian.inca.struts2.rest.annotation.Action.HttpMethod;
import org.meruvian.inca.struts2.rest.annotation.Result;
import org.meruvian.inca.struts2.rest.annotation.Results;
import org.meruvian.yama.actions.DefaultAction;
import org.meruvian.yama.persistence.LogInformation.StatusFlag;
import org.teleneos.pos.item.ItemService;
import org.teleneos.pos.transaction.TransactionDetailImplService.StockNotFoundException;
import org.teleneos.pos.uom.InvaidUnitOfMeasurementException;
import org.teleneos.radius.accounting.RadacctService;
import org.teleneos.radius.internetpackage.PackageManagerService;
import org.teleneos.radius.internetpackage.PaymentMethod;

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

	private static final long serialVersionUID = 1L;

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
		TransactionHeader tHeader = model.getTransactionHeader();
		tHeader.setUsername(model.getId());

		tHeaderService.save(tHeader);

		return new ActionResult("/pos/transaction/detail/" + tHeader.getId())
				.setType("redirect");
	}

	@Action(name = "/addstarter", method = HttpMethod.GET)
	public ActionResult addTransactionHeaderStarter() {
		TransactionHeader tHeader = model.getTransactionHeader();
		tHeader.setUsername(model.getId());

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

		return new ActionResult("freemarker",
				"/view/pos/transaction/transaction-detail-form.ftl");
	}

	@Action(name = "/detail/{transactionHeader.id}", method = HttpMethod.POST)
	public ActionResult addFormDetail() {
		TransactionHeader tHeader = tHeaderService.findById(model
				.getTransactionHeader().getId());
		model.setTransactionHeader(tHeader);
		model.getTransactionDetail().setTransactionHeader(tHeader);
		if (model.getChange().equalsIgnoreCase("true")) {
			if (model.getTransactionDetail().getItem().getId().isEmpty()) {
				addFieldError("transactionDetail.item.name",
						"Item cannot be empty");
			}
			if (model.getTransactionDetail().getQuantity() == 0) {
				addFieldError("transactionDetail.quantity",
						"Quantity must greater than 0");
			}
			if (model.getTransactionDetail().getUom() == null) {
				addFieldError("transactionDetail.uom.name",
						"Unit of measurement cannot be empty");
			}
			if (hasFieldErrors()) {
				model.setTransactionDetails(tDetailService.findByKeyword(model
						.getTransactionHeader().getId(), 0, model.getPage() - 1));
				model.setTransactionHeader(tHeaderService.findById(model
						.getTransactionHeader().getId()));
				return new ActionResult("freemarker",
						"/view/pos/transaction/transaction-detail-form.ftl");
			}
			model.setItem(itemService.findById(model.getTransactionDetail()
					.getItem().getId()));
			model.getTransactionDetail().setInternetPackage(null);
			model.getTransactionDetail().setUserPackage(null);
			model.getTransactionDetail().setPrice(model.getItem().getPrice());
		} else if(model.getChange().equalsIgnoreCase("false")) {
			model.setInternetPackage(packageManagerService.findById(model
					.getTransactionDetail().getInternetPackage().getId()));
			model.getTransactionDetail().setItem(null);
			model.getTransactionDetail().setUom(null);
			model.getTransactionDetail().setUserPackage(null);
			model.getTransactionDetail().setPrice(model.getInternetPackage().getPrice());
			if(model.getTransactionDetail().getInternetPackage().getPaymentMethod().equals(PaymentMethod.POSTPAID)){
				model.getTransactionDetail().setRegistration(true);
				model.getTransactionDetail().setPostpaidPeriod(1);
			}
		} else {
			/**
			long internetTime = System.currentTimeMillis()
					- model.getTransactionDetail().getLogInformation()
							.getCreateDate().getTime();
			model.getTransactionDetail()
					.setPrice(
							((TimeUnit.MILLISECONDS.toMinutes(internetTime) / model
									.getTransactionDetail().getInternetPackage()
									.getTime()) * model.getTransactionDetail()
									.getInternetPackage().getPrice()));
			if (TimeUnit.MILLISECONDS.toMinutes(internetTime)
					% model.getTransactionDetail().getInternetPackage().getPrice() != 0) {
				model.getTransactionDetail().setPrice(
						model.getTransactionDetail().getPrice()
								+ model.getTransactionDetail().getInternetPackage()
										.getPrice());
			}
			*/
			
			model.setTransactionDetail(tDetailService.findById(model.getTransactionDetail().getId()));
			model.getTransactionDetail().getLogInformation().setStatusFlag(StatusFlag.INACTIVE);
			TransactionDetail detail = new TransactionDetail();
			detail.setInternetPackage(model.getTransactionDetail().getInternetPackage());
			detail.setPrice(model.getTransactionDetail().getPrice());
			detail.setTransactionHeader(model.getTransactionHeader());
			detail.setRegistration(false);
			detail.setPostpaidPeriod(model.getTransactionDetail().getPostpaidPeriod());
			detail.setPostpaidStart(model.getTransactionDetail().getPostpaidStart());
			detail.setPostpaidEnd(model.getTransactionDetail().getPostpaidEnd());
			
			
			try {
				tDetailService.save(detail);
			} catch (StockNotFoundException e) {
				e.printStackTrace();
			} catch (InvaidUnitOfMeasurementException e) {
				e.printStackTrace();
			}
			
		}

		try {
			tDetailService.save(model.getTransactionDetail());
		} catch (StockNotFoundException e) {
			addFieldError("transactionDetail.quantity",
					"Stock for this item is not available");
			if (hasFieldErrors()) {
				model.setTransactionDetails(tDetailService.findByKeyword(model
						.getTransactionHeader().getId(), 0, model.getPage() - 1));
				model.setTransactionHeader(tHeaderService.findById(model
						.getTransactionHeader().getId()));
				return new ActionResult("freemarker",
						"/view/pos/transaction/transaction-detail-form.ftl");
			}
		} catch (InvaidUnitOfMeasurementException e) {
			model.setErroruom(true);
			return new ActionResult("freemarker",
					"/view/pos/transaction/transaction-detail-form.ftl");
		}

		return new ActionResult("/pos/transaction/detail/" + tHeader.getId())
				.setType("redirect");
	}

	@Action(name = "/remove/{transactionHeader.id}", method = HttpMethod.GET)
	public ActionResult removeTransaction() {
		tDetailService.remove(model.getTransactionDetail());
		return new ActionResult("/pos/transaction/detail/"
				+ model.getTransactionHeader().getId()).setType("redirect");
	}

	@Action(name = "/cash", method = HttpMethod.POST)
	public ActionResult addCashTransaction() {

		if (model.getTransactionHeader().getCash() == null) {
			addFieldError("transactionHeader.cash", "Cash cannot be empty");
		}

		if (model.getTransactionHeader().getCash() != null) {
			if (model.getTransactionHeader().getCash() < model.getGrandtotal()) {
				addFieldError("transactionHeader.cash", "Insufficient cash");
			}
		}
		model.setTransactionDetails(tDetailService.findByKeyword(model
				.getTransactionHeader().getId(), 0, model.getPage() - 1));
		if (hasFieldErrors()) {
			
			model.setTransactionHeader(tHeaderService.findById(model
					.getTransactionHeader().getId()));
			return new ActionResult("freemarker",
					"/view/pos/transaction/transaction-detail-form.ftl");
		}

		TransactionHeader tHeader = model.getTransactionHeader();
		model.getTransactionHeader().setId(tHeader.getId());
		
		for (TransactionDetail td : model.getTransactionDetails().getEntityList()) {
			if (td.getInternetPackage() != null) {
				if (td.getInternetPackage().getPaymentMethod()
						.equals(PaymentMethod.POSTPAID)) {
					TransactionDetail detail = null;
					if (td.isRegistration()) {
						td.setPostpaidStart(new Date());
						td.setPostpaidEnd(new Date(td.getPostpaidStart().getTime()+(td.getInternetPackage().getTime()*60000)));
					} else {
						detail = new TransactionDetail();
						detail.setInternetPackage(td.getInternetPackage());
						detail.setTransactionHeader(td.getTransactionHeader());
						detail.setPrice(td.getPrice());
						detail.setRegistration(true);
						detail.setPostpaidPeriod(td.getPostpaidPeriod());
						detail.setPostpaidStart(new Date(td.getPostpaidStart().getTime()+(td.getInternetPackage().getTime()*60000)));
						detail.setPostpaidEnd(new Date(detail.getPostpaidStart().getTime()+(td.getInternetPackage().getTime()*60000)));
						detail.setPostpaidPeriod(td.getPostpaidPeriod()+1);
						detail.setSubscribe(true);
						detail.setPaid(true);
					}
					td.setPaid(true);
					try {
						tDetailService.save(td);
						if (detail != null)
							tDetailService.save(detail);
					} catch (StockNotFoundException e) {
						e.printStackTrace();
					} catch (InvaidUnitOfMeasurementException e) {
						e.printStackTrace();
					}
				}
			}
		}
		
		tHeaderService.save(model.getTransactionHeader());
		return new ActionResult("/pos/transaction/detail/" + tHeader.getId())
				.setType("redirect");
	}

	@Action(name = "/userpackage/{username}", method = HttpMethod.GET)
	public ActionResult userPackage() {
		model.setListacc(radacctService.findDetailByUsername(
				model.getUsername(), model.getMax(), model.getPage() - 1));
		return new ActionResult("/pos/transaction").setType("redirect");
	}
	
	@Action(name = "/postpaid")
	public ActionResult postpaid() {
		model.setTransactionDetails(tDetailService.findPostpaidUser(model.getQ(), model.getTransactionHeader().getUsername(), model.getMax(),
				model.getPage() - 1));
		return new ActionResult("freemarker", "/blank.ftl");
	}
	
	@Override
	public TransactionActionModel getModel() {
		return model;
	}

}
