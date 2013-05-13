package net.bogor.itu.service.ticket;

import net.bogor.itu.entity.ticket.TicketThread;

import org.meruvian.yama.persistence.EntityListWrapper;

public interface TicketThreadService {
	
	TicketThread save(TicketThread department);

	EntityListWrapper<TicketThread> findAll(int limit, int page);
	
	EntityListWrapper<TicketThread> findByTicket(String ticket,
			String order, String orderBy, int limit, int page);
}
