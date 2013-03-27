package net.bogor.itu.service.pos;

import javax.inject.Inject;

import net.bogor.itu.entity.pos.GoodReceiving;
import net.bogor.itu.repository.pos.GoodReceivingRepository;

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
	
	@Inject
	private GoodReceivingRepository goodReceivingRepository;

	@Override
	public GoodReceiving findById(String id) {
		return goodReceivingRepository.findById(id);
	}

	@Override
	@Transactional
	public GoodReceiving save(GoodReceiving goodReceiving) {
		if (StringUtils.isBlank(goodReceiving.getId())) {
			goodReceiving.setId(null);

			goodReceivingRepository.persist(goodReceiving);
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