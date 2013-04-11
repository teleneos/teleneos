package net.bogor.itu.repository.radius;

import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import net.bogor.itu.entity.radius.Radacct;
import net.bogor.itu.persistence.PersistenceRepository;

import org.meruvian.yama.persistence.DefaultPersistence;
import org.meruvian.yama.persistence.EntityListWrapper;
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

		String ql = "SELECT r, c, u FROM Radacct r, ConnectionHistory c, User u WHERE r.acctstoptime IS NOT NULL AND r.username = ?1 AND c.radacct = r.acctuniqueid AND c.user.id = u.id ORDER BY r.acctstoptime DESC";
		Query query = entityManager.createQuery(ql);
		query.setParameter(1, username);

		if (limit > 0) {
			query.setMaxResults(limit);
		}
		query.setFirstResult(page * limit);

		list.setEntityList(query.getResultList());

		ql = "SELECT COUNT(r) FROM Radacct r, ConnectionHistory c, User u WHERE r.acctstoptime IS NOT NULL AND r.username = ?1 AND c.radacct = r.acctuniqueid AND c.user.id = u.id";
		TypedQuery<Long> lquery = entityManager.createQuery(ql, Long.class);
		lquery.setParameter(1, username);

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
	
	public boolean checkIsOnline(String username){
		String ql = "SELECT r FROM Radacct r WHERE r.username LIKE ?1 AND r.acctstoptime IS NULL ORDER BY r.acctstoptime DESC";
		TypedQuery<Radacct> query = entityManager
				.createQuery(ql, Radacct.class);
		query.setParameter(1, username + "%");
		try{
			return query.getResultList().size() > 1 ? true : false;			
		}catch (NoResultException e) {
			System.err.println(e.getMessage());
			return false;
		}
	}
	
	public EntityListWrapper<Radacct> findOnlineUser(String username,
			int limit, int page) {
		EntityListWrapper<Radacct> list = new EntityListWrapper<Radacct>();
		list.setLimit(limit);
		list.setCurrentPage(page);

		String ql = "SELECT r FROM Radacct r WHERE r.username LIKE ?1 AND r.acctstoptime IS NULL ORDER BY r.acctstoptime DESC";
		TypedQuery<Radacct> query = entityManager
				.createQuery(ql, Radacct.class);
		query.setParameter(1, username + "%");

		if (limit > 0) {
			query.setMaxResults(limit);
		}
		query.setFirstResult(page);

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
}
