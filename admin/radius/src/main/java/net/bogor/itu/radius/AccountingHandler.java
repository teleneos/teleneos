package net.bogor.itu.radius;

import javax.swing.JOptionPane;

import net.jradius.client.RadClient;
import net.jradius.handler.PacketHandlerBase;
import net.jradius.packet.RadiusPacket;
import net.jradius.packet.attribute.AttributeList;
import net.jradius.server.JRadiusRequest;
import net.jradius.server.JRadiusServer;
import net.jradius.session.JRadiusSession;

/**
 * @author Dian Aditya
 * 
 */
public class AccountingHandler extends PacketHandlerBase {

	@Override
	public boolean handle(JRadiusRequest request) throws Exception {
		try {

			/*
			 * Gather some information about the JRadius request
			 */
			AttributeList ci = request.getConfigItems();
			RadiusPacket req = request.getRequestPacket();
			RadiusPacket rep = request.getReplyPacket();

			AttributeList rp = req.getAttributes();
			JRadiusSession session = request.getSession();

			// JOptionPane.showMessageDialog(null, req.getClass().getName() +
			// "\n"
			// + rep.getClass().getName());

			// JOptionPane.showMessageDialog(null, request.getType());

			// RadClient

			switch (request.getType()) {
			case JRadiusServer.JRADIUS_authorize:
				JOptionPane.showMessageDialog(null, "Reject");

				// request.setReturnValue(JRadiusServer.RLM_MODULE_REJECT);
				// return false;
			default:
				break;
			}

			//
			// /*
			// * Find the username in the request packet
			// */
			// String username = (String) req
			// .getAttributeValue(Attr_UserName.TYPE);
			//
			// if (rep instanceof AccessAccept) {
			// RadiusLog.info("Allowing WPA access for username: " + username);
			// System.out.println("Allowing WPA access for username: "
			// + username);
			// System.out.println(request.getSession().getRedirectURL());
			// } else {
			// if ("allow-wpa-guests".equals((String) req
			// .getAttributeValue(Attr_ChilliSpotConfig.TYPE))) {
			// if (req.findAttribute(Attr_EAPMessage.TYPE) != null) {
			// if (req.findAttribute(Attr_FreeRADIUSProxiedTo.TYPE) != null) {
			// rep = new AccessAccept();
			// rep.addAttribute(new Attr_ChilliSpotConfig(
			// "require-uam-auth"));
			// request.setReplyPacket(rep);
			//
			// ci.add(new Attr_AuthType("Accept"));
			// request.setReturnValue(JRadiusServer.RLM_MODULE_UPDATED);
			//
			// RadiusLog
			// .error("Allowing Guest WPA access for username: "
			// + username);
			// return true;
			// }
			// }
			// }
			// RadiusLog.info("Authentication failed for username: "
			// + username);
			// System.out.println("Authentication failed for username: "
			// + username);
			// }
		} catch (Exception e) {
			e.printStackTrace();
		}

		request.setReturnValue(JRadiusServer.RLM_MODULE_UPDATED);
		return false;
	}

}
