/**
 * 
 */
package org.teleneos.radius;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import org.codehaus.jackson.map.ObjectMapper;
import org.meruvian.yama.security.RadiusService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.teleneos.radius.userpackage.UserPackage;
import org.teleneos.radius.userpackage.UserPackageService;

/**
 * @author Dian Aditya
 * 
 */
@Service
public class RadiusServiceImpl implements RadiusService {

	@Value("${radius.client.remote_intet_address}")
	private String remoteInetAddress;

	@Value("${radius.client.remote_socket_port}")
	private int remoteSocketPort;
	
	@Inject
	private UserPackageService userPackageService; 
	
	@Override
	public void logout(String user) throws IOException {
		Socket clientSocket = new Socket(remoteInetAddress, remoteSocketPort);
		DataOutputStream outToServer = new DataOutputStream(
				clientSocket.getOutputStream());
		Map<String, String> map = new HashMap<String, String>();
		map.put("type", "logout");
		map.put("user", user);
		ObjectMapper mapper = new ObjectMapper();
		mapper.writeValue(outToServer, map);
		clientSocket.close();
	}

	@Override
	public void login(String user, String ip)
			throws IOException {
		Socket clientSocket = new Socket(remoteInetAddress, remoteSocketPort);
		DataOutputStream outToServer = new DataOutputStream(
				clientSocket.getOutputStream());
		Map<String, String> map = new HashMap<String, String>();
		map.put("type", "login");
		map.put("user", user);
		map.put("ip", ip);
		UserPackage userPackage = userPackageService.findActivePackage(user);
		map.put("maxbwdown", String.valueOf(userPackage.getInternetPackage()
				.getSpeed() * 1024 * 8));
		map.put("maxbwup",
				String.valueOf((userPackage.getInternetPackage().getSpeed() * 1024 * 8) / 2));
		ObjectMapper mapper = new ObjectMapper();
		mapper.writeValue(outToServer, map);
		clientSocket.close();
	}
}
