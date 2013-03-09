/**
 * Copyright 2013 Meruvian
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0 
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.meruvian.yama.security.user.service;

import java.util.Date;

import javax.inject.Inject;

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
public class BackendUserServiceImpl implements BackendUserService {

	@Inject
	private BackendUserDAO userDAO;

	@Inject
	private PasswordEncoder encoder;

	@Override
	public BackendUser getById(String id) {
		return userDAO.findById(id);
	}

	@Override
	public BackendUser getByUsername(String username) {
		BackendUser user = userDAO.findByUsername(username);

		return user;
	}

	@Override
	@Transactional
	public BackendUser changePassword(String username, String newpass) {
		BackendUser user = getByUsername(username);
		user.setPassword(encoder.encodePassword(newpass, null));

		return user;
	}

	@Override
	@Transactional
	public void initUser() {
		BackendUser user = new BackendUser();
		user.setUsername("admin");
		user.setPassword(encoder.encodePassword("admin", null));
		user.setRole("ADMINISTRATOR");

		save(user);
	}

	@Override
	public Long count() {
		return userDAO.count();
	}

	@Override
	@Transactional
	public BackendUser save(BackendUser user) {
		if (StringUtils.isBlank(user.getId())) {
			user.setId(null);
			user.setPassword(encoder.encodePassword(user.getPassword(), null));
			userDAO.persist(user);

			return user;
		} else {
			BackendUser temp = userDAO.findById(user.getId());
			temp.getLogInformation().setUpdateBy(user.getId());
			temp.getLogInformation().setUpdateDate(new Date());
			temp.setEmail(user.getEmail());
			temp.setWebsite(user.getWebsite());
			temp.setRole(user.getRole());
			if (!temp.getPassword().equals(user.getPassword())) {
				temp.setPassword(encoder.encodePassword(user.getPassword(),
						null));
			}

			return temp;
		}
	}

	@Override
	public EntityListWrapper<BackendUser> find(String keyword, int limit,
			int page) {
		return userDAO.findByKeyword(keyword, limit, page);
	}
}