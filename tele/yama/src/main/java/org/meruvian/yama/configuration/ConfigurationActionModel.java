package org.meruvian.yama.configuration;

import java.util.ArrayList;
import java.util.List;

import org.meruvian.yama.actions.DefaultActionModel;

public class ConfigurationActionModel extends DefaultActionModel {

	private List<Configuration> configurations = new ArrayList<Configuration>();

	public List<Configuration> getConfigurations() {
		return configurations;
	}

	public void setConfigurations(List<Configuration> configurations) {
		this.configurations = configurations;
	}

}
