package org.teleneos.pos.item;

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
public class ItemCategoryImplService implements ItemCategoryService {

	@Inject
	private ItemCategoryRepository itemCategoryRepository;

	@Override
	public ItemCategory findById(String id) {
		return itemCategoryRepository.findById(id);
	}

	@Override
	@Transactional
	public ItemCategory save(ItemCategory itemCategory) {
		if (StringUtils.isBlank(itemCategory.getId())) {
			itemCategory.setId(null);

			itemCategoryRepository.persist(itemCategory);
		} else {
			ItemCategory ic = itemCategoryRepository.load(itemCategory.getId());
			ic.setName(itemCategory.getName());
			ic.setDescription(itemCategory.getDescription());

			itemCategory = ic;
		}
		return itemCategory;
	}

	@Override
	public EntityListWrapper<ItemCategory> findByKeyword(String keyword,
			String order, String orderBy, int limit, int page) {
		if (StringUtils.isBlank(keyword))
			return itemCategoryRepository.findAll(limit, page);
		return itemCategoryRepository.findByKeyword(keyword, order, orderBy,
				limit, page, "OR");
	}

}
