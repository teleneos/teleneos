package org.teleneos.broker;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;

import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Test;

public class Tests {
	
	@Test
	public void test() throws UnknownHostException, IOException {
		Socket clientSocket = new Socket("192.168.2.3", 6789);
		DataOutputStream outToServer = new DataOutputStream(
				clientSocket.getOutputStream());
		Map<String, String> map = new HashMap<String, String>();
		
//		map.put("type", "logout");
//		map.put("user", "dhinx");
		
		map.put("type", "login");
		map.put("user", "miku");
		map.put("ip", "10.1.0.38");
		map.put("maxbwdown", String.valueOf(120 * 1024 * 8));
		map.put("maxbwup", String.valueOf((120 * 1024 * 8) / 2));
		
		ObjectMapper mapper = new ObjectMapper();
		mapper.writeValue(outToServer, map);
		clientSocket.close();
	}
}
