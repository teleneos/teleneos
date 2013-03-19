package net.bogor.itu.repository.pos;

import net.bogor.itu.entity.pos.TransactionHeader;
import net.bogor.itu.persistence.PersistenceRepository;

import org.apache.commons.lang.StringUtils;
import org.meruvian.yama.persistence.EntityListWrapper;
import org.meruvian.yama.persistence.LogInformation.StatusFlag;
import org.springframework.stereotype.Repository;

@Repository
public class TransactionHeaderRepository extends
		PersistenceRepository<TransactionHeader> {
	public EntityListWrapper<TransactionHeader> findByKeyword(String keyword,
			String order, String orderBy, int limit, int page, String condition) {

		String criteria = "(th.code LIKE ? AND th.user.name.first LIKE ? AND th.user.name.last LIKE ?)";
		criteria = criteria.replace("AND", condition);
		criteria += " AND th.logInformation.statusFlag = ? ORDER BY "
				+ StringUtils.defaultIfEmpty(order, "th.id") + " " + orderBy;
		Object[] params = { keyword, keyword, keyword,
				StatusFlag.ACTIVE };
		for (int i = 0; i < params.length - 1; i++) {
			if (params[i] instanceof String || params[i] == null) {
				params[i] = StringUtils.defaultIfEmpty((String) params[i], "");
				params[i] = StringUtils.join(new String[] { "%",
						(String) params[i], "%" });
			}
		}

		return findAll(limit, page, "th", criteria, params);
	}
}
