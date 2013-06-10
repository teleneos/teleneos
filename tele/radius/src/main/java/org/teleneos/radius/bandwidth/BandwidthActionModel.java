package org.teleneos.radius.bandwidth;

import java.util.HashMap;
import java.util.List;

import org.meruvian.yama.actions.DefaultActionModel;
import org.teleneos.radius.accounting.Radacct;

public class BandwidthActionModel extends DefaultActionModel {

	private HashMap<String, List<Radacct>> map = new HashMap<String, List<Radacct>>();

	public HashMap<String, List<Radacct>> getMap() {
		return map;
	}

	public void setMap(HashMap<String, List<Radacct>> map) {
		this.map = map;
	}

}
