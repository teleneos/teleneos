/**
 * 
 */
package org.teleneos.noc.monitoring;

import java.util.Map;

import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;
import org.teleneos.noc.telecentre.TelecentreService;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import com.opensymphony.xwork2.util.ValueStack;

/**
 * @author Dian Aditya
 * 
 */
public class MonitoringInterceptor extends AbstractInterceptor {

	public static final String CURRENT_TELE = "currentTele";

	@Inject
	private TelecentreService teleService;

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		ActionContext context = invocation.getInvocationContext();
		Map<String, Object> session = context.getSession();
		ValueStack stack = context.getValueStack();

		String currentTele = stack.findString("telecentre");
		if (!StringUtils.isBlank(currentTele)) {
			session.put(CURRENT_TELE, currentTele);
		}

		stack.set("telecentres", teleService.findAll());

		return invocation.invoke();
	}
}
