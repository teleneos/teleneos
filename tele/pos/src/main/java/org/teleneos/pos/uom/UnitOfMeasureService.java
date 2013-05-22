package org.teleneos.pos.uom;

import org.meruvian.yama.persistence.EntityListWrapper;

/**
 * @author Edy Setiawan
 * 
 */
public interface UnitOfMeasureService {
	UnitOfMeasure findById(String id);

	UnitOfMeasure save(UnitOfMeasure unitOfMeasure);

	EntityListWrapper<UnitOfMeasure> findByKeyword(String keyword,
			String order, String orderBy, int limit, int page);

}
