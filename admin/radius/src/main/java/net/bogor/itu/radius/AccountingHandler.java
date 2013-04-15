package net.bogor.itu.radius;

import java.util.Calendar;
import java.util.Date;

import javax.inject.Inject;

import net.bogor.itu.entity.admin.User;
import net.bogor.itu.entity.master.InternetPackage;
import net.bogor.itu.entity.master.PaymentMethod;
import net.bogor.itu.entity.radius.ConnectionHistory;
import net.bogor.itu.entity.radius.UserPackage;
import net.bogor.itu.entity.radius.UserPackage.Status;
import net.bogor.itu.service.admin.UserService;
import net.bogor.itu.service.radius.ConnectionHistoryService;
import net.bogor.itu.service.radius.UserPackageService;
import net.jradius.dictionary.Attr_AcctInputOctets;
import net.jradius.dictionary.Attr_AcctOutputOctets;
import net.jradius.dictionary.Attr_AcctUniqueSessionId;
import net.jradius.dictionary.Attr_CallingStationId;
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
	private RadiusSerivce radiusService;

	@Inject
	private ConnectionHistoryService historyService;

	@Inject
	private UserPackageService packageService;

	// private static final SimpleDateFormat radiusDateFormat = new
	// SimpleDateFormat(
	// "EEE MMM dd hh:mm:ss z yyyy");

	@Override
	public boolean handle(JRadiusRequest request) throws Exception {
		RadiusPacket req = request.getRequestPacket();
		AttributeList rp = req.getAttributes();

		String username = rp.get(Attr_UserName.TYPE).getValue()
				.getValueObject().toString();
		// String timestamp = rp.get(Attr_EventTimestamp.TYPE).getValue()
		// .getValueObject().toString();
		String macAddress = rp.get(Attr_CallingStationId.TYPE).getValue()
				.getValueObject().toString();
		String uniqueSessionId = rp.get(Attr_AcctUniqueSessionId.TYPE)
				.getValue().getValueObject().toString();

		String acctStatusType = req.getAttributeValue("Acct-Status-Type")
				.toString();

		User user = userService.findByUsername(username);
		UserPackage userPackage = packageService
				.findActivePackage(user.getId());

		if (userPackage == null) {
			radiusService.logout(macAddress);

			request.setReturnValue(JRadiusServer.RLM_MODULE_UPDATED);
			return false;
		}

		InternetPackage internetPackage = userPackage.getInternetPackage();

		LOG.info("Persisting history for user: " + username);

		// Acct-Status-Type = Start
		if ("1".equalsIgnoreCase(acctStatusType)) {
			// User doesn't subscribe any active package

			// Set status active, add end date
			if (userPackage.getStatus().equals(Status.NOT_ACTIVATED_YET)) {
				long min = userPackage.getInternetPackage().getTime();
				int p = (int) (min / Integer.MAX_VALUE);

				Calendar calendar = Calendar.getInstance();
				calendar.setTime(userPackage.getLogInformation()
						.getCreateDate());

				for (int i = 0; i < p; i++) {
					calendar.add(Calendar.MINUTE, Integer.MAX_VALUE);
				}

				calendar.add(Calendar.MINUTE, (int) (min % Integer.MAX_VALUE));
				long quota = internetPackage.getQuota();

				userPackage.setStatus(Status.ACTIVE);
				userPackage.setEndDate(calendar.getTime());
				userPackage.setQuotaBalance(quota);
				userPackage.setUnlimited(quota < 1);

				packageService.save(userPackage);
			}

			ConnectionHistory connectionHistory = new ConnectionHistory();
			connectionHistory.setRadacct(uniqueSessionId);
			connectionHistory.setUser(user);
			connectionHistory.setUserPackage(userPackage);

			historyService.save(connectionHistory);

			LOG.info("Done persisting history for user: " + username);
		}

		Date now = new Date(System.currentTimeMillis());

		// Package expired
		if (now.compareTo(userPackage.getEndDate()) > 0) {
			userPackage.setStatus(Status.END);
			packageService.save(userPackage);

			radiusService.logout(macAddress);

			request.setReturnValue(JRadiusServer.RLM_MODULE_UPDATED);
			return false;
		}

		long download = new Long(rp.get(Attr_AcctInputOctets.TYPE).getValue()
				.getValueObject().toString());
		long upload = new Long(rp.get(Attr_AcctOutputOctets.TYPE).getValue()
				.getValueObject().toString());

		userPackage.setQuotaBalance(userPackage.getQuotaBalance()
				- (download + upload));

		if (!userPackage.isUnlimited()
				&& userPackage.getQuotaBalance() < 1
				&& internetPackage.getPaymentMethod().equals(
						PaymentMethod.PREPAID)) {
			userPackage.setStatus(Status.END);

			radiusService.logout(macAddress);
		}

		packageService.save(userPackage);

		// LOG.info("Attribute List: " + rp.getAttributeList());

		// Radacct radacct = radacctService.findByUniq(uniqueSessionId);

		// try {
		// radacct = radacctService.findFirstSession(username);
		//
		// LOG.info("Timestamp: "
		// + radiusDateFormat.parse(timestamp).getTime());
		// LOG.info("Radacct Start Time: "
		// + radacct.getAcctstarttime().getTime());
		// long firstlogin = radacct.getAcctstarttime().getTime();
		// long variablemilis = user.getInternetPackage().getTime() * 60000;
		// long toend = firstlogin + variablemilis;
		// LOG.info("Package variable: "
		// + user.getInternetPackage().getTime() * 60000);
		// if (!radacctService.checkIsOnline(username)) {
		// if
		// (user.getInternetPackage().getType().equals(Type.COUNTDOWN))
		// {
		// if (format.parse(timestamp).compareTo(new Date(toend)) > 0) {
		// radiusService.logout(rp.get(Attr_CallingStationId.TYPE)
		// .getValue().getValueObject().toString());
		// }
		// }
		// } else {
		// radiusService.logout(rp.get(Attr_CallingStationId.TYPE)
		// .getValue().getValueObject().toString());
		// }
		// } catch (IndexOutOfBoundsException e) {
		// LOG.error(e.getMessage());
		// }

		request.setReturnValue(JRadiusServer.RLM_MODULE_UPDATED);
		return false;
	}
}