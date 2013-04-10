package id.co.bonet.itu.persistence;

import java.util.Date;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import org.apache.struts2.ServletActionContext;
import org.meruvian.yama.persistence.DefaultPersistence;
import org.meruvian.yama.persistence.LogInformation;
import org.meruvian.yama.persistence.LogInformation.StatusFlag;
import org.meruvian.yama.security.SessionCredentials;
import org.meruvian.yama.security.user.BackendUser;

/**
 * @author Dian Aditya
 * 
 */

public class LogInformationListener {
	@PrePersist
	public void onPrePersist(Object entity) {
		if (entity instanceof DefaultPersistence) {
			String userId = null;

			if (ServletActionContext.getContext() != null) {
				BackendUser user = null;
				user = SessionCredentials.currentUser();

				userId = user == null ? null : user.getId();
			}

			DefaultPersistence e = (DefaultPersistence) entity;

			if (e.getLogInformation() == null)
				e.setLogInformation(new LogInformation());

			if (e.getLogInformation().getCreateBy() == null) {
				e.getLogInformation().setCreateBy(userId);
			}

			e.getLogInformation().setUpdateBy(userId);
			e.getLogInformation().setStatusFlag(StatusFlag.ACTIVE);
		}
	}

	@PreUpdate
	public void onPreUpdate(Object entity) {
		String userId = null;

		if (ServletActionContext.getContext() != null) {
			BackendUser user = null;
			user = SessionCredentials.currentUser();

			userId = user == null ? null : user.getId();
		}

		if (entity instanceof DefaultPersistence) {
			DefaultPersistence e = (DefaultPersistence) entity;
			if (ServletActionContext.getContext() != null)
				e.getLogInformation().setUpdateBy(userId);
			e.getLogInformation().setUpdateDate(new Date());
		}
	}
}
