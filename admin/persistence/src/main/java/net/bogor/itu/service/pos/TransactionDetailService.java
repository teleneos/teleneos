package net.bogor.itu.service.pos;

import net.bogor.itu.entity.pos.TransactionDetail;
import net.bogor.itu.entity.pos.TransactionHeader;

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
	
}
