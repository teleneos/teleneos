/**
 * 
 */
package org.teleneos.log.access;

import javax.inject.Inject;

import org.meruvian.yama.persistence.EntityListWrapper;
import org.springframework.stereotype.Service;

/**
 * @author Dian Aditya
 * 
 */
@Service
public class AccessLogServiceImpl implements AccessLogService {

	@Inject
	private AccessLogRepository logRepository;

	@Override
	public EntityListWrapper<AccessLog> findAll(String telecentre, String user,
			String q, long from, long to, String order, int limit, int page)
			throws Exception {
		return logRepository.findAll(telecentre, user, q, from, to, order,
				limit, page);
	}
}
