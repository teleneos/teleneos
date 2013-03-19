package net.bogor.itu.repository.pos;

import net.bogor.itu.entity.pos.ItemCategory;
import net.bogor.itu.entity.pos.TransactionDetail;
import net.bogor.itu.persistence.PersistenceRepository;

import org.meruvian.yama.persistence.EntityListWrapper;
import org.springframework.stereotype.Repository;

/**
 * @author Edy Setiawan
 * 
 */
@Repository
public class TransactionDetailRepository extends PersistenceRepository<TransactionDetail>{
	public EntityListWrapper<TransactionDetail> findByKeyword(String keyword,
			int limit, int page) {
		
		String criteria = "(td.transactionHeader.id = ?)";
		Object[] params = {keyword};
		
		return findAll(limit, page, "td", criteria, params);
				
	}
}
