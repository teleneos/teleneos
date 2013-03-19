package net.bogor.itu.action.pos;

import net.bogor.itu.entity.pos.ItemCategory;
import net.bogor.itu.entity.pos.TransactionDetail;
import net.bogor.itu.entity.pos.TransactionHeader;

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
	

}
