package net.bogor.itu.radius;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.inject.Inject;

import net.bogor.itu.entity.admin.User;
import net.bogor.itu.entity.master.InternetPackage.Type;
import net.bogor.itu.entity.radius.Radacct;
import net.bogor.itu.service.admin.UserService;
import net.bogor.itu.service.radius.RadacctService;
import net.jradius.dictionary.Attr_CallingStationId;
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
	
	@Inject
	private RadiusSerivce radiusSerivce;
	
	@Override
	public boolean handle(JRadiusRequest request) throws Exception {
		RadiusPacket req = request.getRequestPacket();
		AttributeList rp = req.getAttributes();
		String username = rp.get(Attr_UserName.TYPE).getValue().getValueObject().toString();
		String timestamp = rp.get(Attr_EventTimestamp.TYPE).getValue().getValueObject().toString();
		System.err.println("Attribute List: " + rp.getAttributeList());
		System.err.println("Username: "+ username);
		User user = userService.findByUsername(username);
		request.setReturnValue(JRadiusServer.RLM_MODULE_UPDATED);
		Radacct radacct = null;
		try{
			radacct = radacctService.findFirstSession(username);
			SimpleDateFormat format = new SimpleDateFormat("EEE MMM dd hh:mm:ss z yyyy");
			System.err.println("Timestamp: "+ format.parse(timestamp).getTime());
			System.err.println("Radacct Start Time: "+ radacct.getAcctstarttime().getTime());
			long firstlogin = radacct.getAcctstarttime().getTime();
			long timestampmilis = format.parse(timestamp).getTime();
			long variablemilis = user.getInternetPackage().getVariable() * 60000;
			long toend = firstlogin + variablemilis;
			System.err.println("Package variable: "+ user.getInternetPackage().getVariable() * 60000);
			System.err.println("Compare "+format.parse(timestamp).compareTo(new Date(toend)));
			if(user.getInternetPackage().getType().equals(Type.COUNTDOWN)){
				if (format.parse(timestamp).compareTo(new Date(toend)) > 0) {
					radiusSerivce.logout(rp.get(Attr_CallingStationId.TYPE).getValue().getValueObject().toString());
				}
			}else if(user.getInternetPackage().getType().equals(Type.FIXTIME)){
				if (format.parse(timestamp).compareTo(new Date(user.getInternetPackage().getVariable())) > 0) {
					radiusSerivce.logout(rp.get(Attr_CallingStationId.TYPE).getValue().getValueObject().toString());
				}
			}
		}catch (IndexOutOfBoundsException e) {
			System.err.println(e.getMessage());
		}
		
		request.setReturnValue(JRadiusServer.RLM_MODULE_UPDATED);
		return false;
	}

}
