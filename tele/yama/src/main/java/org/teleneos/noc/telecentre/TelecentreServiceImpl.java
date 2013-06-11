/**
 * 
 */
package org.teleneos.noc.telecentre;

import javax.inject.Inject;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Dian Aditya
 * 
 */
@Service
@Transactional(readOnly=true)
public class TelecentreServiceImpl implements TelecentreService {
	private TelecentreRepository teleRepo;

	@Override
	@Transactional
	public Telecentre save(Telecentre telecentre) {
		if (StringUtils.isBlank(telecentre.getId())) {
			telecentre.setId(null);
			telecentre.setPassword(RandomStringUtils.random(8,
					"ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz"
							+ "1234567890"));

			teleRepo.persist(telecentre);
		} else {
			Telecentre t = teleRepo.findById(telecentre.getId());
			t.setAddress(telecentre.getAddress());
			t.setEmail(telecentre.getEmail());
			t.setLat(telecentre.getLat());
			t.setLng(telecentre.getLng());
			t.setName(telecentre.getName());
			t.setPhone(telecentre.getPhone());

			telecentre = t;
			teleRepo.persist(t);
		}

		return telecentre;
	}

	@Override
	public Telecentre findById(String id) {
		return teleRepo.findById(id);
	}


	@Override
	public Telecentre remove(Telecentre telecentre) {
		return null;
	}

	@Override
	public Telecentre changePassword(String id) {
		Telecentre telecentre = teleRepo.findById(id);
		telecentre.setPassword(RandomStringUtils.random(8));

		teleRepo.persist(telecentre);

		return telecentre;
	}

	@Inject
	public void setApplicationContext(ApplicationContext context,
			@Value("${auth.provider}") String authProvider)
			throws BeansException {
		Class<TelecentreRepository> teleClass = TelecentreRepository.class;
		teleRepo = context.getBean(authProvider + teleClass.getSimpleName(),
				teleClass);
	}

}
