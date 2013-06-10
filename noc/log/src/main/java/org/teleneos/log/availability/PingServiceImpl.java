/**
 * 
 */
package org.teleneos.log.availability;

import javax.inject.Inject;

import org.meruvian.yama.persistence.EntityListWrapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Dian Aditya
 * 
 */
@Service
@Transactional(readOnly = true)
public class PingServiceImpl implements PingService {

	@Inject
	private PingRepository pingRepository;

	@Override
	public EntityListWrapper<Object[]> findLatestStatus(String name, int limit,
			int page) {
		return pingRepository.findLatestStatus(name, limit, page);
	}
}
