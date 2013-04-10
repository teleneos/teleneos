package id.co.bonet.itu.noc;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.meruvian.yama.persistence.DefaultPersistence;

@Entity
@Table(name = "tc_maintenance")
public class Maintenance extends DefaultPersistence {
	private String name;
	private TelecentreComputer telecentre_comp;
	
	@ManyToOne
	@JoinColumn(name = "telecentre_comp_id")
	public TelecentreComputer getTelecentre_comp() {
		return telecentre_comp;
	}

	public void setTelecentre_comp(TelecentreComputer telecentre_comp) {
		this.telecentre_comp = telecentre_comp;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
