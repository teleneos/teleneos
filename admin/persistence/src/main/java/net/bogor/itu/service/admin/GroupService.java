package net.bogor.itu.service.admin;

import net.bogor.itu.entity.admin.Group;

import org.meruvian.yama.persistence.EntityListWrapper;

/**
 * @author Dian Aditya
 * 
 */
public interface GroupService {
	Group findById(String id);

	Group save(Group service);

	EntityListWrapper<Group> findByKeyword(String keyword, int limit, int page);
}
