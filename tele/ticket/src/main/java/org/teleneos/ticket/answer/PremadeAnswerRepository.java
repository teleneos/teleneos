package org.teleneos.ticket.answer;


import org.apache.commons.lang.StringUtils;
import org.meruvian.yama.persistence.EntityListWrapper;
import org.meruvian.yama.persistence.LogInformation.StatusFlag;
import org.meruvian.yama.persistence.PersistenceRepository;
import org.springframework.stereotype.Repository;

@Repository
public class PremadeAnswerRepository extends PersistenceRepository<PremadeAnswer> {

	public EntityListWrapper<PremadeAnswer> findByKeyword(String keyword,
			String order, String orderBy, int limit, int page, String condition) {

		String criteria = "(i.title LIKE ? AND i.content LIKE ?)";
		criteria = criteria.replace("AND", condition);
		criteria += " AND i.logInformation.statusFlag = ? ORDER BY "
				+ StringUtils.defaultIfEmpty(order, "i.id") + " " + orderBy;
		Object[] params = { keyword, StatusFlag.ACTIVE };
		for (int i = 0; i < params.length - 1; i++) {
			if (params[i] instanceof String || params[i] == null) {
				params[i] = StringUtils.defaultIfEmpty((String) params[i], "");
				params[i] = StringUtils.join(new String[] { "%",
						(String) params[i], "%" });
			}
		}

		return findAll(limit, page, "i", criteria, params);
	}
	
	public EntityListWrapper<PremadeAnswer> findByFaq(String keyword,
			String order, String orderBy, int limit, int page, String condition) {
		String criteria = "(i.title LIKE ? AND i.content LIKE ?)";
		criteria = criteria.replace("AND", condition);
		criteria += "AND i.faq = ? AND i.logInformation.statusFlag = ? ORDER BY "
				+ StringUtils.defaultIfEmpty(order, "i.id") + " " + orderBy;
		Object[] params = { keyword, keyword, true, StatusFlag.ACTIVE };
		for (int i = 0; i < params.length - 1; i++) {
			if (params[i] instanceof String || params[i] == null) {
				params[i] = StringUtils.defaultIfEmpty((String) params[i], "");
				params[i] = StringUtils.join(new String[] { "%",
						(String) params[i], "%" });
			}
		}

		return findAll(limit, page, "i", criteria, params);
	}
}
