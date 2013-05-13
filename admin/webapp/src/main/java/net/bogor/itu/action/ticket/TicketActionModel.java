package net.bogor.itu.action.ticket;

import org.meruvian.yama.actions.DefaultActionModel;
import org.meruvian.yama.persistence.EntityListWrapper;

import net.bogor.itu.entity.ticket.Ticket;
import net.bogor.itu.entity.ticket.TicketThread;

public class TicketActionModel extends DefaultActionModel {

	private Ticket ticket = new Ticket();
	private EntityListWrapper<Ticket> tickets = new EntityListWrapper<Ticket>();
	
	private TicketThread ticketThread = new TicketThread();
	private EntityListWrapper<TicketThread> ticketThreads = new EntityListWrapper<TicketThread>();
	
	private boolean close = false;

	public Ticket getTicket() {
		return ticket;
	}

	public void setTicket(Ticket ticket) {
		this.ticket = ticket;
	}

	public EntityListWrapper<Ticket> getTickets() {
		return tickets;
	}

	public void setTickets(EntityListWrapper<Ticket> tickets) {
		this.tickets = tickets;
	}

	public TicketThread getTicketThread() {
		return ticketThread;
	}

	public void setTicketThread(TicketThread ticketThread) {
		this.ticketThread = ticketThread;
	}

	public EntityListWrapper<TicketThread> getTicketThreads() {
		return ticketThreads;
	}

	public void setTicketThreads(EntityListWrapper<TicketThread> ticketThreads) {
		this.ticketThreads = ticketThreads;
	}

	public boolean isClose() {
		return close;
	}

	public void setClose(boolean close) {
		this.close = close;
	}

}
