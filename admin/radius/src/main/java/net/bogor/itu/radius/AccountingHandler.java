package net.bogor.itu.radius;

import java.io.DataOutputStream;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.inject.Inject;

import net.bogor.itu.entity.admin.User;
import net.bogor.itu.entity.radius.Radacct;
import net.bogor.itu.service.admin.UserService;
import net.bogor.itu.service.radius.RadacctService;
import net.jradius.dictionary.Attr_EventTimestamp;
import net.jradius.dictionary.Attr_UserName;
import net.jradius.handler.PacketHandlerBase;
import net.jradius.packet.RadiusPacket;
import net.jradius.packet.attribute.AttributeList;
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
	
	@Inject
	private UserService userService;
	
	@Inject
	private RadacctService radacctService;
	
	@Override
	public boolean handle(JRadiusRequest request) throws Exception {
		RadiusPacket req = request.getRequestPacket();
		AttributeList rp = req.getAttributes();
		String username = rp.get(Attr_UserName.TYPE).getValue().getValueObject().toString();
		String timestamp = rp.get(Attr_EventTimestamp.TYPE).getValue().getValueObject().toString();
		System.err.println("Attribute List: " + rp.getAttributeList());
		System.err.println("Username: "+ username);
		User user = userService.findByUsername(username);
		Radacct radacct = radacctService.findFirstSession(username);
		SimpleDateFormat format = new SimpleDateFormat("EEE MMM dd hh:mm:ss z yyyy");
		
		System.err.println("Timestamp: "+ format.parse(timestamp).getTime());
		System.err.println("Radacct Start Time: "+ radacct.getAcctstarttime().getTime());
		long firstlogin = radacct.getAcctstarttime().getTime();
		long timestampmilis = format.parse(timestamp).getTime();
		long variablemilis = user.getInternetPackage().getVariable() * 60000;
		long toend = firstlogin + variablemilis;
		System.err.println("Package variable: "+ user.getInternetPackage().getVariable() * 60000);
		System.err.println("Compare "+format.parse(timestamp).compareTo(new Date(toend)));
		if (format.parse(timestamp).compareTo(new Date(toend)) > 0) {
			Socket clientSocket = new Socket("192.168.2.3", 6789);
			DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
			outToServer.writeBytes(rp.get(Attr_UserName.TYPE).getValue().getValueObject().toString() + '\n');
			clientSocket.close();
		}
		request.setReturnValue(JRadiusServer.RLM_MODULE_UPDATED);
		return false;
	}

}
