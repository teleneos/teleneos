package org.teleneos.pos.goodreceiving;

import javax.inject.Inject;

import org.apache.commons.lang.StringUtils;
import org.meruvian.yama.persistence.EntityListWrapper;
import org.meruvian.yama.persistence.LogInformation.StatusFlag;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.teleneos.pos.conversion.Conversion;
import org.teleneos.pos.conversion.ConversionService;
import org.teleneos.pos.inventoryonhand.InventoryOnhand;
import org.teleneos.pos.inventoryonhand.InventoryOnhandRepository;
import org.teleneos.pos.item.Item;
import org.teleneos.pos.item.ItemService;
import org.teleneos.pos.stockaudit.StockAudit;
import org.teleneos.pos.stockaudit.StockAudit.Type;
import org.teleneos.pos.stockaudit.StockAuditService;
import org.teleneos.pos.uom.InvaidUnitOfMeasurementException;

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

	@Inject
	private StockAuditService auditService;

	@Override
	public GoodReceivingDetail findById(String id) {
		return goodReceivingDetailRepository.findById(id);
	}

	@Override
	@Transactional
	public void toInventory(String goodReceivingId) {
		for (GoodReceivingDetail goodReceivingDetail : goodReceivingDetailRepository
				.findByParent(goodReceivingId, 0, 0).getEntityList()) {
			Conversion conversion = conversionService.findConversion(
					goodReceivingDetail.getItem().getUom().getId(),
					goodReceivingDetail.getUom().getId());
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
			goodReceivingService.findById(goodReceivingId).getLogInformation()
					.setStatusFlag(StatusFlag.INACTIVE);
			InventoryOnhand onhand = inventoryOnhandRepository
					.findByItem(goodReceivingDetail.getItem().getId());
			if (onhand != null) {
				onhand.setStock(onhand.getStock() + qtyConvert);
			} else {
				onhand = new InventoryOnhand();
				onhand.setItem(goodReceivingDetail.getItem());
				onhand.setStock(qtyConvert);
			}
			inventoryOnhandRepository.persist(onhand);
			auditService.save(new StockAudit(onhand.getItem(), Type.INBOUND,
					qtyConvert, "receiving from "
							+ goodReceivingDetail.getGoodReceiving()
									.getBusinessPartner().getName()
							+ " invoice no "
							+ goodReceivingDetail.getGoodReceiving()
									.getInvoiceNo(), onhand.getStock()));
		}
	}

	@Override
	@Transactional
	public GoodReceivingDetail save(GoodReceivingDetail goodReceivingDetail)
			throws InvaidUnitOfMeasurementException {
		if (StringUtils.isBlank(goodReceivingDetail.getId())) {
			goodReceivingDetail.setId(null);
			Item item = itemService.findById(goodReceivingDetail.getItem()
					.getId());
			Conversion conversion = conversionService
					.findConversion(goodReceivingDetail.getUom().getId(), item
							.getUom().getId());
			if (conversion != null
					|| goodReceivingDetail.getUom().getId()
							.equals(item.getUom().getId())) {
				goodReceivingDetailRepository.persist(goodReceivingDetail);
			} else {
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
