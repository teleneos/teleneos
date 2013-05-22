package org.teleneos.ticket.ticket;


import javax.inject.Inject;

import org.apache.commons.lang.StringUtils;
import org.meruvian.yama.persistence.EntityListWrapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class TicketThreadServiceImpl implements TicketThreadService {

	@Inject
	private TicketThreadRepository threadRepository;
	
	@Override
	@Transactional
	public TicketThread save(TicketThread ticketThread) {
		if (StringUtils.isBlank(ticketThread.getId())) {
			ticketThread.setId(null);
			threadRepository.persist(ticketThread);
		} else {
			TicketThread dt = threadRepository.load(ticketThread.getId());
			dt.setMessage(ticketThread.getMessage());
			dt.setTicket(ticketThread.getTicket());
			ticketThread = dt;
		}
		return ticketThread;
	}

	@Override
	public EntityListWrapper<TicketThread> findAll(int limit, int page) {
		return threadRepository.findAll(limit, page);
	}

	@Override
	public EntityListWrapper<TicketThread> findByTicket(String ticket,
			String order, String orderBy, int limit, int page) {
		return threadRepository.findByTicket(ticket, order, orderBy, limit, page);
	}

}
