package org.meruvian.yama.security;

import java.io.IOException;

/**
 * @author Dian Aditya
 * 
 */
public interface RadiusService {

	void logout(String user) throws IOException;

	void login(String user, String ip) throws IOException;
}
