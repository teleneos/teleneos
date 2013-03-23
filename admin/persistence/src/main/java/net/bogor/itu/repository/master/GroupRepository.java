package net.bogor.itu.repository.master;

import java.util.List;

import net.bogor.itu.entity.master.GroupA;
import net.bogor.itu.entity.master.GroupPackage;
import net.bogor.itu.persistence.PersistenceRepository;

import org.meruvian.yama.persistence.EntityListWrapper;
import org.springframework.stereotype.Repository;

@Repository(value="groupARepository")
public class GroupRepository extends PersistenceRepository<GroupA> {
	
	public EntityListWrapper<GroupA> findByKeyword(String keyword, int limit,
			int page) {
		return findAll(limit, page, "g", "g.name LIKE ?1", keyword + "%");
	}
	
	public List<GroupPackage> findByGroup(String id) {
		return entityManager.createQuery("FROM GroupPackage g WHERE g.group.id=?").setParameter(1, id).getResultList();
	}
}
