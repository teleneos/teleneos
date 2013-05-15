package net.bogor.itu.service.ticket;

import net.bogor.itu.entity.ticket.PremadeAnswer;

import org.meruvian.yama.persistence.EntityListWrapper;

public interface PremadeAnswerService {
	EntityListWrapper<PremadeAnswer> findByKeyword(String keyword, String order,
			String orderBy, int limit, int page);
	PremadeAnswer save(PremadeAnswer premadeAnswer);
	PremadeAnswer findById(String id);
}
