package org.teleneos.ticket.ticket;


import org.meruvian.yama.persistence.EntityListWrapper;

public interface TicketCategoryService {
	
	EntityListWrapper<TicketCategory> findByKeyword(String keyword,
			String order, String orderBy, int limit, int page);

	TicketCategory save(TicketCategory ticketCategory);

	TicketCategory findById(String id);

	void remove(TicketCategory ticketCategory);
	
}
