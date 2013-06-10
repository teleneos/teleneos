package org.teleneos.radius.bandwidth;

import java.io.IOException;
import java.net.UnknownHostException;

public interface BandwidthService {

	public void reloadConfiguration();
	
	public void reloadHTBService() throws UnknownHostException, IOException;

}
