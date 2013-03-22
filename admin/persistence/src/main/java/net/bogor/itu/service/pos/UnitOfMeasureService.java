package net.bogor.itu.service.pos;

import net.bogor.itu.entity.pos.UnitOfMeasure;

import org.meruvian.yama.persistence.EntityListWrapper;

/**
 * @author Edy Setiawan
 * 
 */
public interface UnitOfMeasureService {
	UnitOfMeasure findById(String id);

	UnitOfMeasure save(UnitOfMeasure unitOfMeasure);

	EntityListWrapper<UnitOfMeasure> findByKeyword(String keyword, String order,
			String orderBy, int limit, int page);

}
