package net.bogor.itu.service.radius;

import net.bogor.itu.entity.radius.ConnectionHistory;

public interface ConnectionHistoryService {
	ConnectionHistory save(ConnectionHistory connectionHistory);

	ConnectionHistory findByAcct(String acctUniqueId);
}
