package net.bogor.itu.service.radius;

import javax.inject.Inject;

import net.bogor.itu.entity.radius.Radacct;
import net.bogor.itu.repository.radius.RadacctRepository;

import org.meruvian.yama.persistence.EntityListWrapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Dian Aditya
 * 
 */
@Service
@Transactional(readOnly = true)
public class RadacctServiceImpl implements RadacctService {
	@Inject
	private RadacctRepository radacctRepo;

	@Override
	public Radacct findById(Long id) {
		return radacctRepo.findById(id);
	}

	@Override
	public EntityListWrapper<Radacct> findByUsername(String username,
			int limit, int page) {
		return radacctRepo.findByUsername(username, limit, page);
	}

	@Override
	public EntityListWrapper<Radacct> findOnlineUser(String username,
			int limit, int page) {
		return radacctRepo.findOnlineUser(username, limit, page);
	}

	@Override
	public Radacct findFirstSession(String username) {
		return radacctRepo.findFirstSession(username);
	}

	@Override
	public Object[] findStatistic(String username) {
		return radacctRepo.findStatistic(username);
	}

	@Override
	public Radacct findByUniq(String uniq) {
		return radacctRepo.findByUniq(uniq);
	}

	@Override
	public EntityListWrapper<Object[]> findDetailByUsername(String username,
			int limit, int page) {
		return radacctRepo.findDetailByUsername(username, limit, page);
	}
}
