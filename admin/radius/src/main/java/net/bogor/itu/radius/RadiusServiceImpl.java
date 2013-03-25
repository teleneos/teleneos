/**
 * 
 */
package net.bogor.itu.radius;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import org.springframework.stereotype.Service;

/**
 * @author Dian Aditya
 * 
 */
@Service
public class RadiusServiceImpl implements RadiusSerivce {

	@Override
	public void logout(String macAddress) throws IOException {
		Socket clientSocket = new Socket("192.168.2.3", 6789);
		DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
		outToServer.writeBytes(macAddress + '\n');
		clientSocket.close();
	}
}
