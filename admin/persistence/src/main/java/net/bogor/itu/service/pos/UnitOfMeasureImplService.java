package net.bogor.itu.service.pos;

import javax.inject.Inject;

import net.bogor.itu.entity.pos.UnitOfMeasure;
import net.bogor.itu.repository.pos.UnitOfMeasureRepository;

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
public class UnitOfMeasureImplService implements UnitOfMeasureService{
	
	@Inject
	private UnitOfMeasureRepository uomRepository;

	@Override
	public UnitOfMeasure findById(String id) {
		return uomRepository.findById(id);
	}

	@Override
	@Transactional
	public UnitOfMeasure save(UnitOfMeasure unitOfMeasure) {
		if (StringUtils.isBlank(unitOfMeasure.getId())) {
			unitOfMeasure.setId(null);

			uomRepository.persist(unitOfMeasure);
		} else {
			UnitOfMeasure it = uomRepository.load(unitOfMeasure.getId());
			it.setName(unitOfMeasure.getName());
			it.setDescription(unitOfMeasure.getDescription());

			unitOfMeasure = it;
		}
		return unitOfMeasure;
	}

	@Override
	public EntityListWrapper<UnitOfMeasure> findByKeyword(String keyword,
			String order, String orderBy, int limit, int page) {
		if (StringUtils.isBlank(keyword))
			return uomRepository.findAll(limit, page);
		return uomRepository.findByKeyword(keyword,order,orderBy,limit, page,"OR");
	}

}
