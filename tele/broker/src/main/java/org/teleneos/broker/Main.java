package org.teleneos.broker;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.net.ServerSocket;
import java.net.Socket;

import org.apache.commons.io.IOUtils;
import org.json.JSONObject;

public class Main {
	public static void main(String[] args) throws IOException {
		ServerSocket welcomeSocket = new ServerSocket(6789);
		System.out.println("=================Service Started================");
		String commands = "";
		while (true) {
			Socket connectionSocket = welcomeSocket.accept();
			BufferedReader inFromClient = new BufferedReader(
					new InputStreamReader(connectionSocket.getInputStream()));
			if (inFromClient != null) {
				try {
					JSONObject mapper = new JSONObject(inFromClient.readLine());
					if (mapper.get("type").equals("logout")) {
						StringWriter mac = new StringWriter();
						commands = "chilli_query logout " + mac.toString();
						Process process = Runtime
								.getRuntime()
								.exec(new String[] { "bash", "-c",
										"chilli_query list | grep ' "+mapper.get("user")+" ' | awk '{print $1}'" });
						IOUtils.copy(process.getInputStream(), mac);
						System.out.println("executing "+commands);
						Runtime.getRuntime().exec(new String[] { "bash", "-c", commands });
						inFromClient.close();
					} 
					if (mapper.get("type").equals("login")) {
						String username = mapper.getString("user");
						commands = "chilli_query authorize ip "+ mapper.get("ip") + 
								" username " + username + 
								" maxbwdown " + mapper.get("maxbwdown")+ 
								" maxbwup " + mapper.get("maxbwup");
						Process process = Runtime.getRuntime().exec(
								new String[] { "bash", "-c", commands });
						StringWriter writer = new StringWriter();
						IOUtils.copy(process.getInputStream(), writer);
						System.out.println("executing "+commands);
						inFromClient.close();
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
}
