package net.bogor.itu.service.admin;

import javax.inject.Inject;

import net.bogor.itu.entity.admin.ServicePackage;
import net.bogor.itu.repository.admin.ServicePackageRepository;

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
public class ServicePackageServiceImpl implements ServicePackageService {

	@Inject
	private ServicePackageRepository serviceRepository;

	@Override
	public ServicePackage findById(String id) {
		return serviceRepository.findById(id);
	}

	@Override
	@Transactional
	public ServicePackage save(ServicePackage service) {
		if (StringUtils.isBlank(service.getId())) {
			service.setId(null);

			serviceRepository.persist(service);
		} else {
			ServicePackage s = serviceRepository.load(service.getId());
			s.setCalculation(service.getCalculation());
			s.setLimitQuota(service.isLimitQuota());
			s.setLimitTime(service.isLimitTime());
			s.setName(service.getName());
			s.setQuotaLimit(service.getQuotaLimit());
			s.setTimeLimit(service.getTimeLimit());
			s.setType(service.getType());
			s.setUnitPrice(service.getUnitPrice());

			service = s;
		}

		return service;
	}

	@Override
	public EntityListWrapper<ServicePackage> findByKeyword(String keyword,
			int limit, int page) {
		if (StringUtils.isBlank(keyword))
			return serviceRepository.findAll(limit, page);
		return serviceRepository.findByKeyword(keyword, limit, page);
	}
}