package org.teleneos.pos.goodreceiving;

import javax.inject.Inject;

import org.apache.commons.lang.StringUtils;
import org.meruvian.yama.persistence.EntityListWrapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.teleneos.pos.inventoryonhand.InventoryOnhand;

/**
 * @author Edy Setiawan
 * 
 */
@Service
@Transactional(readOnly = true)
public class GoodReceivingImplService implements GoodReceivingService {
	InventoryOnhand inventoryOnhand = new InventoryOnhand();

	@Inject
	private GoodReceivingRepository goodReceivingRepository;

	@Override
	public GoodReceiving findById(String id) {
		return goodReceivingRepository.findById(id);
	}

	@Override
	@Transactional
	public GoodReceiving save(GoodReceiving goodReceiving) {
		if (StringUtils.isBlank(goodReceiving.getId())) {
			goodReceiving.setId(null);
			goodReceivingRepository.persist(goodReceiving);
		} else {
			GoodReceiving gr = goodReceivingRepository.load(goodReceiving
					.getId());
			gr.setBusinessPartner(goodReceiving.getBusinessPartner());
			gr.setDate(goodReceiving.getDate());
			gr.setInvoiceNo(goodReceiving.getInvoiceNo());
			goodReceiving = gr;
		}
		return goodReceiving;
	}

	@Override
	public EntityListWrapper<GoodReceiving> findByKeyword(String keyword,
			String order, String orderBy, int limit, int page) {
		if (StringUtils.isBlank(keyword))
			return goodReceivingRepository.findAll(limit, page);
		return goodReceivingRepository.findByKeyword(keyword, order, orderBy,
				limit, page, "OR");
	}

}
