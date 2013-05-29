package org.teleneos.radius.accounting;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.meruvian.yama.persistence.DefaultPersistence;
import org.meruvian.yama.persistence.EntityListWrapper;
import org.meruvian.yama.persistence.PersistenceRepository;
import org.meruvian.yama.persistence.utils.PagingUtils;
import org.springframework.stereotype.Repository;

/**
 * @author Dian Aditya
 * 
 */
@Repository
public class RadacctRepository extends
		PersistenceRepository<DefaultPersistence> {
	public EntityListWrapper<Object[]> findDetailByUsername(String username,
			int limit, int page) {
		EntityListWrapper<Object[]> list = new EntityListWrapper<Object[]>();
		list.setLimit(limit);
		list.setCurrentPage(page);

		String ql = "SELECT r, c FROM Radacct r, ConnectionHistory c WHERE r.acctstoptime IS NOT NULL AND r.username = ?1 AND c.radacct = r.acctuniqueid ORDER BY r.radacctid DESC";
		Query query = entityManager.createQuery(ql);
		query.setParameter(1, username);

		if (limit > 0) {
			query.setMaxResults(limit);
		}
		query.setFirstResult(page * limit);

		list.setEntityList(query.getResultList());

		ql = "SELECT COUNT(r) FROM Radacct r, ConnectionHistory c WHERE r.acctstoptime IS NOT NULL AND r.username = ?1 AND c.radacct = r.acctuniqueid";
		TypedQuery<Long> lquery = entityManager.createQuery(ql, Long.class);
		lquery.setParameter(1, username);

		list.setRowCount(lquery.getSingleResult());
		list.setTotalPage(PagingUtils.getTotalPage(list.getRowCount(), limit));

		return list;
	}

	public EntityListWrapper<Object[]> findStatisticByUsername(String username,
			int limit, int page) {
		EntityListWrapper<Object[]> list = new EntityListWrapper<Object[]>();
		list.setLimit(limit);
		list.setCurrentPage(page);

		String ql = "SELECT a.username, SUM(a.acctinputoctets), SUM(a.acctoutputoctets), "
				+ "SUM(a.acctsessiontime) FROM Radacct a WHERE a.username LIKE ?1 GROUP BY a.username";

		Query query = entityManager.createQuery(ql);
		query.setParameter(1, username + "%");

		if (limit > 0) {
			query.setMaxResults(limit);
		}

		query.setFirstResult(page * limit);

		list.setEntityList(query.getResultList());

		ql = "SELECT COUNT(DISTINCT a.username) FROM Radacct a WHERE a.username LIKE ?1";

		TypedQuery<Long> lquery = entityManager.createQuery(ql, Long.class);
		lquery.setParameter(1, username + "%");

		list.setRowCount(lquery.getSingleResult());
		list.setTotalPage(PagingUtils.getTotalPage(list.getRowCount(), limit));

		return list;
	}

	public EntityListWrapper<Radacct> findByUsername(String username,
			int limit, int page) {
		EntityListWrapper<Radacct> list = new EntityListWrapper<Radacct>();
		list.setLimit(limit);
		list.setCurrentPage(page);

		String ql = "SELECT r FROM Radacct r WHERE r.acctstoptime IS NOT NULL AND r.username = ?1 ORDER BY r.acctstoptime DESC";
		TypedQuery<Radacct> query = entityManager
				.createQuery(ql, Radacct.class);
		query.setParameter(1, username);

		if (limit > 0) {
			query.setMaxResults(limit);
		}
		query.setFirstResult(page * limit);

		list.setEntityList(query.getResultList());

		ql = "SELECT COUNT(r) FROM Radacct r WHERE r.acctstoptime IS NOT NULL AND r.username = ?1 ORDER BY r.acctstoptime DESC";
		TypedQuery<Long> lquery = entityManager.createQuery(ql, Long.class);
		lquery.setParameter(1, username);

		list.setRowCount(lquery.getSingleResult());
		list.setTotalPage(PagingUtils.getTotalPage(list.getRowCount(), limit));

		return list;
	}

	public Radacct findFirstSession(String username) {
		String ql = "SELECT r FROM Radacct r WHERE r.username = ?1 AND r.acctstoptime IS NULL ORDER BY r.acctstarttime ASC LIMIT 0, 1";
		TypedQuery<Radacct> query = entityManager
				.createQuery(ql, Radacct.class);
		query.setParameter(1, username);
		return query.getResultList().get(0);
	}

	public boolean checkIsOnline(String username) {
		String ql = "SELECT r FROM Radacct r WHERE r.username LIKE ?1 AND r.acctstoptime IS NULL ORDER BY r.acctstoptime DESC";
		TypedQuery<Radacct> query = entityManager
				.createQuery(ql, Radacct.class);
		query.setParameter(1, username + "%");
		try {
			return query.getResultList().size() > 1 ? true : false;
		} catch (NoResultException e) {
			return false;
		}
	}

	public EntityListWrapper<Object[]> findOnlineUser(String username,
			int limit, int page) {
		EntityListWrapper<Object[]> list = new EntityListWrapper<Object[]>();
		list.setLimit(limit);
		list.setCurrentPage(page);

		String ql = "SELECT r, c FROM Radacct r, ConnectionHistory c WHERE r.acctuniqueid = c.radacct AND r.username LIKE ?1 AND r.acctstoptime IS NULL ORDER BY r.radacctid ASC";
		Query query = entityManager.createQuery(ql);
		query.setParameter(1, username + "%");

		if (limit > 0) {
			query.setMaxResults(limit);
		}
		query.setFirstResult(page * limit);

		list.setEntityList(query.getResultList());

		ql = "SELECT COUNT(r) FROM Radacct r WHERE r.username LIKE ?1 AND r.acctstoptime IS NULL ORDER BY r.acctstoptime DESC";
		TypedQuery<Long> lquery = entityManager.createQuery(ql, Long.class);
		lquery.setParameter(1, username + "%");

		list.setRowCount(lquery.getSingleResult());
		list.setTotalPage(PagingUtils.getTotalPage(list.getRowCount(), limit));

		return list;
	}

	public Radacct findById(Long id) {
		return entityManager.find(Radacct.class, id);
	}

	public Radacct findByUniq(String uniq) {
		return (Radacct) entityManager
				.createQuery("SELECT r FROM Radacct r WHERE r.acctuniqueid=?1")
				.setParameter(1, uniq).getSingleResult();
	}

	/**
	 * @param username
	 * @return total download, upload, and total online time
	 */
	public Object[] findStatistic(String username) {
		String q = "SELECT SUM(a.acctinputoctets), SUM(a.acctoutputoctets), SUM(a.acctsessiontime) FROM Radacct a WHERE a.username = ?1";
		Query query = entityManager.createQuery(q);
		query.setParameter(1, username);

		return (Object[]) query.getSingleResult();
	}

	public EntityListWrapper<Object[]> daily(String date, int limit, int page) {
		SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
		String ql = "SELECT a.username, SUM(a.acctinputoctets), SUM(a.acctoutputoctets), SUM(a.acctsessiontime) FROM Radacct a WHERE a.acctstarttime BETWEEN ? AND ? GROUP BY a.username";
		Calendar c = Calendar.getInstance();
		EntityListWrapper<Object[]> list = new EntityListWrapper<Object[]>();
		list.setLimit(limit);
		list.setCurrentPage(page);
		try {
			c.setTime(format.parse(date));
			c.add(Calendar.HOUR_OF_DAY, 23);
			c.add(Calendar.MINUTE, 59);
			c.add(Calendar.SECOND, 59);
			Query query = entityManager.createQuery(ql);
			query.setParameter(1, format.parse(date)).setParameter(2,
					c.getTime());

			if (limit > 0) {
				query.setMaxResults(limit);
			}

			query.setFirstResult(page * limit);

			list.setEntityList(query.getResultList());

			ql = "SELECT COUNT(DISTINCT a.username) FROM Radacct a WHERE a.acctstarttime BETWEEN ? AND ?";

			TypedQuery<Long> lquery = entityManager.createQuery(ql, Long.class);
			lquery.setParameter(1, format.parse(date)).setParameter(2,
					c.getTime());

			list.setRowCount(lquery.getSingleResult());
			list.setTotalPage(PagingUtils.getTotalPage(list.getRowCount(),
					limit));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return list;
	}

	public EntityListWrapper<Object[]> monthly(String date, int limit, int page) {
		SimpleDateFormat format = new SimpleDateFormat("MM/yyyy");
		String ql = "SELECT a.username, SUM(a.acctinputoctets), SUM(a.acctoutputoctets), SUM(a.acctsessiontime) FROM Radacct a WHERE a.acctstarttime BETWEEN ? AND ? GROUP BY a.username";
		Calendar c = Calendar.getInstance();
		EntityListWrapper<Object[]> list = new EntityListWrapper<Object[]>();
		list.setLimit(limit);
		list.setCurrentPage(page);
		try {
			c.setTime(format.parse(date));
			c.add(Calendar.MONTH, 1);
			Query query = entityManager.createQuery(ql);
			query.setParameter(1, format.parse(date)).setParameter(2,
					c.getTime());

			if (limit > 0) {
				query.setMaxResults(limit);
			}

			query.setFirstResult(page * limit);

			list.setEntityList(query.getResultList());

			ql = "SELECT COUNT(DISTINCT a.username) FROM Radacct a WHERE a.acctstarttime BETWEEN ? AND ?";

			TypedQuery<Long> lquery = entityManager.createQuery(ql, Long.class);
			lquery.setParameter(1, format.parse(date)).setParameter(2,
					c.getTime());

			list.setRowCount(lquery.getSingleResult());
			list.setTotalPage(PagingUtils.getTotalPage(list.getRowCount(),
					limit));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return list;
	}

	public EntityListWrapper<Object[]> weekly(String date, int limit, int page) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String ql = "SELECT a.username, SUM(a.acctinputoctets), SUM(a.acctoutputoctets), SUM(a.acctsessiontime) FROM Radacct a WHERE a.acctstarttime BETWEEN ? AND ? GROUP BY a.username";
		Calendar c = Calendar.getInstance();
		EntityListWrapper<Object[]> list = new EntityListWrapper<Object[]>();
		list.setLimit(limit);
		list.setCurrentPage(page);
		try {
			c.setTime(format.parse(date));
			c.add(Calendar.DATE, 6);
			Query query = entityManager.createQuery(ql);
			query.setParameter(1, format.parse(date)).setParameter(2,
					c.getTime());

			if (limit > 0) {
				query.setMaxResults(limit);
			}

			query.setFirstResult(page * limit);

			list.setEntityList(query.getResultList());

			ql = "SELECT COUNT(DISTINCT a.username) FROM Radacct a WHERE a.acctstarttime BETWEEN ? AND ?";

			TypedQuery<Long> lquery = entityManager.createQuery(ql, Long.class);
			lquery.setParameter(1, format.parse(date)).setParameter(2,
					c.getTime());

			list.setRowCount(lquery.getSingleResult());
			list.setTotalPage(PagingUtils.getTotalPage(list.getRowCount(),
					limit));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return list;
	}

	public Date getFirstConnection() {
		Query query = entityManager
				.createQuery(
						"SELECT a.acctstarttime FROM Radacct a ORDER BY a.acctstarttime ASC")
				.setMaxResults(1);
		try {
			return (Date) query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}
}
