package org.teleneos.broker;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.net.ServerSocket;
import java.net.Socket;

import org.apache.commons.io.IOUtils;

public class Main {
	
	public static final String RELOAD_HTB = "reload";
	public static final String DISCONNECT_REQ = "disconnect"; 
	
	public static void main(String[] args) throws IOException {
		String clientSentence;
		ServerSocket welcomeSocket = new ServerSocket(6789);
		System.out.println("Service Started");
		while (true) {
			Socket connectionSocket = welcomeSocket.accept();
			BufferedReader inFromClient = new BufferedReader(
					new InputStreamReader(connectionSocket.getInputStream()));
			clientSentence = inFromClient.readLine();
			System.out.println("Processing Request " + clientSentence);
			
			try {
				ProcessBuilder builder = null;
				if(clientSentence.startsWith(DISCONNECT_REQ)){
					String macAddr = clientSentence.substring(DISCONNECT_REQ.length(), clientSentence.length());
					System.out.println("disconnecting client "+macAddr);
					builder = new ProcessBuilder("echo meruvian | sudo -S chilli_query logout $1",
							macAddr);
				}else if(clientSentence.startsWith(RELOAD_HTB)){
					System.out.println("reloading htb services");
					builder = new ProcessBuilder("/sbin/htb eth0 stop & /sbin/htb eth0 start");
				}
				Process process = builder.start();
				StringWriter writer = new StringWriter();
				IOUtils.copy(process.getInputStream(), writer);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
