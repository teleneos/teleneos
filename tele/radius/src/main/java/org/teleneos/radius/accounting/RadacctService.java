package org.teleneos.radius.accounting;

import java.util.Date;
import java.util.List;

import org.meruvian.yama.persistence.EntityListWrapper;
import org.teleneos.radius.history.ConnectionHistory;

/**
 * @author Dian Aditya
 * 
 */
public interface RadacctService {
	Radacct findById(Long id);

	EntityListWrapper<Radacct> findByUsername(String username, int limit,
			int page);

	/**
	 * 
	 * @param username
	 * @param limit
	 * @param page
	 * @return [{@link Radacct}, {@link ConnectionHistory}]
	 */
	EntityListWrapper<Object[]> findOnlineUser(String username, int limit,
			int page);

	public boolean checkIsOnline(String username);

	Radacct findFirstSession(String username);

	Object[] findStatistic(String username);

	Radacct findByUniq(String uniq);

	EntityListWrapper<Object[]> findDetailByUsername(String username,
			int limit, int page);

	/**
	 * 
	 * @return [username, total download (in byte), total upload (in byte),
	 *         online time (in seconds)]
	 */
	EntityListWrapper<Object[]> findStatisticByUsername(String username,
			int limit, int page);

	List<Radacct> findByRange(long from, long to);

	EntityListWrapper<Object[]> daily(String date, int limit, int page);

	EntityListWrapper<Object[]> monthly(String date, int limit, int page);

	EntityListWrapper<Object[]> weekly(String date, int limit, int page);

	public Date getFirstConnection();

}