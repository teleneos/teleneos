package net.bogor.itu.repository.pos;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.persistence.Query;

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
	
	public EntityListWrapper<Object[]> report(String from, String to) throws ParseException{
		EntityListWrapper<Object[]> list = new EntityListWrapper<Object[]>();
		SimpleDateFormat format = new SimpleDateFormat(
				"MM/dd/yyyy");
		String ql = "SELECT t, COUNT(t.item.id) FROM TransactionDetail t WHERE t.item.id IS NOT NULL AND t.logInformation.createDate >= ? AND t.logInformation.createDate <= ? GROUP BY t.item.id";
		Query query = entityManager.createQuery(ql).setParameter(1, format.parse(from)).setParameter(2, format.parse(to));
		list.setEntityList(query.getResultList());
		return list;
	}

	public EntityListWrapper<Object[]> internet(String periodfrom,
			String periodto) throws ParseException {
		EntityListWrapper<Object[]> list = new EntityListWrapper<Object[]>();
		SimpleDateFormat format = new SimpleDateFormat(
				"MM/dd/yyyy");
		String ql = "SELECT t, COUNT(t.item.id) FROM TransactionDetail t WHERE t.internetPackage.id IS NOT NULL AND t.logInformation.createDate >= ? AND t.logInformation.createDate <= ? GROUP BY t.internetPackage.id";
		Query query = entityManager.createQuery(ql).setParameter(1, format.parse(periodfrom)).setParameter(2, format.parse(periodto));
		list.setEntityList(query.getResultList());
		return list;
	}
}
