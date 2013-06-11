package org.meruvian.yama.configuration;

import java.util.ArrayList;
import java.util.List;

import org.meruvian.yama.actions.DefaultActionModel;
import org.teleneos.noc.telecentre.Telecentre;

public class ConfigurationActionModel extends DefaultActionModel {

	private List<Configuration> configurations = new ArrayList<Configuration>();
	private Telecentre telecentre = new Telecentre();

	public List<Configuration> getConfigurations() {
		return configurations;
	}

	public void setConfigurations(List<Configuration> configurations) {
		this.configurations = configurations;
	}

	public Telecentre getTelecentre() {
		return telecentre;
	}

	public void setTelecentre(Telecentre telecentre) {
		this.telecentre = telecentre;
	}

}
