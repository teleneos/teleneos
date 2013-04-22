/**
 * Copyright 2012 BlueOxygen Technology
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
package org.meruvian.yama.security.user;

import javax.persistence.TypedQuery;

import net.bogor.itu.persistence.PersistenceRepository;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.meruvian.yama.persistence.EntityListWrapper;
import org.meruvian.yama.persistence.LogInformation.StatusFlag;
import org.springframework.stereotype.Repository;

/**
 * @author Dian Aditya
 * 
 */
@Repository
public class BackendUserDAO extends PersistenceRepository<BackendUser> {
	private static final Log LOG = LogFactory.getLog(BackendUserDAO.class);

	public BackendUser findByUsername(String username) {
		TypedQuery<BackendUser> query = createQuery(BackendUser.class, "d",
				"d", "d.logInformation.statusFlag = ? AND d.username = ?",
				StatusFlag.ACTIVE, username);
		try {
			return query.getSingleResult();
		} catch (Exception e) {
			LOG.error("No entity found within key: " + username);

			return null;
		}
	}

	public EntityListWrapper<BackendUser> findByKeyword(String keyword,
			int limit, int page) {
		if (StringUtils.isBlank(keyword))
			return findAll(limit, page);

		return findByKeyword(limit, page, keyword, "username", "email",
				"website");
	}
}
