/**
 * 
 */
package org.teleneos.servicebus.squid;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.inject.Inject;

import org.apache.camel.Body;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.teleneos.radius.accounting.Radacct;
import org.teleneos.radius.accounting.RadacctService;

/**
 * @author Dian Aditya
 * 
 */
public class LogUserIPAppender {
	@Inject
	private RadacctService radacctService;

	public File appendUser(@Body String body) throws Exception {
		String[] strings = StringUtils.split(body, '\n');

		long from = findTimeMilis(strings[0]);
		long to = findTimeMilis(strings[strings.length - 1]);

		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(from);
		calendar.add(Calendar.HOUR, -3);
		from = calendar.getTimeInMillis();

		calendar.setTimeInMillis(to);
		calendar.add(Calendar.HOUR, 3);
		to = calendar.getTimeInMillis();

		List<Radacct> accts = radacctService.findByRange(from, to);
		for (Radacct radacct : accts) {
			strings = compareUserLog(strings, radacct);
		}

		return writeToFile(strings);
	}

	private File writeToFile(String[] logs) throws IOException {
		File file = File.createTempFile(UUID.randomUUID().toString(), null);
		FileOutputStream fos = new FileOutputStream(file);

		for (String s : logs) {
			fos.write(s.getBytes());
			fos.write('\n');
		}

		IOUtils.closeQuietly(fos);

		return file;
	}

	private long findTimeMilis(String line) {
		return new Long((StringUtils.split(line)[0]).replace(".", ""));
	}

	private String[] compareUserLog(String[] logs, Radacct radacct) {
		long start = radacct.getAcctstarttime().getTime();
		Date stopDate = radacct.getAcctstoptime();
		long stop = stopDate == null ? System.currentTimeMillis() : stopDate
				.getTime();
		String ip = radacct.getFramedipaddress();
		String user = radacct.getUsername();

		int i = 0;
		for (String line : logs) {
			String[] cols = StringUtils.split(line);
			if (cols[0].contains(".")) {
				long time = new Long(cols[0].replace(".", ""));

				if (time >= start && time <= stop) {
					if (ip.equals(cols[2])) {
						cols[0] = Long.toString(time);
						cols[2] = user;

						logs[i] = StringUtils.join(cols, ' ');
					}
				}
			}

			i++;
		}

		return logs;
	}
}
