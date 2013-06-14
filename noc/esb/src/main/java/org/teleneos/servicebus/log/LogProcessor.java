/**
 * 
 */
package org.teleneos.servicebus.log;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.UUID;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.inject.Inject;

import org.apache.camel.Body;
import org.apache.camel.Exchange;
import org.apache.camel.Header;
import org.apache.camel.Message;
import org.apache.camel.Processor;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.solr.common.SolrInputDocument;
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

	public SolrInputDocument index(@Body String body,
			@Header("TelecentreId") String tele) {
		String[] split = StringUtils.split(body);
		if (split[0].contains(".")) {
			return null;
		}

		SolrInputDocument document = new SolrInputDocument();
		document.addField("id", UUID.randomUUID().toString());
		document.addField("time", Long.parseLong(split[0]));
		document.addField("elapsed", Integer.parseInt(split[1]));
		document.addField("user", split[2]);
		document.addField("status", split[3]);
		document.addField("bytes", Integer.parseInt(split[4]));
		document.addField("method", split[5]);
		document.addField("url", split[6]);
		document.addField("rfc93", split[7]);
		document.addField("peerhost", split[8]);
		document.addField("type", split[9]);
		document.addField("tele", tele);

		return document;
	}
}
