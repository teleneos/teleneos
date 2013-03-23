package net.bogor.itu.service.master;

import javax.inject.Inject;

import net.bogor.itu.entity.master.InternetPackage;
import net.bogor.itu.repository.master.PackageManagerRepository;

import org.apache.commons.lang.StringUtils;
import org.meruvian.yama.persistence.EntityListWrapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class PackageManagerImplService implements PackageManagerService {

	@Inject
	private PackageManagerRepository packageManagerRepository;

	@Override
	public EntityListWrapper<InternetPackage> findByName(String keyword,
			String order, String orderBy, int limit, int page) {
		if (StringUtils.isBlank(keyword))
			return packageManagerRepository.findAll(limit, page);
		return packageManagerRepository.findByKeyword(keyword, order, orderBy,
				limit, page, "OR");
	}

	@Override
	@Transactional
	public InternetPackage save(InternetPackage internetPackage) {
		if (StringUtils.isBlank(internetPackage.getId())) {
			internetPackage.setId(null);

			packageManagerRepository.persist(internetPackage);
		} else {
			InternetPackage ic = packageManagerRepository.load(internetPackage
					.getId());
			ic.setName(internetPackage.getName());
			internetPackage = ic;
		}
		return internetPackage;
	}

	@Override
	public InternetPackage findById(String id) {
		return findById(id);
	}

	@Override
	public EntityListWrapper<InternetPackage> all() {
		return packageManagerRepository.findAll(0, 0);
	}

}
