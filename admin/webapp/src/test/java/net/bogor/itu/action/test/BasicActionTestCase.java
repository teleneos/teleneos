package net.bogor.itu.action.test;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.StrutsSpringJUnit4TestCase;
import org.junit.Before;
import org.junit.Rule;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author Dian Aditya
 * 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath*:applicationContext*.xml" })
public abstract class BasicActionTestCase<T> extends
		StrutsSpringJUnit4TestCase<T> {
	@Rule
	public ExpectedException exception = ExpectedException.none();

	@Before
	@Override
	public void setUp() throws Exception {
		super.setUp();
		ServletActionContext.setServletContext(servletContext);
		ServletActionContext.setRequest(request);
		ServletActionContext.setResponse(response);
	}

}
