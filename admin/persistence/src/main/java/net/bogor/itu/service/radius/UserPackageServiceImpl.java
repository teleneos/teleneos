package net.bogor.itu.service.radius;

import javax.inject.Inject;

import net.bogor.itu.entity.radius.UserPackage;
import net.bogor.itu.repository.radius.UserPackageRepository;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Dian Aditya
 * 
 */
@Service
@Transactional(readOnly = true)
public class UserPackageServiceImpl implements UserPackageService {

	@Inject
	private UserPackageRepository packageRepository;

	public UserPackage findActivePackage(String userId) {
		return packageRepository.findActive(userId);
	}

	@Override
	@Transactional
	public UserPackage save(UserPackage userPackage) {
		if (StringUtils.isBlank(userPackage.getId())) {
			userPackage.setId(null);

			packageRepository.persist(userPackage);
		} else {
			UserPackage up = packageRepository.load(userPackage.getId());
			up.setEndDate(userPackage.getEndDate());
			up.setInternetPackage(userPackage.getInternetPackage());
			up.setStatus(userPackage.getStatus());
			up.setUser(userPackage.getUser());
			up.setQuotaBalance(userPackage.getQuotaBalance());
			up.setUnlimited(userPackage.isUnlimited());
			up.getLogInformation().setStatusFlag(
					userPackage.getLogInformation().getStatusFlag());

			userPackage = up;
		}

		return userPackage;
	}
}
