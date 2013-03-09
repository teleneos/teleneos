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
}