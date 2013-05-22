package org.teleneos.pos.goodreceiving;

import org.meruvian.yama.persistence.EntityListWrapper;
import org.teleneos.pos.uom.InvaidUnitOfMeasurementException;

/**
 * @author Edy Setiawan
 * 
 */
public interface GoodReceivingDetailService {
	GoodReceivingDetail findById(String id);

	GoodReceivingDetail save(GoodReceivingDetail goodReceivingDetail)
			throws InvaidUnitOfMeasurementException;

	EntityListWrapper<GoodReceivingDetail> findByKeyword(String keyword,
			int limit, int page);

	EntityListWrapper<GoodReceivingDetail> findByParent(String id, int limit,
			int page);

	void toInventory(String goodReceivingId);
}
