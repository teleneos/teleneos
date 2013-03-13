package net.bogor.itu.service.admin;

import javax.inject.Inject;

import net.bogor.itu.entity.admin.User;
import net.bogor.itu.entity.radius.Radcheck;
import net.bogor.itu.repository.admin.UserRepository;
import net.bogor.itu.repository.radius.RadcheckRepository;

import org.apache.commons.lang.StringUtils;
import org.meruvian.yama.persistence.EntityListWrapper;
import org.meruvian.yama.security.user.BackendUser;
import org.meruvian.yama.security.user.BackendUserDAO;
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
	private RadcheckRepository radcheckRepo;

	@Override
	@Transactional
	public User save(User user) {
		if (StringUtils.isBlank(user.getId())) {
			user.setId(null);

			BackendUser backendUser = user.getUser();
			backendUser.setId(null);

			Radcheck radcheck = new Radcheck();
			radcheck.setUsername(backendUser.getUsername());
			radcheck.setAttribute("Cleartext-Password");
			radcheck.setOp(":=");
			radcheck.setValue(backendUser.getPassword());

			user.setRadcheck(radcheck);

			radcheckRepo.persist(radcheck);
			backendUserRepo.persist(backendUser);
			userRepo.persist(user);
		} else {
			BackendUser bu = user.getUser();

			User u = userRepo.findById(user.getId());
			BackendUser b = u.getUser();
			Radcheck r = u.getRadcheck();

			b.setUsername(bu.getUsername());
			b.setEmail(bu.getEmail());
			b.setWebsite(bu.getWebsite());

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