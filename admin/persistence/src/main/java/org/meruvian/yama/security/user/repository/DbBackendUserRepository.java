/**
 * 
 */
package org.meruvian.yama.security.user.repository;

import javax.persistence.TypedQuery;

import net.bogor.itu.persistence.PersistenceRepository;

import org.apache.commons.lang.StringUtils;
import org.meruvian.yama.persistence.EntityListWrapper;
import org.meruvian.yama.persistence.LogInformation.StatusFlag;
import org.meruvian.yama.security.user.BackendUser;
import org.springframework.stereotype.Repository;

/**
 * @author Dian Aditya
 * 
 */
@Repository("dbBackendUserRepository")
public class DbBackendUserRepository extends PersistenceRepository<BackendUser>
		implements BackendUserRepository {

	@Override
	public BackendUser findByUsername(String username) {
		TypedQuery<BackendUser> query = createQuery(BackendUser.class, "d",
				"d", "d.logInformation.statusFlag = ? AND d.username = ?",
				StatusFlag.ACTIVE, username);
		try {
			return query.getSingleResult();
		} catch (Exception e) {
			LOG.error("No entity found within key " + username, e);

			return null;
		}
	}

	@Override
	public EntityListWrapper<BackendUser> findByKeyword(String keyword,
			int limit, int page) {
		if (StringUtils.isBlank(keyword))
			return findAll(limit, page);

		return findByKeyword(limit, page, keyword, "username", "email",
				"website");
	}
}
