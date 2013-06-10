/**
 * 
 */
package org.teleneos.log.availability;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.apache.commons.lang3.ArrayUtils;
import org.meruvian.yama.persistence.EntityListWrapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.teleneos.noc.telecentre.Telecentre;
import org.teleneos.noc.telecentre.TelecentreRepository;

/**
 * @author Dian Aditya
 * 
 */
@Service
@Transactional(readOnly = true)
public class PingServiceImpl implements PingService {

	@Inject
	private PingRepository pingRepository;

	@Inject
	private TelecentreRepository teleRepository;

	@Override
	public EntityListWrapper<Object[]> findLatestStatus(String name, int limit,
			int page) {
		EntityListWrapper<Object[]> results = pingRepository.findLatestStatus(
				name, limit, page);
		List<Object[]> latestStatus = new ArrayList<Object[]>();

		for (Object[] t : results.getEntityList()) {
			Telecentre telecentre = teleRepository.findById((String) t[0]);

			latestStatus.add(ArrayUtils.add(t, telecentre));
		}

		results.setEntityList(latestStatus);

		return results;
	}
}
