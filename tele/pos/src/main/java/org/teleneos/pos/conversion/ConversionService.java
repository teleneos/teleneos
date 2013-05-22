package org.teleneos.pos.conversion;

import org.meruvian.yama.persistence.EntityListWrapper;

public interface ConversionService {

	Conversion findById(String id);

	Conversion save(Conversion conversion);

	EntityListWrapper<Conversion> findAll(String keyword, int limit, int page);

	Conversion findConversion(String uom1, String uom2);

}
