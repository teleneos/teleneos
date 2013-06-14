/**
 * 
 */
package org.teleneos.servicebus.radius;

import java.util.Calendar;
import java.util.List;

import javax.inject.Inject;

import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.Processor;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.transaction.annotation.Transactional;
import org.teleneos.radius.accounting.Radacct;
import org.teleneos.radius.accounting.RadacctService;

/**
 * @author Dian Aditya
 * 
 */
public class AccountingProcessor implements Processor {
	@Inject
	private RadacctService radacctService;

	public List<Radacct> accounting(Message message) {
		Calendar calendar = Calendar.getInstance();
		long now = calendar.getTimeInMillis();
		calendar.add(Calendar.DAY_OF_MONTH, -1);
		long then = calendar.getTimeInMillis();

		return radacctService.findByRange(then, now);
	}

	@Override
	@Transactional
	public void process(Exchange exchange) throws Exception {
		Message message = exchange.getIn();
		Radacct radacct = message.getBody(Radacct.class);

		int version = ObjectUtils.defaultIfNull(radacct.getSyncVersion(), 0);
		radacct.setSyncVersion(version + 1);
	}
}
