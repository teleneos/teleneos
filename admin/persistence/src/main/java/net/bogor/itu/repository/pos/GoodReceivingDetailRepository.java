package net.bogor.itu.repository.pos;

import net.bogor.itu.entity.pos.GoodReceivingDetail;
import net.bogor.itu.persistence.PersistenceRepository;

import org.meruvian.yama.persistence.EntityListWrapper;
import org.springframework.stereotype.Repository;

/**
 * @author Edy Setiawan
 * 
 */
@Repository
public class GoodReceivingDetailRepository extends
		PersistenceRepository<GoodReceivingDetail> {

	public EntityListWrapper<GoodReceivingDetail> findByKeyword(String keyword,
			int limit, int page) {

		String criteria = "(gr.goodReceiving.id = ?)";
		Object[] params = { keyword };

		return findAll(limit, page, "gr", criteria, params);
	}

	public EntityListWrapper<GoodReceivingDetail> findByParent(String id,
			int limit, int page) {
		String criteria = "i.goodReceiving.id = ?";
		Object[] params = { id };
		return findAll(limit, page, "i", criteria, params);
	}

}