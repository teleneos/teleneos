package net.bogor.itu.service.pos;

import javax.inject.Inject;

import net.bogor.itu.entity.pos.Conversion;
import net.bogor.itu.repository.pos.ConversionRepository;

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
			gr.setQty(conversion.getQty());
			gr.setUomFrom(conversion.getUomFrom());
			gr.setUomTo(conversion.getUomTo());
			conversion = gr;
		}
		return conversion;
	}

	@Override
	public EntityListWrapper<Conversion> findAll(int limit, int page) {
		return conversionRepository.findAll(limit, page);
	}

	@Override
	public Conversion findConversion(String uom1, String uom2) {
		return conversionRepository.findConversion(uom1, uom2);
	}

}