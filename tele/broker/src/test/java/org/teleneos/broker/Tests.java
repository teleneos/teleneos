package org.teleneos.broker;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import org.apache.commons.io.IOUtils;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;

public class Tests {
	
	@Test
	public void test() throws UnknownHostException, IOException, JSONException {
		Socket clientSocket = new Socket("192.168.2.3", 6789);
		DataOutputStream outToServer = new DataOutputStream(
				clientSocket.getOutputStream());
		JSONObject map = new JSONObject();
		
//		map.put("type", "logout");
//		map.put("user", "miku");
		
		map.put("type", "login");
		map.put("user", "miku");
		map.put("ip", "10.1.0.46");
		map.put("maxbwdown", String.valueOf(120 * 1024 * 8));
		map.put("maxbwup", String.valueOf((120 * 1024 * 8) / 2));
		IOUtils.write(map.toString(), outToServer);
		clientSocket.close();
	}
}
