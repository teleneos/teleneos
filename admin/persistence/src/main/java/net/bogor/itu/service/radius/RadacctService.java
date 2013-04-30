package net.bogor.itu.service.radius;

import net.bogor.itu.entity.radius.ConnectionHistory;
import net.bogor.itu.entity.radius.Radacct;

import org.meruvian.yama.persistence.EntityListWrapper;

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
}