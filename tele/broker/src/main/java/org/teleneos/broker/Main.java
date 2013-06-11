package org.teleneos.broker;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Map;

import org.apache.commons.io.IOUtils;
import org.codehaus.jackson.map.ObjectMapper;

public class Main {

	public static void main(String[] args) throws IOException {
		ServerSocket welcomeSocket = new ServerSocket(6789);
		System.out.println("Service Started");
		while (true) {
			Socket connectionSocket = welcomeSocket.accept();
			BufferedReader inFromClient = new BufferedReader(
					new InputStreamReader(connectionSocket.getInputStream()));
			ObjectMapper mapper = new ObjectMapper();
			@SuppressWarnings("unchecked")
			Map<String, String> map = mapper.readValue(inFromClient.readLine(),
					Map.class);
			try {
				if (map.get("type").equals("logout")) {
					StringWriter mac = new StringWriter();
					Process process = Runtime
							.getRuntime()
							.exec(new String[] { "bash", "-c",
									"sudo -S chilli_query list | grep ' miku ' | awk '{print $1}'" });
					IOUtils.copy(process.getInputStream(), mac);
					System.out
							.println("disconnecting client " + mac.toString());
					Runtime.getRuntime().exec(
							new String[] {
									"bash",
									"-c",
									"echo meruvian | sudo -S chilli_query logout "
											+ mac.toString() });
				} else if (map.get("type").equals("login")) {
					String username = map.get("user");
					System.out.println("authorizing user " + username);
					Runtime.getRuntime()
							.exec(new String[] {
									"bash",
									"-c",
									"chilli_query authorize ip "+ map.get("ip") + 
									" username " + username + 
									" maxbwdown " + map.get("maxbwdown")+ 
									" maxbwup " + map.get("maxbwup") 
									});
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
