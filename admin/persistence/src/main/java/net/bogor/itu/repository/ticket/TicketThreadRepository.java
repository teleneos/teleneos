package net.bogor.itu.repository.ticket;

import net.bogor.itu.entity.ticket.TicketThread;
import net.bogor.itu.persistence.PersistenceRepository;

import org.apache.commons.lang.StringUtils;
import org.meruvian.yama.persistence.EntityListWrapper;
import org.meruvian.yama.persistence.LogInformation.StatusFlag;
import org.springframework.stereotype.Repository;

@Repository
public class TicketThreadRepository extends PersistenceRepository<TicketThread> {
	public EntityListWrapper<TicketThread> findByTicket(String ticket,
			String order, String orderBy, int limit, int page) {
		String criteria = "i.ticket.id = ? AND i.logInformation.statusFlag = ? ORDER BY "
				+ StringUtils.defaultIfEmpty(order, "i.id") + " " + orderBy;
		Object[] params = { ticket, StatusFlag.ACTIVE };
		return findAll(limit, page, "i", criteria, params);
	}
}
