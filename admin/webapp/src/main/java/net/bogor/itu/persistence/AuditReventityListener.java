package net.bogor.itu.persistence;

import org.hibernate.envers.RevisionListener;
import org.meruvian.yama.security.SessionCredentials;

public class AuditReventityListener implements RevisionListener {

	@Override
	public void newRevision(Object revEntity) {
		AuditRevEntity auditRevEntity = (AuditRevEntity) revEntity;
		auditRevEntity.setUsername(SessionCredentials.currentUser().getUsername());
	}

}
