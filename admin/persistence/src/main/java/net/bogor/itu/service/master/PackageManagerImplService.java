package net.bogor.itu.service.master;

import javax.inject.Inject;

import net.bogor.itu.entity.master.InternetPackage;
import net.bogor.itu.entity.master.PaymentMethod;
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

		return packageManagerRepository.findByKeyword(keyword, limit, page);
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
			ic.setPrice(internetPackage.getPrice());
			ic.setStatus(internetPackage.getStatus());

			internetPackage = ic;
		}
		return internetPackage;
	}

	@Override
	public InternetPackage findById(String id) {
		return packageManagerRepository.findById(id);
	}

	@Override
	public EntityListWrapper<InternetPackage> all() {
		return packageManagerRepository.findAll(0, 0);
	}

	@Override
	public EntityListWrapper<InternetPackage> findByPaymentMethod(
			boolean freeCharge, PaymentMethod method, String groupId,
			int limit, int page) {
		return packageManagerRepository.findByPaymentMethod(freeCharge, method,
				groupId, limit, page);
	}

}
