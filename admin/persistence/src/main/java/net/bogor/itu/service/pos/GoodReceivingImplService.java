package net.bogor.itu.service.pos;

import javax.inject.Inject;

import net.bogor.itu.entity.pos.GoodReceiving;
import net.bogor.itu.entity.pos.InventoryOnhand;
import net.bogor.itu.entity.pos.ItemType;
import net.bogor.itu.repository.pos.GoodReceivingRepository;
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
public class GoodReceivingImplService implements GoodReceivingService{
	InventoryOnhand inventoryOnhand = new InventoryOnhand();
	
	@Inject
	private GoodReceivingRepository goodReceivingRepository;
	
	@Inject
	private InventoryOnhandRepository inventoryOnhandRepository;

	@Inject
	private ItemTypeService itemTypeService;
	@Override
	public GoodReceiving findById(String id) {
		return goodReceivingRepository.findById(id);
	}

	@Override
	@Transactional	
	public GoodReceiving save(GoodReceiving goodReceiving) {
		if (StringUtils.isBlank(goodReceiving.getId())) {
			goodReceiving.setId(null);
			ItemType type = itemTypeService.findById(goodReceiving.getItemType().getId());
			
			int qtyConvert = Integer.parseInt(goodReceiving.getQuantity() != null ? goodReceiving.getQuantity() : "0") * Integer.parseInt(type.getUnit() != null ? type.getUnit(): "0");
			goodReceiving.setQuantity("" + qtyConvert);
			InventoryOnhand onhand = inventoryOnhandRepository.findByItem(goodReceiving.getItem()
					.getId()); 
			goodReceivingRepository.persist(goodReceiving);
			if (onhand != null) {
				onhand.setStock(onhand.getStock()
						+ Integer.parseInt(goodReceiving.getQuantity()));
			} else {
				onhand = new InventoryOnhand();
				onhand.setItem(goodReceiving.getItem());
				onhand.setStock(qtyConvert);
			}
			inventoryOnhandRepository.persist(onhand);
		} else {
			GoodReceiving gr = goodReceivingRepository.load(goodReceiving.getId());
			gr.setStatus(goodReceiving.getStatus());

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
