package net.bogor.itu.radius;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.inject.Inject;

import net.bogor.itu.entity.admin.User;
import net.bogor.itu.entity.master.InternetPackage.Type;
import net.bogor.itu.entity.radius.ConnectionHistory;
import net.bogor.itu.entity.radius.Radacct;
import net.bogor.itu.service.admin.UserService;
import net.bogor.itu.service.radius.ConnectionHistoryService;
import net.bogor.itu.service.radius.RadacctService;
import net.jradius.dictionary.Attr_AcctUniqueSessionId;
import net.jradius.dictionary.Attr_CallingStationId;
import net.jradius.dictionary.Attr_EventTimestamp;
import net.jradius.dictionary.Attr_UserName;
import net.jradius.handler.PacketHandlerBase;
import net.jradius.packet.RadiusPacket;
import net.jradius.packet.attribute.AttributeList;
import net.jradius.server.JRadiusRequest;
import net.jradius.server.JRadiusServer;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @author Dian Aditya
 * 
 */
public class AccountingHandler extends PacketHandlerBase {
	private static final Log LOG = LogFactory.getLog(AccountingHandler.class);

	@Inject
	private UserService userService;

	@Inject
	private RadacctService radacctService;

	@Inject
	private RadiusSerivce radiusService;

	@Inject 
	private ConnectionHistoryService historyService;
	
	@Override
	public boolean handle(JRadiusRequest request) throws Exception {
		RadiusPacket req = request.getRequestPacket();
		AttributeList rp = req.getAttributes();
		String username = rp.get(Attr_UserName.TYPE).getValue()
				.getValueObject().toString();
		String timestamp = rp.get(Attr_EventTimestamp.TYPE).getValue()
				.getValueObject().toString();
		User user = userService.findByUsername(username);
		System.err.println("--start persisting history--");
		if ("1".equalsIgnoreCase(req.getAttributeValue("Acct-Status-Type").toString())) {
			ConnectionHistory connectionHistory = new ConnectionHistory();
			/**			
	 			Radacct radacct = new Radacct();
				radacct.setRadacctid(1L);
				radacct.setAcctuniqueid(rp.get(Attr_AcctUniqueSessionId.TYPE)
					.getValue().getValueObject().toString()); 
 			*/
			connectionHistory.setRadacct(rp.get(Attr_AcctUniqueSessionId.TYPE)
					.getValue().getValueObject().toString());
			connectionHistory.setUser(user);
			historyService.save(connectionHistory);
			System.err.println("--done persisting history--");
		}
		
		LOG.info("Attribute List: " + rp.getAttributeList());
		Radacct radacct = null;
		try {
			radacct = radacctService.findFirstSession(username);
			SimpleDateFormat format = new SimpleDateFormat(
					"EEE MMM dd hh:mm:ss z yyyy");
			LOG.info("Timestamp: " + format.parse(timestamp).getTime());
			LOG.info("Radacct Start Time: "
					+ radacct.getAcctstarttime().getTime());
			long firstlogin = radacct.getAcctstarttime().getTime();
			long variablemilis = user.getInternetPackage().getTime() * 60000;
			long toend = firstlogin + variablemilis;
			LOG.info("Package variable: "
					+ user.getInternetPackage().getTime() * 60000);
			if (!radacctService.checkIsOnline(username)) {
				if (user.getInternetPackage().getType().equals(Type.COUNTDOWN)) {
					if (format.parse(timestamp).compareTo(new Date(toend)) > 0) {
						radiusService.logout(rp.get(Attr_CallingStationId.TYPE)
								.getValue().getValueObject().toString());
					}
				} 
				/**
				 else if (user.getInternetPackage().getType()
						.equals(Type.FIXTIME)) {
					if (format.parse(timestamp).compareTo(
							new Date(user.getInternetPackage().getVariable())) > 0) {
						radiusSerivce.logout(rp.get(Attr_CallingStationId.TYPE)
								.getValue().getValueObject().toString());
					}
				}
				*/
			} else {
				radiusService.logout(rp.get(Attr_CallingStationId.TYPE)
						.getValue().getValueObject().toString());
			}
		} catch (IndexOutOfBoundsException e) {
			LOG.error(e.getMessage());
		}

		request.setReturnValue(JRadiusServer.RLM_MODULE_UPDATED);
		return false;
	}

}
