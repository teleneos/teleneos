package net.bogor.itu.service.master;

import java.util.List;

import net.bogor.itu.entity.master.GroupA;
import net.bogor.itu.entity.master.GroupPackage;

import org.meruvian.yama.persistence.EntityListWrapper;

public interface GroupService {
	GroupA findById(String id);

	GroupA save(GroupA group);

	EntityListWrapper<GroupA> findByKeyword(String keyword, int limit, int page);
	
	List<GroupPackage> getGroupPackages(String groubid);
}
