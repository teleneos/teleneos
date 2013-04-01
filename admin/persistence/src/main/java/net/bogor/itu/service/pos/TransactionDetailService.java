package net.bogor.itu.service.pos;

import net.bogor.itu.entity.pos.TransactionDetail;

import org.meruvian.yama.persistence.EntityListWrapper;

/**
 * @author Edy Setiawan
 * 
 */
public interface TransactionDetailService {

	TransactionDetail findById(String id);
	
	TransactionDetail save(TransactionDetail transactionDetail);
	
	EntityListWrapper<TransactionDetail> findByKeyword(String keyword, int limit,
			int page);
	
	EntityListWrapper<Object[]> report(String from, String to);

	EntityListWrapper<Object[]> internet(String periodfrom, String periodto);
	
	void remove(TransactionDetail detail);
}
