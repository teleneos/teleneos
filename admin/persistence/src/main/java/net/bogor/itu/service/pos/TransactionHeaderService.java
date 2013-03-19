package net.bogor.itu.service.pos;

import net.bogor.itu.entity.pos.TransactionHeader;

import org.meruvian.yama.persistence.EntityListWrapper;
/**
 * @author Edy Setiawan
 * 
 */
public interface TransactionHeaderService {
	
	TransactionHeader findById(String id);
	
	TransactionHeader save(TransactionHeader transactionHeader);
	
	EntityListWrapper<TransactionHeader> findByKeyword(String keyword,String order,String orderby, int limit,
			int page);

}
