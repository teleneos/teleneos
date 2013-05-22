package org.teleneos.pos.inventoryonhand;

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
public class InventoryOnhandImplService implements InventoryOnhandService {

	@Inject
	private InventoryOnhandRepository inventoryOnhandRepository;

	@Override
	public InventoryOnhand findById(String id) {
		return inventoryOnhandRepository.findById(id);
	}

	@Override
	@Transactional
	public InventoryOnhand save(InventoryOnhand inventoryOnhand) {
		if (StringUtils.isBlank(inventoryOnhand.getId())) {
			inventoryOnhand.setId(null);

			inventoryOnhandRepository.persist(inventoryOnhand);
		} else {
			InventoryOnhand ion = inventoryOnhandRepository
					.load(inventoryOnhand.getId());
			ion.setItem(inventoryOnhand.getItem());
			ion.setStock(inventoryOnhand.getStock());

			inventoryOnhand = ion;
		}
		return inventoryOnhand;
	}

	@Override
	public EntityListWrapper<InventoryOnhand> findByKeyword(String keyword,
			String order, String orderBy, int limit, int page) {
		if (StringUtils.isBlank(keyword))
			return inventoryOnhandRepository.findAll(limit, page);
		return inventoryOnhandRepository.findByKeyword(keyword, order, orderBy,
				limit, page, "OR");
	}

}
