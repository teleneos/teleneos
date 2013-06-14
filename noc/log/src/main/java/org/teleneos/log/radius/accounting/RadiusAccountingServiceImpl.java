/**
 * 
 */
package org.teleneos.log.radius.accounting;

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
public class RadiusAccountingServiceImpl implements RadiusAccountingService {

	@Inject
	private RadiusAccountingRepository acctRepository;

	@Override
	public EntityListWrapper<RadiusAccounting> findAll(String telecentre,
			String user, long from, long to, String order, int limit, int page) {
		return acctRepository.findAll(telecentre, user, from, to, order, limit,
				page);
	}

}
