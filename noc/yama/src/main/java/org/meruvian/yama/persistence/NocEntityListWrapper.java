/**
 * 
 */
package org.meruvian.yama.persistence;

import org.apache.commons.lang3.ArrayUtils;

/**
 * @author Dian Aditya
 * 
 */
public class NocEntityListWrapper<T> extends EntityListWrapper<T> {
	private byte[] cookie;

	public byte[] getCookie() {
		return ArrayUtils.clone(cookie);
	}

	public void setCookie(byte[] cookie) {
		this.cookie = ArrayUtils.clone(cookie);
	}
}
