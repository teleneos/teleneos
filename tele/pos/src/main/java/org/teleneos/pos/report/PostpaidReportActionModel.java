package org.teleneos.pos.report;

import org.meruvian.yama.actions.DefaultActionModel;
import org.meruvian.yama.persistence.EntityListWrapper;
import org.teleneos.pos.transaction.TransactionDetail;

public class PostpaidReportActionModel extends DefaultActionModel {

	private EntityListWrapper<TransactionDetail> transactionDetails = new EntityListWrapper<TransactionDetail>();

	public EntityListWrapper<TransactionDetail> getTransactionDetails() {
		return transactionDetails;
	}

	public void setTransactionDetails(
			EntityListWrapper<TransactionDetail> transactionDetails) {
		this.transactionDetails = transactionDetails;
	}

}
