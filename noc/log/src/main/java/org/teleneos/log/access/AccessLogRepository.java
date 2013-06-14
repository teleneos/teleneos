/**
 * 
 */
package org.teleneos.log.access;

import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrQuery.ORDER;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.meruvian.yama.persistence.EntityListWrapper;
import org.meruvian.yama.persistence.utils.PagingUtils;
import org.springframework.stereotype.Repository;

/**
 * @author Dian Aditya
 * 
 */
@Repository
public class AccessLogRepository {
	@Inject
	private SolrServer solrServer;

	public EntityListWrapper<AccessLog> findAll(String telecentre, String user,
			String q, long from, long to, String order, int limit, int page)
			throws Exception {
		StringBuilder qBuilder = new StringBuilder();

		qBuilder.append("tele:").append(telecentre);

		qBuilder.append(" AND ");
		qBuilder.append("user:").append(StringUtils.defaultIfBlank(user, "*"));

		if (StringUtils.isNotBlank(q)) {
			qBuilder.append(" AND ");
			qBuilder.append("(text:").append("*").append(q).append("*");
			qBuilder.append(" OR ");
			qBuilder.append("url:").append("*").append(q).append("*)");
		}

		qBuilder.append(" AND ");
		qBuilder.append("time:").append("[").append(from).append(" TO ")
				.append(to <= 0 ? "*" : to).append("]");

		SolrQuery query = new SolrQuery(qBuilder.toString());
		query.setSort("time",
				StringUtils.equalsIgnoreCase(order, "ASC") ? ORDER.asc
						: ORDER.desc);
		query.setRows(limit);
		query.setStart(page * limit);

		QueryResponse rsp = solrServer.query(query);

		EntityListWrapper<AccessLog> results = new EntityListWrapper<AccessLog>();
		results.setEntityList(rsp.getBeans(AccessLog.class));
		results.setCurrentPage(page);
		results.setLimit(limit);
		results.setRowCount(rsp.getResults().getNumFound());
		results.setTotalPage(PagingUtils.getTotalPage(results.getRowCount(),
				limit));

		return results;
	}
}
