package net.bogor.itu.service.ticket;

import net.bogor.itu.entity.ticket.TicketCategory;

import org.meruvian.yama.persistence.EntityListWrapper;

public interface TicketCategoryService {
	
	EntityListWrapper<TicketCategory> findByKeyword(String keyword,
			String order, String orderBy, int limit, int page);

	TicketCategory save(TicketCategory ticketCategory);

	TicketCategory findById(String id);

	void remove(TicketCategory ticketCategory);
	
}
