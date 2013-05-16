package net.bogor.itu.service.ticket;

import javax.inject.Inject;

import net.bogor.itu.entity.ticket.TicketCategory;
import net.bogor.itu.repository.ticket.TicketCategoryRepository;

import org.apache.commons.lang.StringUtils;
import org.meruvian.yama.persistence.EntityListWrapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class TicketCategoryServiceImpl implements TicketCategoryService {

	@Inject
	private TicketCategoryRepository categoryRepository;

	@Override
	public EntityListWrapper<TicketCategory> findByKeyword(String keyword,
			String order, String orderBy, int limit, int page) {
		if (StringUtils.isBlank(keyword))
			return categoryRepository.findAll(limit, page);
		return categoryRepository.findByKeyword(keyword, order, orderBy, limit,
				page, "OR");
	}

	@Override
	@Transactional
	public TicketCategory save(TicketCategory ticketCategory) {
		if (StringUtils.isBlank(ticketCategory.getId())) {
			ticketCategory.setId(null);
			categoryRepository.persist(ticketCategory);
		} else {
			TicketCategory dt = categoryRepository.load(ticketCategory.getId());
			dt.setName(ticketCategory.getName());
			dt.setDescription(ticketCategory.getDescription());
			ticketCategory = dt;
		}
		return ticketCategory;
	}

	@Override
	public TicketCategory findById(String id) {
		return categoryRepository.findById(id);
	}

	@Override
	@Transactional
	public void remove(TicketCategory answer) {
		categoryRepository.delete(answer);
	}

}
