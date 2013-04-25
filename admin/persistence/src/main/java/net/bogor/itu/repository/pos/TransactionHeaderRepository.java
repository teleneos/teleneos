package net.bogor.itu.repository.pos;

import net.bogor.itu.entity.pos.TransactionHeader;
import net.bogor.itu.persistence.PersistenceRepository;

import org.meruvian.yama.persistence.EntityListWrapper;
import org.meruvian.yama.persistence.LogInformation.StatusFlag;
import org.springframework.stereotype.Repository;

/**
 * @author Edy Setiawan
 * 
 */
@Repository
public class TransactionHeaderRepository extends
		PersistenceRepository<TransactionHeader> {
	public EntityListWrapper<TransactionHeader> findByKeyword(String keyword,
			int limit, int page) {

		String criteria = "th.username LIKE ? AND th.logInformation.statusFlag = ? ORDER BY "
				+ "th.logInformation.createDate DESC";

		return findAll(limit, page, "th", criteria, keyword + "%",
				StatusFlag.ACTIVE);
	}
}
