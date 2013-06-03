package org.teleneos.ticket.answer;


import org.meruvian.yama.persistence.EntityListWrapper;

public interface PremadeAnswerService {
	EntityListWrapper<PremadeAnswer> findByKeyword(String keyword, String order,
			String orderBy, int limit, int page);
	EntityListWrapper<PremadeAnswer> findByFaq(String keyword, String order,
			String orderBy, int limit, int page);
	PremadeAnswer save(PremadeAnswer premadeAnswer);
	PremadeAnswer findById(String id);
	void remove(PremadeAnswer answer);
}
