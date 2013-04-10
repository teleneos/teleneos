/**
 * 
 */
package id.co.bonet.itu.noc.log;

import id.co.bonet.itu.noc.log.entity.JsonRPC;
import id.co.bonet.itu.noc.log.entity.UserLoginResponse;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.apache.camel.spring.SpringRouteBuilder;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.codehaus.jackson.JsonGenerator.Feature;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;

/**
 * @author Dian Aditya
 * 
 */
public class ZabbixLogRouteBuilder extends SpringRouteBuilder {
	private static final Log LOG = LogFactory.getLog(ZabbixLogRouteBuilder.class);

	private JsonRPC rpc;
	private ObjectMapper mapper;

	@Value("${zabbix.rest.endpoint.uri}")
	private String zabbixUri;

	@Value("${zabbix.rest.username}")
	private String zabbixUser;

	@Value("${zabbix.rest.password}")
	private String zabbixPassword;

	@Value("${tc.sync.period}")
	private String syncPeriod;

	@Value("${noc.server.uri}")
	private String nocUri;

	@Override
	public void configure() throws Exception {
		rpc = new JsonRPC();
		rpc.setId("1");
		rpc.setMethod("user.login");
		rpc.getParams().put("user", zabbixUser);
		rpc.getParams().put("password", zabbixPassword);

		mapper = new ObjectMapper();
		mapper.configure(Feature.QUOTE_FIELD_NAMES, true);

		// Generate new token every xx minutes
		from("timer://timer1?fixedRate=true&delay=0&period=1s&repeatCount=1")
				.to("direct:auth");
		from("timer://timer2?fixedRate=true&delay=0&period=" + syncPeriod).to(
				"direct:auth");

		from("direct:auth").setHeader(Exchange.HTTP_METHOD, constant("POST"))
				.setHeader(Exchange.CONTENT_TYPE, constant("application/json"))
				.setBody(simple(mapper.writeValueAsString(rpc))).to(zabbixUri)
				.unmarshal().json(JsonLibrary.Jackson, UserLoginResponse.class)
				.process(new Processor() {
					@Override
					public void process(Exchange exchange) throws Exception {
						UserLoginResponse response = exchange.getIn().getBody(
								UserLoginResponse.class);

						rpc = new JsonRPC();
						rpc.setId("2");
						rpc.setMethod("host.get");
						rpc.setAuth(response.getResult());
						rpc.getParams().put("output", "extend");
						exchange.getOut().setBody(
								mapper.writeValueAsString(rpc));

						LOG.info("Login success, token : " + rpc.getAuth());
					}
				}).to("direct:host");

		// from("timer://timer3?fixedRate=true&delay=10000&period=5s")
		from("direct:host").setHeader(Exchange.HTTP_METHOD, constant("POST"))
				.setHeader(Exchange.CONTENT_TYPE, constant("application/json"))
				.to(zabbixUri).convertBodyTo(String.class)
				.process(new Processor() {

					@Override
					public void process(Exchange exchange) throws Exception {
						exchange.getOut().setBody(exchange.getIn().getBody());

						LOG.info("Sending data to NOC server: " + nocUri);
					}
				}).to("direct:noc");

		from("direct:noc")
				.setHeader(Exchange.HTTP_METHOD, constant("POST"))
				.setHeader(Exchange.CONTENT_TYPE, constant("application/json"))
				/* .setHeader(Exchange.HTTP_URI, constant(nocUri)) */.to(nocUri);
	}
}
