package org.teleneos.ticket.ticket;


import org.meruvian.yama.persistence.EntityListWrapper;

public interface TicketService {
	
	EntityListWrapper<Ticket> findByKeyword(String keyword, String order,
			String orderBy, int limit, int page);

	Ticket save(Ticket department);
	
	EntityListWrapper<Ticket> findByMe(String keyword, String user, String order,
			String orderBy, int limit, int page);

	Ticket findById(String id);
	
}
