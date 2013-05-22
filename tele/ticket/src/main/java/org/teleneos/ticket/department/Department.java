package org.teleneos.ticket.department;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.meruvian.yama.persistence.DefaultPersistence;

@Entity
@Table(name = "tc_ticket_department")
public class Department extends DefaultPersistence {
	
	private static final long serialVersionUID = -4388902917961691996L;
	
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
