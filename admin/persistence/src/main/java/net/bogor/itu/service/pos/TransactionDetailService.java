package net.bogor.itu.service.pos;

import java.util.Date;

import net.bogor.itu.entity.pos.TransactionDetail;
import net.bogor.itu.service.pos.TransactionDetailImplService.StockNotFoundException;

import org.meruvian.yama.persistence.EntityListWrapper;

/**
 * @author Edy Setiawan
 * 
 */
public interface TransactionDetailService {

	TransactionDetail findById(String id);
	
	TransactionDetail save(TransactionDetail transactionDetail) throws StockNotFoundException, InvaidUnitOfMeasurementException;
	
	EntityListWrapper<TransactionDetail> findByKeyword(String keyword, int limit,
			int page);
	
	EntityListWrapper<Object[]> report(String from, String to);
	
	EntityListWrapper<Object[]> internet(String periodfrom, String periodto);
	
	EntityListWrapper<Object[]> daily(String date);
	
	void remove(TransactionDetail detail);

	EntityListWrapper<Object[]> monthly(String date);
	
	Date getFirstTransaction();

	EntityListWrapper<Object[]> weekly(String date);
}
