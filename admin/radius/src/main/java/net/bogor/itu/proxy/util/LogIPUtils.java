/**
 * 
 */
package net.bogor.itu.proxy.util;

/**
 * @author Dian Aditya
 * 
 */
public class LogIPUtils {
	public static String decimalIPToString(long ip) {
		String binary = Long.toBinaryString(ip);
		StringBuilder ipString = new StringBuilder();

		int index = 0;
		while (index < binary.length()) {
			String b = binary.substring(index,
					Math.min(index + 8, binary.length()));

			ipString.append(Integer.parseInt(b, 2)).append(".");

			index += 8;
		}

		return ipString.substring(0, ipString.length() - 1);
	}
}
