/**
 * 
 */
package org.teleneos.servicebus.radius;

import java.util.Calendar;
import java.util.List;

import javax.inject.Inject;

import org.apache.camel.Message;
import org.teleneos.radius.accounting.Radacct;
import org.teleneos.radius.accounting.RadacctService;

/**
 * @author Dian Aditya
 * 
 */
public class AccountingProcessor {
	@Inject
	private RadacctService radacctService;

	public List<Radacct> accounting(Message message) {
		Calendar calendar = Calendar.getInstance();
		long now = calendar.getTimeInMillis();
		calendar.add(Calendar.DAY_OF_MONTH, -1);
		long then = calendar.getTimeInMillis();

		return radacctService.findByRange(then, now);
	}
}
