package org.teleneos.pos.transaction;

import java.util.Date;

import org.meruvian.yama.actions.DefaultActionModel;
import org.meruvian.yama.persistence.EntityListWrapper;
import org.teleneos.pos.conversion.Conversion;
import org.teleneos.pos.item.Item;
import org.teleneos.radius.accounting.Radacct;
import org.teleneos.radius.internetpackage.InternetPackage;
import org.teleneos.radius.userpackage.UserPackage;

/**
 * @author Edy Setiawan
 * 
 */
public class TransactionActionModel extends DefaultActionModel {

	private TransactionHeader transactionHeader = new TransactionHeader();
	private EntityListWrapper<TransactionHeader> transactionHeaders = new EntityListWrapper<TransactionHeader>();
	private TransactionDetail transactionDetail = new TransactionDetail();
	private EntityListWrapper<TransactionDetail> transactionDetails = new EntityListWrapper<TransactionDetail>();
	private EntityListWrapper<Conversion> conversions = new EntityListWrapper<Conversion>();
	private UserPackage userPackage = new UserPackage();
	private String id;
	private Item item = new Item();
	private EntityListWrapper<Radacct> accts = new EntityListWrapper<Radacct>();
	private InternetPackage internetPackage = new InternetPackage();
	private String change;
	private EntityListWrapper<Object[]> listacc = new EntityListWrapper<Object[]>();
	private String username;
	private boolean erroruom = false;
	private long grandtotal;
	private Date currentDate = new Date();
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public EntityListWrapper<Object[]> getListacc() {
		return listacc;
	}

	public void setListacc(EntityListWrapper<Object[]> listacc) {
		this.listacc = listacc;
	}

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

	public boolean isErroruom() {
		return erroruom;
	}

	public void setErroruom(boolean erroruom) {
		this.erroruom = erroruom;
	}

	public long getGrandtotal() {
		return grandtotal;
	}

	public void setGrandtotal(long grandtotal) {
		this.grandtotal = grandtotal;
	}

	public UserPackage getUserPackage() {
		return userPackage;
	}

	public void setUserPackage(UserPackage userPackage) {
		this.userPackage = userPackage;
	}

	public EntityListWrapper<Conversion> getConversions() {
		return conversions;
	}

	public void setConversions(EntityListWrapper<Conversion> conversions) {
		this.conversions = conversions;
	}

	public Date getCurrentDate() {
		return currentDate;
	}

	public void setCurrentDate(Date currentDate) {
		this.currentDate = currentDate;
	}

}
