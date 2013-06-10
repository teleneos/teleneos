/**
 * 
 */
package org.teleneos.noc.telecentre;

import org.meruvian.yama.actions.DefaultActionModel;
import org.meruvian.yama.persistence.NocEntityListWrapper;

/**
 * @author Dian Aditya
 * 
 */
public class TelecentreActionModel extends DefaultActionModel {
	private Telecentre telecentre = new Telecentre();
	private NocEntityListWrapper<Telecentre> telecentres = new NocEntityListWrapper<Telecentre>();

	public Telecentre getTelecentre() {
		return telecentre;
	}

	public void setTelecentre(Telecentre telecentre) {
		this.telecentre = telecentre;
	}

	public NocEntityListWrapper<Telecentre> getTelecentres() {
		return telecentres;
	}

	public void setTelecentres(NocEntityListWrapper<Telecentre> telecentres) {
		this.telecentres = telecentres;
	}
}