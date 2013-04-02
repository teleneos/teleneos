package net.bogor.itu.service.master;

import java.util.List;

import javax.inject.Inject;

import net.bogor.itu.entity.master.GroupA;
import net.bogor.itu.entity.master.GroupPackage;
import net.bogor.itu.repository.master.GroupRepository;

import org.apache.commons.lang.StringUtils;
import org.meruvian.yama.persistence.EntityListWrapper;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class GroupImplService implements GroupService {

	@Inject
	private GroupRepository repository;

	@Override
	public GroupA findById(String id) {
		return repository.findById(id);
	}

	@Override
	@Transactional
	public GroupA save(GroupA group) throws DataIntegrityViolationException {
		if (StringUtils.isBlank(group.getId())) {
			group.setId(null);
			repository.persist(group);
		} else {
			GroupA g = repository.load(group.getId());
			g.setName(group.getName());
			g.setFreeOfCharge(group.isFreeOfCharge());
			g.setCode(group.getCode());
			g.setClub(group.isClub());
			g.setPaymentMethod(group.getPaymentMethod());
			g.setPaymentPeriod(group.getPaymentPeriod());
			g.setGroupPackages(group.getGroupPackages());

			group = g;
		}
		return group;
	}

	@Override
	public EntityListWrapper<GroupA> findByKeyword(String keyword, int limit,
			int page) {
		if (StringUtils.isBlank(keyword))
			return repository.findAll(limit, page);
		return repository.findByKeyword(keyword, limit, page);
	}

	@Override
	public List<GroupPackage> getGroupPackages(String groubid) {
		return repository.findByGroup(groubid);
	}

}
