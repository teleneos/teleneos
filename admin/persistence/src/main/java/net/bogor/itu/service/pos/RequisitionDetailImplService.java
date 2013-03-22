package net.bogor.itu.service.pos;

import javax.inject.Inject;

import net.bogor.itu.entity.pos.RequisitionDetail;
import net.bogor.itu.repository.pos.RequisitionDetailRepository;

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
public class RequisitionDetailImplService implements RequisitionDetailService{
	
	@Inject
	private RequisitionDetailRepository reqDetailRepository; 

	@Override
	public RequisitionDetail findById(String id) {
		return reqDetailRepository.findById(id);
	}

	@Override
	@Transactional
	public RequisitionDetail save(RequisitionDetail requisitionDetail) {
		if (StringUtils.isBlank(requisitionDetail.getId())) {
			requisitionDetail.setId(null);

			reqDetailRepository.persist(requisitionDetail);
		} else {
			RequisitionDetail td = reqDetailRepository.load(requisitionDetail.getId());
			td.setRequisition(requisitionDetail.getRequisition());
			td.setItem(requisitionDetail.getItem());
			td.setQuantity(requisitionDetail.getQuantity());
			td.setDueDate(requisitionDetail.getDueDate());

			requisitionDetail = td;
		}
		return requisitionDetail;
	}

	@Override
	public EntityListWrapper<RequisitionDetail> findByKeyword(String keyword,
			int limit, int page) {
		if (StringUtils.isBlank(keyword))
			return reqDetailRepository.findAll(limit, page);
		return reqDetailRepository.findByKeyword(keyword, limit, page);
	}
	
	

}
