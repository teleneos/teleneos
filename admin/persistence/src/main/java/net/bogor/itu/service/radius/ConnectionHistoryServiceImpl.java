package net.bogor.itu.service.radius;

import javax.inject.Inject;

import net.bogor.itu.entity.radius.ConnectionHistory;
import net.bogor.itu.repository.radius.ConnectionHistoryRepository;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class ConnectionHistoryServiceImpl implements ConnectionHistoryService {

	@Inject
	private ConnectionHistoryRepository historyRepository;

	@Override
	@Transactional
	public ConnectionHistory save(ConnectionHistory connectionHistory) {
		if (StringUtils.isBlank(connectionHistory.getId())) {
			connectionHistory.setId(null);
			historyRepository.persist(connectionHistory);
		} else {
			ConnectionHistory it = historyRepository.load(connectionHistory
					.getId());
			it.setRadacct(connectionHistory.getRadacct());
			it.setUsername(connectionHistory.getUsername());

			connectionHistory = it;
		}
		return connectionHistory;
	}

	@Override
	public ConnectionHistory findByAcct(String acctUniqueId) {
		return historyRepository.findByAcct(acctUniqueId);
	}

}
