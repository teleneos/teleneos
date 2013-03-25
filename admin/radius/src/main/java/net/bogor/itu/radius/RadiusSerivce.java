package net.bogor.itu.radius;

import java.io.IOException;

/**
 * @author Dian Aditya
 * 
 */
public interface RadiusSerivce {
	void logout(String macAddress) throws IOException;
}
