package org.teleneos.pos.requisition;

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
public class RequisitionImplService implements RequisitionService {

	@Inject
	private RequisitionRepository requisitionRepository;

	@Override
	public Requisition findById(String id) {
		return requisitionRepository.findById(id);
	}

	@Override
	@Transactional
	public Requisition save(Requisition requisition) {
		if (StringUtils.isBlank(requisition.getId())) {
			requisition.setId(null);

			requisitionRepository.persist(requisition);
		} else {
			Requisition r = requisitionRepository.load(requisition.getId());
			r.setTitle(requisition.getTitle());
			r.setDescription(requisition.getDescription());
			r.setDueDate(requisition.getDueDate());

			requisition = r;
		}
		return requisition;
	}

	@Override
	public EntityListWrapper<Requisition> findByKeyword(String keyword,
			String order, String orderBy, int limit, int page) {
		if (StringUtils.isBlank(keyword))
			return requisitionRepository.findAll(limit, page);
		return requisitionRepository.findByKeyword(keyword, order, orderBy,
				limit, page, "OR");
	}

}
