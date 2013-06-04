/**
 * 
 */
package org.teleneos.servicebus.log;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.inject.Inject;

import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.Processor;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.springframework.beans.factory.annotation.Value;

/**
 * @author Dian Aditya
 * 
 */
public class LogProcessor implements Processor {

	@Inject
	private Configuration configuration;
	@Value("${hadoop.path.accesslog}")
	private String logPath;
	private FileSystem fileSystem;

	@PostConstruct
	public void openFileSystem() throws IOException {
		fileSystem = FileSystem.get(configuration);
	}

	@Override
	public void process(Exchange exchange) throws Exception {
		Message message = exchange.getIn();
		byte[] bs = message.getBody(byte[].class);

		Path path = new Path(StringUtils.join(logPath, "/",
				message.getHeader("TelecentreId"), "/",
				message.getHeader(Exchange.FILE_NAME)));

		FSDataOutputStream dos = fileSystem.create(path, true);
		ByteArrayInputStream ais = new ByteArrayInputStream(bs);

		IOUtils.copy(ais, dos);

		IOUtils.closeQuietly(ais);
		IOUtils.closeQuietly(dos);
	}

	@PreDestroy
	public void closeFileSystem() throws IOException {
		fileSystem.close();
	}

}
