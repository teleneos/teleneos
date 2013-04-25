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
import org.meruvian.yama.persistence.LogInformation.StatusFlag;
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
	private GoodReceivingService goodReceivingService;
	
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
	public void toInventory(String goodReceivingId) {
		for (GoodReceivingDetail goodReceivingDetail : goodReceivingDetailRepository
				.findByParent(goodReceivingId, 0, 0).getEntityList()) {
			Conversion conversion = conversionService.findConversion(goodReceivingDetail.getItem()
					.getUom().getId(), goodReceivingDetail.getUom().getId());
			int qtyConvert = 0;
			if (goodReceivingDetail.getItem().getUom().getId()
					.equals(goodReceivingDetail.getUom().getId())) {
				qtyConvert = goodReceivingDetail.getQuantity();
			} else {
				if (conversion.getUomFrom().getId()
						.equals(goodReceivingDetail.getUom().getId())) {
					// qtyConvert = (goodReceivingDetail.getQuantity() /
					// conversion.getQty()) * conversion.getMultiplyRate();
					qtyConvert = goodReceivingDetail.getQuantity()
							* conversion.getMultiplyRate();
				} else if (conversion.getUomTo().getId()
						.equals(goodReceivingDetail.getUom().getId())) {
					// qtyConvert = (goodReceivingDetail.getQuantity() /
					// conversion.getMultiplyRate()) * conversion.getQty();
					qtyConvert = goodReceivingDetail.getQuantity()
							/ conversion.getMultiplyRate();
				}
			}
			goodReceivingService.findById(goodReceivingId).getLogInformation().setStatusFlag(StatusFlag.INACTIVE);
			InventoryOnhand onhand = inventoryOnhandRepository
					.findByItem(goodReceivingDetail.getItem().getId());
			if (onhand != null) {
				onhand.setStock(onhand.getStock()
						+ qtyConvert);
			} else {
				onhand = new InventoryOnhand();
				onhand.setItem(goodReceivingDetail.getItem());
				onhand.setStock(qtyConvert);
			}
			inventoryOnhandRepository.persist(onhand);
		}
	}

	@Override
	@Transactional
	public GoodReceivingDetail save(GoodReceivingDetail goodReceivingDetail) throws InvaidUnitOfMeasurementException {
		if (StringUtils.isBlank(goodReceivingDetail.getId())) {
			goodReceivingDetail.setId(null);
			Item item = itemService.findById(goodReceivingDetail.getItem().getId());
			Conversion conversion = conversionService
					.findConversion(goodReceivingDetail.getUom().getId(), item
							.getUom().getId());
			if(conversion!=null){
				goodReceivingDetailRepository.persist(goodReceivingDetail);				
			}else{
				throw new InvaidUnitOfMeasurementException();
			}
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
