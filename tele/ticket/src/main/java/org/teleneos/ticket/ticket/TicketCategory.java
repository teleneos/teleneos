package org.teleneos.ticket.ticket;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.meruvian.yama.persistence.DefaultPersistence;

@Entity
@Table(name = "tc_ticket_category")
public class TicketCategory extends DefaultPersistence {

	private static final long serialVersionUID = -2558776540679180886L;

	private String name;
	private String description;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
