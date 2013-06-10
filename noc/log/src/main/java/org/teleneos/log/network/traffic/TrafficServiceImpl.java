/**
 * 
 */
package org.teleneos.log.network.traffic;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.teleneos.log.network.traffic.Traffic.TrafficType;

/**
 * @author Dian Aditya
 * 
 */
@Service
@Transactional(readOnly = true)
public class TrafficServiceImpl implements TrafficService {

	@Inject
	private TrafficRepository trafficRepository;

	@Override
	public List<Traffic> findLatestIncoming(String telecentre, int limit) {
		return trafficRepository.findLatestTraffic(telecentre,
				TrafficType.INCOMING, limit);
	}

	@Override
	public List<Traffic> findLatestOutgoing(String telecentre, int limit) {
		return trafficRepository.findLatestTraffic(telecentre,
				TrafficType.OUTGOING, limit);
	}

}
