package net.bogor.itu.service.pos;

import javax.inject.Inject;

import net.bogor.itu.entity.pos.Conversion;
import net.bogor.itu.entity.pos.GoodReceivingDetail;
import net.bogor.itu.entity.pos.InventoryOnhand;
import net.bogor.itu.entity.pos.Item;
import net.bogor.itu.repository.pos.GoodReceivingDetailRepository;
import net.bogor.itu.repository.pos.InventoryOnhandRepository;

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
public class GoodReceivingDetailImplService implements
		GoodReceivingDetailService {

	@Inject
	private GoodReceivingDetailRepository goodReceivingDetailRepository;

	@Inject
	private InventoryOnhandRepository inventoryOnhandRepository;

	@Inject
	private ConversionService conversionService;

	@Inject
	private ItemService itemService;

	@Override
	public GoodReceivingDetail findById(String id) {
		return goodReceivingDetailRepository.findById(id);
	}

	@Override
	@Transactional
	public GoodReceivingDetail save(GoodReceivingDetail goodReceivingDetail) {
		if (StringUtils.isBlank(goodReceivingDetail.getId())) {
			goodReceivingDetail.setId(null);
			Item item = itemService.findById(goodReceivingDetail.getItem()
					.getId());
			Conversion conversion = conversionService.findConversion(item
					.getUom().getId(), goodReceivingDetail.getUom().getId());
			int qtyConvert = 0;
			if (item.getUom().getId()
					.equals(goodReceivingDetail.getUom().getId())) {
				qtyConvert = goodReceivingDetail.getQuantity();
			} else {
				if (conversion.getUomFrom().getId()
						.equals(goodReceivingDetail.getUom().getId())) {
					qtyConvert = (goodReceivingDetail.getQuantity() / conversion
							.getQty()) * conversion.getMultiplyRate();
				} else if (conversion.getUomTo().getId()
						.equals(goodReceivingDetail.getUom().getId())) {
					qtyConvert = (goodReceivingDetail.getQuantity() / conversion
							.getMultiplyRate()) * conversion.getQty();
				}
			}
			goodReceivingDetail.setQuantity(qtyConvert);
			InventoryOnhand onhand = inventoryOnhandRepository
					.findByItem(goodReceivingDetail.getItem().getId());
			goodReceivingDetailRepository.persist(goodReceivingDetail);
			if (onhand != null) {
				onhand.setStock(onhand.getStock()
						+ goodReceivingDetail.getQuantity());
			} else {
				onhand = new InventoryOnhand();
				onhand.setItem(goodReceivingDetail.getItem());
				onhand.setStock(qtyConvert);
			}
			inventoryOnhandRepository.persist(onhand);

		} else {
			GoodReceivingDetail grd = goodReceivingDetailRepository
					.load(goodReceivingDetail.getId());
			grd.setGoodReceiving(goodReceivingDetail.getGoodReceiving());
			grd.setItem(goodReceivingDetail.getItem());
			grd.setQuantity(goodReceivingDetail.getQuantity());

			goodReceivingDetail = grd;
		}
		return goodReceivingDetail;
	}

	@Override
	public EntityListWrapper<GoodReceivingDetail> findByKeyword(String keyword,
			int limit, int page) {
		if (StringUtils.isBlank(keyword))
			return goodReceivingDetailRepository.findAll(limit, page);
		return goodReceivingDetailRepository
				.findByKeyword(keyword, limit, page);
	}

	@Override
	public EntityListWrapper<GoodReceivingDetail> findByParent(String id,
			int limit, int page) {
		return goodReceivingDetailRepository.findByParent(id, limit, page);
	}

}
