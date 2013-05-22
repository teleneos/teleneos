package org.teleneos.radius;

import java.io.IOException;

/**
 * @author Dian Aditya
 * 
 */
public interface RadiusSerivce {
	void logout(String macAddress) throws IOException;
}
