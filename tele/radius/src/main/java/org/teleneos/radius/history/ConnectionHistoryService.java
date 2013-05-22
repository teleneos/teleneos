package org.teleneos.radius.history;


public interface ConnectionHistoryService {
	ConnectionHistory save(ConnectionHistory connectionHistory);

	ConnectionHistory findByAcct(String acctUniqueId);
}
