package org.teleneos.pos.transaction;

import java.util.Date;

import org.meruvian.yama.persistence.EntityListWrapper;
import org.teleneos.pos.transaction.TransactionDetailImplService.StockNotFoundException;
import org.teleneos.pos.uom.InvaidUnitOfMeasurementException;

/**
 * @author Edy Setiawan
 * 
 */
public interface TransactionDetailService {

	TransactionDetail findById(String id);

	TransactionDetail save(TransactionDetail transactionDetail)
			throws StockNotFoundException, InvaidUnitOfMeasurementException;

	EntityListWrapper<TransactionDetail> findByKeyword(String keyword,
			int limit, int page);

	EntityListWrapper<Object[]> report(String from, String to);

	EntityListWrapper<Object[]> internet(String periodfrom, String periodto);

	EntityListWrapper<Object[]> daily(String date);

	void remove(TransactionDetail detail);

	EntityListWrapper<Object[]> monthly(String date);

	Date getFirstTransaction();

	EntityListWrapper<Object[]> weekly(String date);

	EntityListWrapper<TransactionDetail> generatePostpaidReport(String q,
			int max, int page);
	
	EntityListWrapper<TransactionDetail> findPostpaidUser(String keyword, String username, int limit, int page);
}
