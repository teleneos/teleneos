/**
 * 
 */
package org.meruvian.yama.persistence;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

/**
 * @author Dian Aditya
 * 
 */
@MappedSuperclass
public class NocDefaultPersistence extends DefaultPersistence {
	private String telecentre;

	@Column(name = "tele_id")
	public String getTelecentre() {
		return telecentre;
	}

	public void setTelecentre(String telecentre) {
		this.telecentre = telecentre;
	}

}
