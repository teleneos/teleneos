package net.bogor.itu.repository.admin;

import net.bogor.itu.entity.admin.Group;
import net.bogor.itu.persistence.PersistenceRepository;

import org.meruvian.yama.persistence.EntityListWrapper;
import org.springframework.stereotype.Repository;

/**
 * @author Dian Aditya
 * 
 */
@Repository
public class GroupRepository extends PersistenceRepository<Group> {
	public EntityListWrapper<Group> findByKeyword(String keyword, int limit,
			int page) {
		return findAll(limit, page, "g", "g.name LIKE ?1", keyword + "%");
	}
}
