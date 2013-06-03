package org.teleneos.ticket.answer;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.meruvian.yama.persistence.DefaultPersistence;

@Entity
@Table(name = "tc_ticket_premade_answer")
public class PremadeAnswer extends DefaultPersistence {

	private static final long serialVersionUID = -7390792379426662167L;

	private String title;
	private String content;
	private boolean faq = false;
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public boolean isFaq() {
		return faq;
	}

	public void setFaq(boolean faq) {
		this.faq = faq;
	}
	
}
