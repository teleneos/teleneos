package org.teleneos.pos.po;

import javax.inject.Inject;

import org.apache.commons.lang.StringUtils;
import org.meruvian.yama.persistence.EntityListWrapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Edy Setiawan
 * 
 */
@Service
@Transactional(readOnly = true)
public class PurchaseOrderDetailImplService implements
		PurchaseOrderDetailService {

	@Inject
	private PurchaseOrderDetailRepository purchaseOrderDetailRepository;

	@Override
	public PurchaseOrderDetail findById(String id) {
		return purchaseOrderDetailRepository.findById(id);
	}

	@Override
	@Transactional
	public PurchaseOrderDetail save(PurchaseOrderDetail purchaseOrderDetail) {
		if (StringUtils.isBlank(purchaseOrderDetail.getId())) {
			purchaseOrderDetail.setId(null);

			purchaseOrderDetailRepository.persist(purchaseOrderDetail);
		} else {
			PurchaseOrderDetail po = purchaseOrderDetailRepository
					.load(purchaseOrderDetail.getId());
			po.setPurchaseOrder(purchaseOrderDetail.getPurchaseOrder());
			po.setItem(purchaseOrderDetail.getItem());
			po.setQuantity(purchaseOrderDetail.getQuantity());
			po.setPrice(purchaseOrderDetail.getPrice());

			purchaseOrderDetail = po;
		}
		return purchaseOrderDetail;
	}

	@Override
	public EntityListWrapper<PurchaseOrderDetail> findByKeyword(String keyword,
			int limit, int page) {
		if (StringUtils.isBlank(keyword))
			return purchaseOrderDetailRepository.findAll(limit, page);
		return purchaseOrderDetailRepository
				.findByKeyword(keyword, limit, page);
	}

}
