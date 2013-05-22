package org.meruvian.yama.security.user;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * @author Dian Aditya
 * 
 */
@Embeddable
public class Name implements Serializable {
	private String first;
	private String middle;
	private String last;
	private String title;

	@Column(name = "first_name", nullable = false)
	public String getFirst() {
		return first;
	}

	public void setFirst(String first) {
		this.first = first;
	}

	@Column(name = "middle_name")
	public String getMiddle() {
		return middle;
	}

	public void setMiddle(String middle) {
		this.middle = middle;
	}

	@Column(name = "last_name")
	public String getLast() {
		return last;
	}

	public void setLast(String last) {
		this.last = last;
	}

	@Column(name = "title_name")
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
}
