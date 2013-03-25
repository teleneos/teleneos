package net.bogor.itu.service.pos;

import javax.inject.Inject;

import net.bogor.itu.entity.pos.PurchaseOrder;
import net.bogor.itu.repository.pos.PurchaseOrderRepository;

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
public class PurchaseOrderImplService implements PurchaseOrderService{
	
	@Inject
	private PurchaseOrderRepository purchaseOrderRepository;

	@Override
	public PurchaseOrder findById(String id) {
		return purchaseOrderRepository.findById(id);
	}

	@Override
	@Transactional
	public PurchaseOrder save(PurchaseOrder purchaseOrder) {
		if (StringUtils.isBlank(purchaseOrder.getId())) {
			purchaseOrder.setId(null);

			purchaseOrderRepository.persist(purchaseOrder);
		} else {
			PurchaseOrder po = purchaseOrderRepository.load(purchaseOrder.getId());
//			po.setTitle(purchaseOrder.getTitle());
//			po.setDescription(purchaseOrder.getDescription());
//			po.setDueDate(purchaseOrder.getDueDate());
//			po.setBuyer(purchaseOrder.getBuyer());
//			po.setRequisition(purchaseOrder.getRequisition());
//			po.setBusinessPartner(purchaseOrder.getBusinessPartner());
			po.setStatus(purchaseOrder.getStatus());

			purchaseOrder = po;
		}
		return purchaseOrder;
	}

	@Override
	public EntityListWrapper<PurchaseOrder> findByKeyword(String keyword,
			String order, String orderBy, int limit, int page) {
		if (StringUtils.isBlank(keyword))
		return purchaseOrderRepository.findAll(limit, page);
		return purchaseOrderRepository.findByKeyword(keyword, order, orderBy,
				limit, page, "OR");
	}
	

}
