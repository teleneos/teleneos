/**
 * 
 */
package org.teleneos.servicebus.log;

import java.util.ArrayList;
import java.util.List;

import org.apache.camel.Body;
import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.Processor;
import org.meruvian.yama.persistence.NocDefaultPersistence;
import org.teleneos.log.availability.Ping;
import org.teleneos.log.network.disk.DiskSpace;
import org.teleneos.log.network.disk.DiskSpace.DiskType;
import org.teleneos.log.network.traffic.Traffic;
import org.teleneos.log.network.traffic.Traffic.TrafficType;
import org.teleneos.log.network.traffic.TrafficMessageWrapper;

/**
 * @author Dian Aditya
 * 
 */
public class NetworkProcessor implements Processor {

	public void process(Exchange exchange) throws Exception {
		Message message = exchange.getIn();
		NocDefaultPersistence persistence = message
				.getBody(NocDefaultPersistence.class);
		persistence.setTelecentre(message.getHeader("TelecentreId",
				String.class));

		message.setBody(persistence);
	}

	public List<Traffic> traffic(@Body TrafficMessageWrapper message) {
		List<Traffic> traffics = new ArrayList<Traffic>();

		for (Traffic traffic : message.get(0).getResult()) {
			traffic.setTrafficType(TrafficType.INCOMING);
			traffics.add(traffic);
		}

		for (Traffic traffic : message.get(1).getResult()) {
			traffic.setTrafficType(TrafficType.OUTGOING);
			traffics.add(traffic);
		}

		return traffics;
	}

	public List<DiskSpace> disk(@Body List<DiskSpace> diskSpaces) {
		diskSpaces.get(0).setDiskType(DiskType.FREE_DISK);
		diskSpaces.get(1).setDiskType(DiskType.TOTAL_DISK);
		diskSpaces.get(2).setDiskType(DiskType.FREE_SWAP);
		diskSpaces.get(3).setDiskType(DiskType.TOTAL_SWAP);

		return diskSpaces;
	}

	public Ping ping(Message message) {
		Ping ping = new Ping();
		ping.setTelecentre(message.getHeader("TelecentreId", String.class));

		return ping;
	}
}
