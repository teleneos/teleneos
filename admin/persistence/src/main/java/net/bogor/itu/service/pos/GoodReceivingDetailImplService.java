package net.bogor.itu.service.pos;

import javax.inject.Inject;

import net.bogor.itu.entity.pos.GoodReceivingDetail;
import net.bogor.itu.repository.pos.GoodReceivingDetailRepository;

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
public class GoodReceivingDetailImplService implements
		GoodReceivingDetailService {

	@Inject
	private GoodReceivingDetailRepository goodReceivingDetailRepository;

	@Override
	public GoodReceivingDetail findById(String id) {
		return goodReceivingDetailRepository.findById(id);
	}

	@Override
	@Transactional
	public GoodReceivingDetail save(GoodReceivingDetail goodReceivingDetail) {
		if (StringUtils.isBlank(goodReceivingDetail.getId())) {
			goodReceivingDetail.setId(null);

			goodReceivingDetailRepository.persist(goodReceivingDetail);
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

}
