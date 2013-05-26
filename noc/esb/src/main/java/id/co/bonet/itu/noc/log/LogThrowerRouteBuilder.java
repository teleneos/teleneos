package id.co.bonet.itu.noc.log;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.spring.SpringRouteBuilder;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class LogThrowerRouteBuilder extends SpringRouteBuilder {
	private static final Log LOG = LogFactory
			.getLog(LogThrowerRouteBuilder.class);
	
	@Override
	public void configure() throws Exception {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		from("file:/home/black-meruvian/workspace/file/inbox/zabbix?noop=true")
		.setHeader("appName", constant("zabbix"))
		.filter(body().isNotNull())
//		.to("activemq:queue:zabbix.log");
//		.to("amqp:queue:Log");
		.to("spring-amqp:clientExchange:clientQueue:zabbix.log");
	
	from("file:/home/black-meruvian/workspace/file/inbox/squid?noop=true")
		.setHeader("appName", constant("squid"))
		.filter(body().isNotNull())
//		.to("activemq:queue:zabbix.log");
//		.to("amqp:queue:Log");
		.to("spring-amqp:clientExchange:clientQueue:zabbix.log");

	from("spring-amqp:clientExchange:clientQueue:zabbix.log")
//		.to("mina:tcp://10.1.0.26:9001");
		.to("http://localhost:8081/log");
	
//	from("cxfrs://http://localhost:8081?resourceClasses="+LogService.class.getName())
//	.process(new Processor() {
//			@Override
//			public void process(Exchange exchange) throws Exception {
//				log.info(exchange.getIn().getBody(String.class));
//			}
//	})
//	.setBody(constant("SUCCESS"));
	
//	from("mina:tcp://localhost:9001")
//		.to("spring-amqp:serverExchange:serverQueue:zabbix.log");
//
//	from("spring-amqp:serverExchange:serverQueue:zabbix.log")
////	from("amqp:queue:Log")
////	from("activemq:queue:zabbix.log")
//	// .to("hdfs://192.168.2.25:54310/user/black-meruvian?fileSystemType=HDFS&splitStrategy=BYTES:5000,IDLE:1000");
//		.setHeader("file", constant("zabbix_server.log"))
//		.process(new Processor() {
//
//				@Override
//				public void process(Exchange exchange) throws Exception {
//					exchange.getIn().setHeader("file", exchange.getIn().getHeader(Exchange.FILE_NAME));
//				}
//		})
//		.routingSlip(header("file")
//				.prepend("/" + dateFormat.format(new Date()) + "/")
//				.prepend(header("appName"))
////				.prepend("hdfs://192.168.2.15:54310/itu/tc1/")
//				.prepend("hdfs://10.1.0.29:54310/itu/tc1/")
//				.append("?fileSystemType=HDFS")).end();

	}

}
