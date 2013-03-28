package net.bogor.itu.service.radius;

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

	EntityListWrapper<Radacct> findOnlineUser(String username, int limit,
			int page);

	Radacct findFirstSession(String username);

	Object[] findStatistic(String username);
	
	Radacct findByUniq(String uniq);
	
	EntityListWrapper<Object[]> findDetailByUsername(String username,
			int limit, int page);
}