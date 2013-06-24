package org.teleneos.pos.transaction;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.meruvian.yama.persistence.EntityListWrapper;
import org.meruvian.yama.persistence.PersistenceRepository;
import org.meruvian.yama.persistence.LogInformation.StatusFlag;
import org.meruvian.yama.persistence.utils.PagingUtils;
import org.springframework.stereotype.Repository;
import org.teleneos.radius.internetpackage.PaymentMethod;

/**
 * @author Edy Setiawan
 * 
 */
@Repository
public class TransactionDetailRepository extends
		PersistenceRepository<TransactionDetail> {
	public EntityListWrapper<TransactionDetail> findByKeyword(String keyword,
			int limit, int page) {

		String criteria = "(td.transactionHeader.id = ?) AND td.subscribe = ?";
		Object[] params = { keyword, false };

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

	public EntityListWrapper<Object[]> daily(String date) {
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
		Query query = entityManager
				.createQuery(
						"SELECT t.logInformation.createDate FROM TransactionDetail t ORDER BY t.logInformation.createDate ASC")
				.setMaxResults(1);
		try {
			return (Date) query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

	public EntityListWrapper<TransactionDetail> generatePostpaidReport(
			String q, int max, int page) {
		EntityListWrapper<TransactionDetail> list = new EntityListWrapper<TransactionDetail>();
		list.setLimit(max);
		list.setCurrentPage(page);
		StringBuilder sb = new StringBuilder("SELECT td FROM TransactionDetail td WHERE td.internetPackage.paymentMethod = ? AND td.subscribe = ? AND ");
		boolean isPaid = q.equalsIgnoreCase("paid");
		boolean isUnpaid = q.equalsIgnoreCase("unpaid");
		Query query = null;
		int crit = 0;
		if (isPaid || isUnpaid) {
			sb.append("td.transactionHeader.postpaidStatus = ? ");
			query = entityManager.createQuery(sb.toString())
					.setParameter(1, PaymentMethod.POSTPAID)
					.setParameter(2, false)
					.setParameter(3, isPaid);
			crit = 0;
		} else if (q.matches("[0-9]+")) {
			sb.append("td.transactionHeader.counter = ? ");
			query = entityManager.createQuery(sb.toString())
					.setParameter(1, PaymentMethod.POSTPAID)
					.setParameter(2, false)
					.setParameter(3, Long.parseLong(q));
			crit = 1;
		} else {
			sb.append("( td.internetPackage.name LIKE ? OR td.transactionHeader.username LIKE ? )");
			query = entityManager.createQuery(sb.toString())
					.setParameter(1, PaymentMethod.POSTPAID)
					.setParameter(2, false)
					.setParameter(3, "%"+q+"%")
					.setParameter(4, "%"+q+"%");
			crit = 2;
		}
		
		if (max > 0) {
			query.setMaxResults(max);
		}
		
		query.setFirstResult(page * max);
		list.setEntityList(query.getResultList());
		
		sb = sb.replace(0, 15, "SELECT COUNT(td) FROM ");
		TypedQuery<Long> lquery = entityManager.createQuery(sb.toString(), Long.class);
		switch (crit) {
		case 0:
			lquery.setParameter(1, PaymentMethod.POSTPAID).setParameter(2, false).setParameter(3, isPaid);
			break;
		case 1:
			lquery.setParameter(1, PaymentMethod.POSTPAID).setParameter(2, false).setParameter(3, Long.parseLong(q));
			break;
		case 2:
			lquery.setParameter(1, PaymentMethod.POSTPAID).setParameter(2, false).setParameter(3, "%"+q+"%").setParameter(4, "%"+q+"%");
		}
		list.setRowCount(lquery.getSingleResult());
		list.setTotalPage(PagingUtils.getTotalPage(list.getRowCount(), max));
		return list;
	}
	
	public EntityListWrapper<TransactionDetail> findPostpaidUser(String keyword, String username, int limit, int page) {
		return findAll(limit, page, "p", "p.internetPackage.paymentMethod = ?1 AND (p.internetPackage.code LIKE ?2 OR p.internetPackage.name LIKE ?3 ) AND p.transactionHeader.username = ?4 AND p.registration = ?5 AND p.paid = ?6 AND p.logInformation.statusFlag = ?7",
				PaymentMethod.POSTPAID, keyword+"%", keyword+"%", username, true, true, StatusFlag.ACTIVE);
	}
	
}
