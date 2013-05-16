package net.bogor.itu.action.ticket;

import net.bogor.itu.entity.ticket.TicketCategory;

import org.meruvian.yama.actions.DefaultActionModel;
import org.meruvian.yama.persistence.EntityListWrapper;

public class TicketCategoryActionModel extends DefaultActionModel {
	
	private TicketCategory category = new TicketCategory();
	private EntityListWrapper<TicketCategory> categories = new EntityListWrapper<TicketCategory>();

	public TicketCategory getCategory() {
		return category;
	}

	public void setCategory(TicketCategory category) {
		this.category = category;
	}

	public EntityListWrapper<TicketCategory> getCategories() {
		return categories;
	}

	public void setCategories(EntityListWrapper<TicketCategory> categories) {
		this.categories = categories;
	}

}
