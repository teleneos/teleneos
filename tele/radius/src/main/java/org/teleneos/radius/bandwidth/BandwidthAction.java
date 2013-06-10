package org.teleneos.radius.bandwidth;

import javax.inject.Inject;

import org.meruvian.inca.struts2.rest.annotation.Action;
import org.meruvian.yama.actions.DefaultAction;

import com.opensymphony.xwork2.ModelDriven;

@Action(name = "/cfg")
public class BandwidthAction extends DefaultAction implements
		ModelDriven<BandwidthActionModel> {

	private BandwidthActionModel model = new BandwidthActionModel();

	private static final long serialVersionUID = -5731214301544432453L;

	@Inject
	private BandwidthService bandwidthService;

	@Action
	public String packageList() {
		bandwidthService.reloadConfiguration();
		return DefaultAction.INDEX;
	}

	@Override
	public BandwidthActionModel getModel() {
		return model;
	}

}
