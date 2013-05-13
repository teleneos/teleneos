package net.bogor.itu.entity.ticket;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.meruvian.yama.persistence.DefaultPersistence;

@Entity
@Table(name = "tc_ticket_thread")
public class TicketThread extends DefaultPersistence {
	
	private static final long serialVersionUID = 4641118012589110389L;
	
	private Ticket ticket;
	private String message;

	@ManyToOne
	@JoinColumn(name = "ticket_id")
	public Ticket getTicket() {
		return ticket;
	}

	public void setTicket(Ticket ticket) {
		this.ticket = ticket;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
