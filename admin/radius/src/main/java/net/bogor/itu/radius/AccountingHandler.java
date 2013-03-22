package net.bogor.itu.radius;

import net.jradius.handler.PacketHandlerBase;
import net.jradius.server.JRadiusRequest;
import net.jradius.server.JRadiusServer;

import org.springframework.beans.factory.annotation.Value;

/**
 * @author Dian Aditya
 * 
 */
public class AccountingHandler extends PacketHandlerBase {

	@Value("${radius.client.remote_intet_address}")
	private String radiusInetAddress;

	@Value("${radius.client.shared_secret}")
	private String radiusSharedSecret;

	@Value("${radius.server.coa_port}")
	private String radiusCoAPort;

	@Value("${radius.client.disconnect_shell_location}")
	private String disconnectSh;

	@Override
	public boolean handle(JRadiusRequest request) throws Exception {

		request.setReturnValue(JRadiusServer.RLM_MODULE_UPDATED);
		return false;
	}

}
