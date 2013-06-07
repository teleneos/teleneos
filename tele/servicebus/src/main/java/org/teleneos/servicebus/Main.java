/**
 * 
 */
package org.teleneos.servicebus;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author Dian Aditya
 * 
 */
public class Main {
	public static final Logger LOG = LoggerFactory.getLogger(Main.class);

	public static void main(String[] args) {
		LOG.info("Starting application");

		ApplicationContext applicationContext = new ClassPathXmlApplicationContext(
				"applicationContext.xml");

		LOG.info("Application started {}",
				new Date(applicationContext.getStartupDate()));
	}
}
