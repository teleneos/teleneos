package net.bogor.itu.service.master;

import javax.inject.Inject;

import net.bogor.itu.entity.master.InternetPackage;
import net.bogor.itu.entity.master.PaymentMethod;
import net.bogor.itu.repository.master.PackageManagerRepository;

import org.apache.commons.lang.StringUtils;
import org.meruvian.yama.persistence.EntityListWrapper;
import org.springframework.dao.DataIntegrityViolationException;
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
	public InternetPackage save(InternetPackage internetPackage) throws DataIntegrityViolationException {
		if (StringUtils.isBlank(internetPackage.getId())) {
			internetPackage.setId(null);

			packageManagerRepository.persist(internetPackage);
		} else {
			InternetPackage ic = packageManagerRepository.load(internetPackage
					.getId());
			ic.setCode(internetPackage.getCode());
			ic.setName(internetPackage.getName());
			ic.setPaymentMethod(internetPackage.getPaymentMethod());
			ic.setPrice(internetPackage.getPrice());
			ic.setTime(internetPackage.getTime());
			ic.setQuota(internetPackage.getQuota());
			ic.setSpeed(internetPackage.getSpeed());
			ic.setNextSpeed(internetPackage.getNextSpeed());
			ic.getLogInformation().setStatusFlag(internetPackage.getLogInformation().getStatusFlag());
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
