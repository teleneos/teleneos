/**
 * Copyright 2012 BlueOxygen Technology
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0 
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.meruvian.inca.struts2.rest.discoverer;

import org.meruvian.inca.struts2.rest.commons.RestConstants;

import com.opensymphony.xwork2.inject.Inject;
import com.opensymphony.xwork2.util.finder.Test;

/**
 * @author Dian Aditya
 * 
 */
public class AnnotationClasspathFilter implements Test<String> {
	private String[] actionPackages = { "action", "actions", "controller",
			"controllers" };

	@Inject
	public AnnotationClasspathFilter(
			@Inject(RestConstants.INCA_PACKAGE_LOCATORS) String packageLocator) {
		String[] packageLocators = packageLocator.split("\\s*[,]\\s*");

		if (packageLocators.length > 0) actionPackages = packageLocators;
	}

	@Override
	public boolean test(String filename) {
		if (filename.endsWith(".class")) {
			if (filename.startsWith("/")) {
				filename = filename.substring(1);
			}
			if (match(filename.replace('/', '.').replace(".class", ""))) { return true; }
		}
		return false;
	}

	/**
	 * @param replace
	 * @return
	 */
	private boolean match(String replace) {
		for (String include : actionPackages) {
			if (replace.startsWith(include + ".")) return true;
			if(replace.contains(".")){
				if (replace.substring(0, replace.lastIndexOf('.'))
						.endsWith(include)) return true;
				if (replace.contains(include + ".")) return true;
			}
		}

		return false;
	}
}