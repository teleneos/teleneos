package org.teleneos.ticket.ticket;


import javax.inject.Inject;

import org.apache.commons.lang.StringUtils;
import org.meruvian.yama.persistence.EntityListWrapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class TicketServiceImpl implements TicketService {

	@Inject
	private TicketRepository ticketRepository;

	@Override
	public EntityListWrapper<Ticket> findByKeyword(String keyword,
			String order, String orderBy, int limit, int page) {
		if (StringUtils.isBlank(keyword))
			return ticketRepository.findAll(limit, page);
		return ticketRepository.findByKeyword(keyword, order, orderBy,
				limit, page, "OR");
	}

	@Override
	@Transactional
	public Ticket save(Ticket ticket) {
		if (StringUtils.isBlank(ticket.getId())) {
			ticket.setId(null);
			ticketRepository.persist(ticket);
		} else {
			Ticket dt = ticketRepository.load(ticket.getId());
			dt.setSubject(ticket.getSubject());
			dt.setMessage(ticket.getMessage());
			dt.setPriority(ticket.getPriority());
			dt.getLogInformation().setStatusFlag(ticket.getLogInformation().getStatusFlag());
			ticket = dt;
		}
		return ticket;
	}

	@Override
	public EntityListWrapper<Ticket> findByMe(String keyword, String user, String order,
			String orderBy, int limit, int page) {
		if (StringUtils.isBlank(keyword))
			return ticketRepository.findByMe("", user, order, orderBy,
					limit, page, "OR");
		return ticketRepository.findByMe(keyword, user, order, orderBy,
				limit, page, "OR");
	}

	@Override
	public Ticket findById(String id) {
		return ticketRepository.findById(id);
	}

}
