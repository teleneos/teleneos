package net.bogor.itu.service.admin;

import javax.inject.Inject;

import net.bogor.itu.entity.admin.User;
import net.bogor.itu.repository.admin.UserRepository;

import org.apache.commons.lang.StringUtils;
import org.meruvian.yama.persistence.EntityListWrapper;
import org.meruvian.yama.security.user.BackendUser;
import org.meruvian.yama.security.user.BackendUserDAO;
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
	@Inject
	private UserRepository userRepo;

	@Inject
	private BackendUserDAO backendUserRepo;

	@Inject
	private PasswordEncoder encoder;

	@Override
	@Transactional
	public User save(User user) {
		if (StringUtils.isBlank(user.getId())) {
			user.setId(null);

			BackendUser backendUser = user.getUser();
			backendUser.setId(null);

			backendUserRepo.persist(backendUser);
			userRepo.persist(user);
		} else {
			BackendUser bu = user.getUser();

			User u = userRepo.findById(user.getId());
			BackendUser b = u.getUser();

			b.setUsername(bu.getUsername());
			b.setEmail(bu.getEmail());
			b.setWebsite(bu.getWebsite());
			b.setRole(bu.getRole());
			if (!b.getPassword().equals(bu.getPassword())) {
				b.setPassword(encoder.encodePassword(bu.getPassword(), null));
			}
			b.getLogInformation().setStatusFlag(
					bu.getLogInformation().getStatusFlag());

			u.setAddress(user.getAddress());
			u.setBirthDate(user.getBirthDate());
			u.setMale(user.isMale());
			u.setName(user.getName());
			u.setPhone(user.getPhone());

			user = u;
		}

		return user;
	}

	@Override
	public User findById(String id) {
		return userRepo.findById(id);
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
	public User remove(User user) {
		return null;
	}
}