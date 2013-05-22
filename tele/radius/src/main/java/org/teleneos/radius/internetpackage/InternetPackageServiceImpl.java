package org.teleneos.radius.internetpackage;

import javax.inject.Inject;

import org.meruvian.yama.persistence.EntityListWrapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class InternetPackageServiceImpl implements InternetPackageService {
	
	@Inject
	private InternetPackageRepository packageRepository;

	@Override
	public EntityListWrapper<InternetPackage> getAll(String name, int limit, int page) {
		return packageRepository.find(name, limit, page);
	}

}
