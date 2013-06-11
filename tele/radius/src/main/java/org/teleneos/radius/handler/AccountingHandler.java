package org.teleneos.radius.handler;

import java.util.Calendar;
import java.util.Date;

import javax.inject.Inject;

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
import org.teleneos.radius.RadiusSerivce;
import org.teleneos.radius.history.ConnectionHistory;
import org.teleneos.radius.history.ConnectionHistoryService;
import org.teleneos.radius.internetpackage.InternetPackage;
import org.teleneos.radius.internetpackage.PaymentMethod;
import org.teleneos.radius.userpackage.UserPackage;
import org.teleneos.radius.userpackage.UserPackage.Status;
import org.teleneos.radius.userpackage.UserPackageService;

/**
 * @author Dian Aditya
 * 
 */
public class AccountingHandler extends PacketHandlerBase {
	private static final Log LOG = LogFactory.getLog(AccountingHandler.class);

	@Inject
	private RadiusSerivce radiusService;

	@Inject
	private ConnectionHistoryService historyService;

	@Inject
	private UserPackageService packageService;

	@Override
	public boolean handle(JRadiusRequest request) throws Exception {
		try {
			RadiusPacket req = request.getRequestPacket();
			AttributeList rp = req.getAttributes();

			String username = rp.get(Attr_UserName.TYPE).getValue()
					.getValueObject().toString();
			String macAddress = rp.get(Attr_CallingStationId.TYPE).getValue()
					.getValueObject().toString();
			String uniqueSessionId = rp.get(Attr_AcctUniqueSessionId.TYPE)
					.getValue().getValueObject().toString();
			String acctStatusType = req.getAttributeValue("Acct-Status-Type")
					.toString();

			UserPackage userPackage = packageService
					.findActivePackage(username);

			// User doesn't subscribe any active package
			if (userPackage == null) {
				LOG.info("No active package for user:" + username);
				radiusService.logout(macAddress);

				request.setReturnValue(JRadiusServer.RLM_MODULE_UPDATED);
				return false;
			}

			InternetPackage internetPackage = userPackage.getInternetPackage();

			LOG.info("User: " + username);
			LOG.info("Accounting status type: " + acctStatusType);
			LOG.info("User package status: " + userPackage.getStatus());

			// Acct-Status-Type = Start
			if ("1".equalsIgnoreCase(acctStatusType)) {
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

					calendar.add(Calendar.MINUTE,
							(int) (min % Integer.MAX_VALUE));
					long quota = internetPackage.getQuota();
					LOG.info("Activating package: " + internetPackage.getCode()
							+ "(" + internetPackage.getName() + ")");
					LOG.info("Quota: " + quota);

					userPackage.setStatus(Status.ACTIVE);
					userPackage.setEndDate(calendar.getTime());
					userPackage.setQuotaBalance(quota);
					userPackage.setUnlimited(quota < 1);

					packageService.save(userPackage);
				}

				ConnectionHistory connectionHistory = new ConnectionHistory();
				connectionHistory.setRadacct(uniqueSessionId);
				connectionHistory.setUsername(username);
				connectionHistory.setUserPackage(userPackage);

				historyService.save(connectionHistory);
			} else if ("2".equalsIgnoreCase(acctStatusType)) {
				long download = new Long(rp.get(Attr_AcctInputOctets.TYPE)
						.getValue().getValueObject().toString());
				long upload = new Long(rp.get(Attr_AcctOutputOctets.TYPE)
						.getValue().getValueObject().toString());

				userPackage.setQuotaBalance(userPackage.getQuotaBalance()
						- (download + upload));

				packageService.save(userPackage);

				request.setReturnValue(JRadiusServer.RLM_MODULE_UPDATED);
				return false;
			}

			Date now = new Date(System.currentTimeMillis());
			LOG.info("Current time: " + now);
			LOG.info("Package expired at: " + userPackage.getEndDate());

			// Package expired
			if (!userPackage.getInternetPackage().getPaymentMethod().equals(PaymentMethod.POSTPAID) 
					&& now.compareTo(userPackage.getEndDate()) > 0) {
				LOG.info("Expired package: " + internetPackage.getName()
						+ " for user: " + username);

				userPackage.setStatus(Status.END);
				packageService.save(userPackage);

				radiusService.logout(macAddress);

				request.setReturnValue(JRadiusServer.RLM_MODULE_UPDATED);
				return false;
			}

			if ((!userPackage.isUnlimited())
					&& userPackage.getQuotaBalance() < 1
					&& internetPackage.getPaymentMethod().equals(
							PaymentMethod.PREPAID)) {
				LOG.info("End of quota balance: " + username);
				userPackage.setStatus(Status.END);
				packageService.save(userPackage);

				radiusService.logout(macAddress);

				request.setReturnValue(JRadiusServer.RLM_MODULE_UPDATED);
				return false;
			}

			if ("3".equalsIgnoreCase(acctStatusType)) {
				long download = new Long(rp.get(Attr_AcctInputOctets.TYPE)
						.getValue().getValueObject().toString());
				long upload = new Long(rp.get(Attr_AcctOutputOctets.TYPE)
						.getValue().getValueObject().toString());

				LOG.info("Quota Balance: "
						+ (userPackage.getQuotaBalance() / 1024) + "KB");
				LOG.info("Bandwidth usage: " + ((download + upload) / 1024)
						+ "KB");
				LOG.info("Unlimited : " + userPackage.isUnlimited()
						+ ", subscription method: "
						+ internetPackage.getPaymentMethod());

				if ((!userPackage.isUnlimited())
						&& userPackage.getQuotaBalance() < (download + upload)
						&& internetPackage.getPaymentMethod().equals(
								PaymentMethod.PREPAID)
						&& (!"2".equalsIgnoreCase(acctStatusType))) {
					LOG.info("End of quota balance: " + username);

					userPackage.setStatus(Status.END);
					packageService.save(userPackage);

					radiusService.logout(macAddress);
				}
			}
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
		}

		request.setReturnValue(JRadiusServer.RLM_MODULE_UPDATED);
		return false;
	}
}