package id.co.bonet.itu.noc.action;

import id.co.bonet.itu.noc.Telecentre;

import org.meruvian.yama.actions.DefaultActionModel;
import org.meruvian.yama.persistence.EntityListWrapper;

public class TelecentreActionModel extends DefaultActionModel {
	private Telecentre telecentre = new Telecentre();
	private EntityListWrapper<Telecentre> telecentres = new EntityListWrapper<Telecentre>();
	
	public Telecentre getTelecentre() {
		return telecentre;
	}
	public void setTelecentre(Telecentre telecentre) {
		this.telecentre = telecentre;
	}
	public EntityListWrapper<Telecentre> getTelecentres() {
		return telecentres;
	}
	public void setTelecentres(EntityListWrapper<Telecentre> telecentres) {
		this.telecentres = telecentres;
	}
}
