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
		System.out.println("=================Service Started================");
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
									"chilli_query list | grep ' "+map.get("user")+" ' | awk '{print $1}'" });
					IOUtils.copy(process.getInputStream(), mac);
					System.out.println("disconnecting client ("+map.get("user")+") " + mac.toString().trim());
					Runtime.getRuntime().exec(
							new String[] {
									"bash",
									"-c",
									"chilli_query logout "
											+ mac.toString() });
					inFromClient.close();
				} 
				if (map.get("type").equals("login")) {
					String username = map.get("user");
					Process process = Runtime.getRuntime()
							.exec(new String[] {
									"bash",
									"-c",
									"chilli_query authorize ip "+ map.get("ip") + 
									" username " + username + 
									" maxbwdown " + map.get("maxbwdown")+ 
									" maxbwup " + map.get("maxbwup") 
									});
					StringWriter writer = new StringWriter();
					IOUtils.copy(process.getInputStream(), writer);
					System.err.println("authorizing user " +username+"("+map.get("ip")+"), Speed "+(Integer.parseInt(map.get("maxbwdown"))/1024/8)+" kbps : "+writer.toString());
					inFromClient.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
