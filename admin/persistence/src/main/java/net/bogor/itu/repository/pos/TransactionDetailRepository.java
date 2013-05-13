package net.bogor.itu.repository.pos;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.NoResultException;
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
public class TransactionDetailRepository extends
		PersistenceRepository<TransactionDetail> {
	public EntityListWrapper<TransactionDetail> findByKeyword(String keyword,
			int limit, int page) {

		String criteria = "(td.transactionHeader.id = ?)";
		Object[] params = { keyword };

		return findAll(limit, page, "td", criteria, params);

	}

	public EntityListWrapper<Object[]> report(String from, String to)
			throws ParseException {
		EntityListWrapper<Object[]> list = new EntityListWrapper<Object[]>();
		SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
		String ql = "SELECT t, COUNT(t.item.id) FROM TransactionDetail t WHERE t.item.id IS NOT NULL AND (t.logInformation.createDate BETWEEN ? AND ?) GROUP BY t.item.id";
		Query query = entityManager.createQuery(ql)
				.setParameter(1, format.parse(from))
				.setParameter(2, format.parse(to));
		list.setEntityList(query.getResultList());
		return list;
	}

	public EntityListWrapper<Object[]> internet(String periodfrom,
			String periodto) throws ParseException {
		EntityListWrapper<Object[]> list = new EntityListWrapper<Object[]>();
		SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
		String ql = "SELECT t, COUNT(t.item.id) FROM TransactionDetail t WHERE t.internetPackage.id IS NOT NULL AND (t.logInformation.createDate BETWEEN ? AND ?) GROUP BY t.internetPackage.id";
		Query query = entityManager.createQuery(ql)
				.setParameter(1, format.parse(periodfrom))
				.setParameter(2, format.parse(periodto));
		list.setEntityList(query.getResultList());
		return list;
	}
	
	public EntityListWrapper<Object[]> daily(String date){
		EntityListWrapper<Object[]> list = new EntityListWrapper<Object[]>();
		SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
		String ql = "SELECT t, COUNT(t.item.id), COUNT(t.internetPackage.id) FROM TransactionDetail t WHERE t.logInformation.createDate BETWEEN ? AND ? GROUP BY t.item.id, t.internetPackage.id";
		Calendar c = Calendar.getInstance(); 
		try {
			c.setTime(format.parse(date));
			c.add(Calendar.HOUR_OF_DAY, 23);
			c.add(Calendar.MINUTE, 59);
			c.add(Calendar.SECOND, 59);
			Query query = entityManager.createQuery(ql)
					.setParameter(1, format.parse(date))
					.setParameter(2, c.getTime());
			System.err.println("date 1: "+c.getTime().toString()+"|"+format.parse(date).toString());
			list.setEntityList(query.getResultList());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public List<TransactionDetail> findByHeader(String headerId, boolean item) {
		String criteria = " WHERE d.transactionHeader.id = ?1 AND "
				+ (item ? "d.item" : "d.internetPackage") + " IS NOT NULL";

		return createQuery(entityClass, "d", "d", criteria, headerId)
				.getResultList();
	}

	public EntityListWrapper<Object[]> monthly(String date) {
		EntityListWrapper<Object[]> list = new EntityListWrapper<Object[]>();
		SimpleDateFormat format = new SimpleDateFormat("MM/yyyy");
		String ql = "SELECT t, COUNT(t.item.id), COUNT(t.internetPackage.id) FROM TransactionDetail t WHERE t.logInformation.createDate BETWEEN ? AND ? GROUP BY t.item.id, t.internetPackage.id";
		Calendar c = Calendar.getInstance(); 
		try {
			c.setTime(format.parse(date));
			c.add(Calendar.MONTH, 1);			
			Query query = entityManager.createQuery(ql)
					.setParameter(1, format.parse(date))
					.setParameter(2, c.getTime());
			list.setEntityList(query.getResultList());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public EntityListWrapper<Object[]> weekly(String date) {
		EntityListWrapper<Object[]> list = new EntityListWrapper<Object[]>();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String ql = "SELECT t, COUNT(t.item.id), COUNT(t.internetPackage.id) FROM TransactionDetail t WHERE t.logInformation.createDate BETWEEN ? AND ? GROUP BY t.item.id, t.internetPackage.id";
		Calendar c = Calendar.getInstance(); 
		try {
			c.setTime(format.parse(date));
			c.add(Calendar.DATE, 6);
			Query query = entityManager.createQuery(ql)
					.setParameter(1, format.parse(date))
					.setParameter(2, c.getTime());
			list.setEntityList(query.getResultList());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public Date getFirstTransaction() {
		Query query = entityManager.createQuery("SELECT t.logInformation.createDate FROM TransactionDetail t ORDER BY t.logInformation.createDate ASC").setMaxResults(1);
		try {
			return (Date) query.getSingleResult();	
		} catch (NoResultException e) {
			return null;
		}
	}

}
