/**
 * 
 */
package org.teleneos.radius;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * @author Dian Aditya
 * 
 */
@Service
public class RadiusServiceImpl implements RadiusSerivce {

	@Value("${radius.client.remote_intet_address}")
	private String remoteInetAddress;

	@Value("${radius.client.remote_socket_port}")
	private int remoteSocketPort;

	@Override
	public void logout(String macAddress) throws IOException {
		Socket clientSocket = new Socket(remoteInetAddress, remoteSocketPort);
		DataOutputStream outToServer = new DataOutputStream(
				clientSocket.getOutputStream());
		outToServer.writeBytes(macAddress + '\n');
		clientSocket.close();
	}
}
