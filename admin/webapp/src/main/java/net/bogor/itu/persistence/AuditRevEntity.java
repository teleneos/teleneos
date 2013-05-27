package net.bogor.itu.persistence;

import javax.persistence.Entity;

import org.hibernate.envers.DefaultRevisionEntity;
import org.hibernate.envers.RevisionEntity;

@Entity(name = "REVISION")
@RevisionEntity(AuditReventityListener.class)
public class AuditRevEntity extends DefaultRevisionEntity {

	private static final long serialVersionUID = 8110968696164493089L;

	private String username;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

}
