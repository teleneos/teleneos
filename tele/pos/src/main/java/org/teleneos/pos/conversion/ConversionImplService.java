package org.teleneos.pos.conversion;

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
public class ConversionImplService implements ConversionService {

	@Inject
	private ConversionRepository conversionRepository;

	@Override
	public Conversion findById(String id) {
		return conversionRepository.findById(id);
	}

	@Override
	@Transactional
	public Conversion save(Conversion conversion) {
		if (StringUtils.isBlank(conversion.getId())) {
			conversion.setId(null);
			conversionRepository.persist(conversion);
		} else {
			Conversion gr = conversionRepository.load(conversion.getId());
			gr.setMultiplyRate(conversion.getMultiplyRate());
			gr.setUomFrom(conversion.getUomFrom());
			gr.setUomTo(conversion.getUomTo());
			conversion = gr;
		}
		return conversion;
	}

	@Override
	public EntityListWrapper<Conversion> findAll(String keyword, int limit,
			int page) {
		return conversionRepository.findAll(keyword, limit, page);
	}

	@Override
	public Conversion findConversion(String uom1, String uom2) {
		return conversionRepository.findConversion(uom1, uom2);
	}

}
