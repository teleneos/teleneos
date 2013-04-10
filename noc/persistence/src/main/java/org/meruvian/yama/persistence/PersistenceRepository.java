package org.meruvian.yama.persistence;

import javax.persistence.TypedQuery;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.lucene.search.Query;
import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.FullTextQuery;
import org.hibernate.search.jpa.Search;
import org.hibernate.search.query.dsl.QueryBuilder;
import org.meruvian.yama.persistence.LogInformation.StatusFlag;
import org.meruvian.yama.persistence.access.PersistenceDAO;
import org.meruvian.yama.persistence.utils.PagingUtils;

/**
 * @author Dian Aditya
 * 
 */
public class PersistenceRepository<T extends DefaultPersistence> extends
		PersistenceDAO<T> {
	public static final Log LOG = LogFactory
			.getLog(PersistenceRepository.class);

	public EntityListWrapper<T> findAll(int limit, int page) {
		LOG.debug("getting " + entityClass.getSimpleName());

		return findAll(limit, page, "d", "");
	}

	/**
	 * 
	 * @deprecated use {@link #findAll(int, int)} instead
	 */
	@Deprecated
	@Override
	public EntityListWrapper<T> getAll(int limit, int page) {
		TypedQuery<T> query = createQuery(entityClass, "d", "d",
				"d.logInformation.statusFlag = ?", StatusFlag.ACTIVE);

		if (limit > 0) {
			query.setMaxResults(limit);
		}

		query.setFirstResult(page);

		EntityListWrapper<T> paging = new EntityListWrapper<T>();
		paging.setCurrentPage(page);
		paging.setLimit(limit);
		paging.setEntityList(query.getResultList());

		return paging;
	}

	public Long count() {
		return count("d", "");
	}

	/**
	 * 
	 * @deprecated use {@link #count()} instead
	 */
	@Deprecated
	@Override
	public long getRowCount() {
		return getRowCount("d", "d.logInformation.statusFlag = ?",
				StatusFlag.ACTIVE);
	}

	/**
	 * 
	 * @deprecated use {@link PagingUtils#getTotalPage(long, long)} instead
	 */
	@Deprecated
	@Override
	public long getTotalPage(int limit) {
		return PagingUtils.getTotalPage(getRowCount(), limit);
	}

	public T load(String id) {
		LOG.debug("loading " + entityClass.getSimpleName()
				+ " instance with id: " + id);
		try {
			T instance = entityManager.getReference(entityClass, id);

			if (instance == null) {
				LOG.debug("load successful, no instance found");
			} else {
				LOG.debug("load successful, instance found");
			}
			return instance;
		} catch (RuntimeException re) {
			LOG.error("load failed", re);
			return null;
		}
	}

	protected EntityListWrapper<T> findAll(int limit, int page, String alias,
			String criteria, Object... parameters) {
		return findAll(limit, page, entityClass, alias, criteria, parameters);
	}

	protected <X> EntityListWrapper<X> findAll(int limit, int page,
			Class<X> resultClass, String alias, String criteria,
			Object... parameters) {
		TypedQuery<X> query = createQuery(resultClass, alias, alias, criteria,
				parameters);

		if (limit > 0) {
			query.setMaxResults(limit);
		}

		query.setFirstResult(page * limit);

		EntityListWrapper<X> paging = new EntityListWrapper<X>();
		paging.setCurrentPage(page);
		paging.setLimit(limit);
		paging.setEntityList(query.getResultList());
		paging.setRowCount(count(alias, criteria, parameters));
		paging.setTotalPage(PagingUtils.getTotalPage(paging.getRowCount(),
				limit));

		return paging;
	}

	protected Long count(String alias, String criteria, Object... parameters) {
		LOG.debug("getting " + entityClass.getSimpleName() + " count");
		try {
			long count = createQuery(Long.class, "count(" + alias + ".id)",
					alias, criteria, parameters).getSingleResult();

			LOG.debug("get successful, return " + count + " row");

			return count;
		} catch (RuntimeException re) {
			LOG.error("get failed", re);
			throw re;
		}
	}

	/**
	 * 
	 * @deprecated use {@link #count(String, String, Object...)} instead
	 */
	@Override
	@Deprecated
	protected long getRowCount(String alias, String criteria,
			Object... parameters) {
		LOG.debug("getting " + entityClass.getSimpleName() + " count");
		try {
			long count = createQuery(Long.class, "count(id)", alias, criteria,
					parameters).getSingleResult();

			LOG.debug("get successful, return " + count + " row");

			return count;
		} catch (RuntimeException re) {
			LOG.error("get failed", re);
			throw re;
		}
	}

	protected EntityListWrapper<T> findByKeyword(int limit, int page,
			String keyword, String... fields) {
		FullTextEntityManager em = Search
				.getFullTextEntityManager(entityManager);
		QueryBuilder qb = em.getSearchFactory().buildQueryBuilder()
				.forEntity(entityClass).get();
		Query query = qb.keyword().onFields(fields).matching(keyword)
				.createQuery();

		FullTextQuery q = em.createFullTextQuery(query, entityClass);

		if (limit > 0) {
			q.setMaxResults(limit);
		}

		q.setFirstResult(page);

		EntityListWrapper<T> paging = new EntityListWrapper<T>();
		paging.setCurrentPage(page);
		paging.setLimit(limit);
		paging.setEntityList(q.getResultList());
		paging.setRowCount(q.getResultSize());
		paging.setTotalPage(PagingUtils.getTotalPage(paging.getRowCount(),
				limit));

		return paging;
	}

	@Override
	protected <X> TypedQuery<X> createQuery(Class<X> resultClass,
			String select, String alias, String criteria, Object... parameters) {
		String ql = "SELECT " + select.trim() + " FROM "
				+ entityClass.getName() + " " + alias.trim()
				+ (StringUtils.isBlank(criteria) ? "" : (" WHERE " + criteria));

		TypedQuery<X> query = entityManager.createQuery(ql, resultClass);

		for (int i = 1; i <= parameters.length; i++) {
			query.setParameter(i, parameters[i - 1]);
		}

		return query;
	}
}
