package org.teleneos.broker;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;

import org.codehaus.jackson.map.ObjectMapper;

public class Tests {
	public static void main(String[] args) throws UnknownHostException, IOException {
		Socket clientSocket = new Socket("192.168.2.3", 6789);
		DataOutputStream outToServer = new DataOutputStream(
				clientSocket.getOutputStream());
		Map<String, String> map = new HashMap<String, String>();
		map.put("type", "logout");
		map.put("user", "dhinx");
		ObjectMapper mapper = new ObjectMapper();
		mapper.writeValue(outToServer, map);
		clientSocket.close();
	}
}
