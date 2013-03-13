package net.bogor.itu.service.admin;

import javax.inject.Inject;

import net.bogor.itu.entity.admin.Group;
import net.bogor.itu.repository.admin.GroupRepository;

import org.apache.commons.lang.StringUtils;
import org.meruvian.yama.persistence.EntityListWrapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Dian Aditya
 * 
 */
@Service
@Transactional(readOnly = true)
public class GroupServiceImpl implements GroupService {

	@Inject
	private GroupRepository groupRepository;

	@Override
	public Group findById(String id) {
		return groupRepository.findById(id);
	}

	@Override
	@Transactional
	public Group save(Group group) {
		if (StringUtils.isBlank(group.getId())) {
			group.setId(null);

			groupRepository.persist(group);
		} else {
			Group g = groupRepository.load(group.getId());
			g.setName(group.getName());
			g.setDescription(group.getDescription());

			group = g;
		}

		return group;
	}

	@Override
	public EntityListWrapper<Group> findByKeyword(String keyword, int limit,
			int page) {
		return groupRepository.findByKeyword(keyword, limit, page);
	}
}