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
public class ItemImplService implements ItemService {

	@Inject
	private ItemRepository itemRepository;

	@Override
	public Item findById(String id) {
		return itemRepository.findById(id);
	}

	@Override
	@Transactional
	public Item save(Item item) {
		if (StringUtils.isBlank(item.getId())) {
			item.setId(null);

			itemRepository.persist(item);
		} else {
			Item i = itemRepository.load(item.getId());
			i.setCode(item.getCode());
			i.setName(item.getName());
			i.setDescription(item.getDescription());
			i.setPrice(item.getPrice());
			i.setUom(item.getUom());
			i.setCategory(item.getCategory());

			item = i;
		}
		return item;
	}

	@Override
	public EntityListWrapper<Item> findByKeyword(String keyword, String order,
			String orderBy, int limit, int page) {
		if (StringUtils.isBlank(keyword))
			return itemRepository.findAll(limit, page);
		return itemRepository.findByKeyword(keyword, order, orderBy, limit,
				page, "OR");
	}

}
