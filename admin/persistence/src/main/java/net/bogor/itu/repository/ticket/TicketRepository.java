package net.bogor.itu.repository.ticket;

import net.bogor.itu.entity.ticket.Ticket;
import net.bogor.itu.persistence.PersistenceRepository;

import org.apache.commons.lang.StringUtils;
import org.meruvian.yama.persistence.EntityListWrapper;
import org.meruvian.yama.persistence.LogInformation.StatusFlag;
import org.springframework.stereotype.Repository;

@Repository
public class TicketRepository extends PersistenceRepository<Ticket> {

	public EntityListWrapper<Ticket> findByKeyword(String keyword,
			String order, String orderBy, int limit, int page, String condition) {

		String criteria = "(i.subject LIKE ? AND i.message LIKE ?)";
		criteria = criteria.replace("AND", condition);
		criteria += " ORDER BY " + StringUtils.defaultIfEmpty(order, "i.id") + " " + orderBy;
		Object[] params = { keyword, keyword, StatusFlag.ACTIVE };
		for (int i = 0; i < params.length; i++) {
			if (params[i] instanceof String || params[i] == null) {
				params[i] = StringUtils.defaultIfEmpty((String) params[i], "");
				params[i] = StringUtils.join(new String[] { "%",
						(String) params[i], "%" });
			}
		}

		return findAll(limit, page, "i", criteria, params);
	}

	public EntityListWrapper<Ticket> findByMe(String keyword, String user, String order,
			String orderBy, int limit, int page, String condition) {
		String criteria = "(i.subject LIKE ? AND i.message LIKE ?)";
		criteria = criteria.replace("AND", condition);
		criteria += " AND i.logInformation.createBy = ? ORDER BY "
				+ StringUtils.defaultIfEmpty(order, "i.id") + " " + orderBy;
		Object[] params = { keyword, keyword, user};
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
