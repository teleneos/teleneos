package org.meruvian.yama.security.user.service;

import javax.inject.Inject;

import org.apache.commons.lang.StringUtils;
import org.meruvian.yama.persistence.EntityListWrapper;
import org.meruvian.yama.security.user.BackendUser;
import org.meruvian.yama.security.user.User;
import org.meruvian.yama.security.user.repository.UserRepository;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.security.authentication.encoding.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Dian Aditya
 * 
 */
@Service
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService {
	private UserRepository userRepo;

	private PasswordEncoder encoder;

	@Override
	@Transactional
	public User save(User user) {
		if (StringUtils.isBlank(user.getId())) {
			user.setId(null);
			BackendUser b = user.getUser();
			b.setPassword(encoder.encodePassword(b.getPassword(), null));

			userRepo.persist(user);
		} else {
			BackendUser bu = user.getUser();

			User u = null;
			try {
				u = userRepo.findByUsername(bu.getUsername());
			} catch (Exception e) {
				e.printStackTrace();
			}

			BackendUser b = u.getUser();

			b.setUsername(bu.getUsername());
			b.setEmail(bu.getEmail());
			b.setWebsite(bu.getWebsite());
			b.setRole(bu.getRole());
			if (!b.getPassword().equals(bu.getPassword())) {
				b.setPassword(encoder.encodePassword(bu.getPassword(), null));
			}

			u.setAddress(user.getAddress());
			u.setBirthDate(user.getBirthDate());
			u.setMale(user.isMale());
			u.setName(user.getName());
			u.setPhone(user.getPhone());
			u.setIdcard(user.getIdcard());
			u.setOccupation(user.getOccupation());
			u.getLogInformation().setStatusFlag(
					user.getLogInformation().getStatusFlag());

			user = u;

			userRepo.persist(u);
		}

		return user;
	}

	@Override
	public User findByUsername(String username) {
		return userRepo.findByUsername(username);
	}

	@Override
	public EntityListWrapper<User> findByUsername(String username, int limit,
			int page) {
		return userRepo.findByUsername(username, limit, page);
	}

	@Override
	public EntityListWrapper<User> findByTelecentre(String telecentre,
			String username, int limit, int page) {
		return userRepo.findByTelecentre(telecentre, username, limit, page);
	}

	@Override
	public User remove(User user) {
		return null;
	}

	@Override
	@Transactional
	public User changePassword(String username, String newpass) {
		User user = findByUsername(username);
		BackendUser b = user.getUser();

		if (!b.getPassword().equals(newpass)) {
			b.setPassword(encoder.encodePassword(newpass, null));
		}

		userRepo.persist(user);

		return user;
	}

	@Override
	@Transactional
	public void initUser() {
		BackendUser user = new BackendUser();
		user.setUsername("admin");
		user.setPassword(encoder.encodePassword("admin", null));
		user.setRole("ADMINISTRATOR");

		User u = new User();
		u.setUser(user);

		save(u);
	}

	@Inject
	public void setApplicationContext(ApplicationContext context,
			@Value("${auth.provider}") String authProvider)
			throws BeansException {
		Class<UserRepository> urClass = UserRepository.class;
		userRepo = context.getBean(authProvider + urClass.getSimpleName(),
				urClass);

		Class<PasswordEncoder> peClass = PasswordEncoder.class;
		encoder = context.getBean(authProvider + peClass.getSimpleName(),
				peClass);
	}
}