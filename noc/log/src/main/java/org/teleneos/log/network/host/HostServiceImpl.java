/**
 * 
 */
package org.teleneos.log.network.host;

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
public class HostServiceImpl implements HostService {

	@Inject
	private HostRepository hostRepository;

	@Override
	public EntityListWrapper<Host> findLatestClientStatus(String telecentre,
			int limit, int page) {
		return hostRepository.findHosts(telecentre, limit, page);
	}

}
