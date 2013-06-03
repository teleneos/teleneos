package org.teleneos.ticket.answer;


import javax.inject.Inject;

import org.apache.commons.lang.StringUtils;
import org.meruvian.yama.persistence.EntityListWrapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class PremadeAnswerServiceImpl implements PremadeAnswerService {

	@Inject
	private PremadeAnswerRepository premadeAnswerRepository;

	@Override
	public EntityListWrapper<PremadeAnswer> findByKeyword(String keyword,
			String order, String orderBy, int limit, int page) {
		if (StringUtils.isBlank(keyword))
			return premadeAnswerRepository.findAll(limit, page);
		return premadeAnswerRepository.findByKeyword(keyword, order, orderBy,
				limit, page, "OR");
	}

	@Override
	@Transactional
	public PremadeAnswer save(PremadeAnswer premadeAnswer) {
		if (StringUtils.isBlank(premadeAnswer.getId())) {
			premadeAnswer.setId(null);
			premadeAnswerRepository.persist(premadeAnswer);
		} else {
			PremadeAnswer dt = premadeAnswerRepository.load(premadeAnswer
					.getId());
			dt.setTitle(premadeAnswer.getTitle());
			dt.setContent(premadeAnswer.getContent());
			premadeAnswer = dt;
		}
		return premadeAnswer;
	}

	@Override
	public PremadeAnswer findById(String id) {
		return premadeAnswerRepository.findById(id);
	}

	@Override
	@Transactional
	public void remove(PremadeAnswer answer) {
		premadeAnswerRepository.delete(answer);
	}

	@Override
	public EntityListWrapper<PremadeAnswer> findByFaq(String keyword,
			String order, String orderBy, int limit, int page) {
		return premadeAnswerRepository.findByFaq(keyword, order, orderBy,
				limit, page, "OR");
	}

}
