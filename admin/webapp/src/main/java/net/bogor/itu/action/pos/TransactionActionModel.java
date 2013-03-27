package net.bogor.itu.action.pos;

import net.bogor.itu.entity.master.InternetPackage;
import net.bogor.itu.entity.pos.Item;
import net.bogor.itu.entity.pos.ItemCategory;
import net.bogor.itu.entity.pos.TransactionDetail;
import net.bogor.itu.entity.pos.TransactionHeader;
import net.bogor.itu.entity.radius.Radacct;

import org.meruvian.yama.actions.DefaultActionModel;
import org.meruvian.yama.persistence.EntityListWrapper;

/**
 * @author Edy Setiawan
 * 
 */
public class TransactionActionModel extends DefaultActionModel {

	private TransactionHeader transactionHeader = new TransactionHeader();
	private EntityListWrapper<TransactionHeader> transactionHeaders = new EntityListWrapper<TransactionHeader>();
	private TransactionDetail transactionDetail = new TransactionDetail();
	private EntityListWrapper<TransactionDetail> transactionDetails = new EntityListWrapper<TransactionDetail>();
	private String id;
	private Item item = new Item();
	private EntityListWrapper<Radacct> accts = new EntityListWrapper<Radacct>();
	private InternetPackage internetPackage = new InternetPackage();
	private String change;

	public TransactionHeader getTransactionHeader() {
		return transactionHeader;
	}

	public void setTransactionHeader(TransactionHeader transactionHeader) {
		this.transactionHeader = transactionHeader;
	}

	public EntityListWrapper<TransactionHeader> getTransactionHeaders() {
		return transactionHeaders;
	}

	public void setTransactionHeaders(
			EntityListWrapper<TransactionHeader> transactionHeaders) {
		this.transactionHeaders = transactionHeaders;
	}

	public TransactionDetail getTransactionDetail() {
		return transactionDetail;
	}

	public void setTransactionDetail(TransactionDetail transactionDetail) {
		this.transactionDetail = transactionDetail;
	}

	public EntityListWrapper<TransactionDetail> getTransactionDetails() {
		return transactionDetails;
	}

	public void setTransactionDetails(
			EntityListWrapper<TransactionDetail> transactionDetails) {
		this.transactionDetails = transactionDetails;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public EntityListWrapper<Radacct> getAccts() {
		return accts;
	}

	public void setAccts(EntityListWrapper<Radacct> accts) {
		this.accts = accts;
	}

	public InternetPackage getInternetPackage() {
		return internetPackage;
	}

	public void setInternetPackage(InternetPackage internetPackage) {
		this.internetPackage = internetPackage;
	}

	public String getChange() {
		return change;
	}

	public void setChange(String change) {
		this.change = change;
	}


}
