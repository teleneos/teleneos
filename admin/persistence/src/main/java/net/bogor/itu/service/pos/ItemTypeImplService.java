package net.bogor.itu.service.pos;

import javax.inject.Inject;

import net.bogor.itu.entity.pos.ItemType;
import net.bogor.itu.repository.pos.ItemTypeRepository;

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
public class ItemTypeImplService implements ItemTypeService{
	
	@Inject
	private ItemTypeRepository itemTypeRepository;

	@Override
	public ItemType findById(String id) {
		return itemTypeRepository.findById(id);
	}

	@Override
	@Transactional
	public ItemType save(ItemType itemType) {
		if (StringUtils.isBlank(itemType.getId())) {
			itemType.setId(null);

			itemTypeRepository.persist(itemType);
		} else {
			ItemType it = itemTypeRepository.load(itemType.getId());
			it.setName(itemType.getName());
			it.setDescription(itemType.getDescription());

			itemType = it;
		}
		return itemType;
	}

	@Override
	public EntityListWrapper<ItemType> findByKeyword(String keyword,
			String order, String orderBy, int limit, int page) {
		if (StringUtils.isBlank(keyword))
			return itemTypeRepository.findAll(limit, page);
		return itemTypeRepository.findByKeyword(keyword,order,orderBy,limit, page,"OR");
	}

}
