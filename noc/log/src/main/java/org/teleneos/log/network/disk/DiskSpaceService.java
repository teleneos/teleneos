/**
 * 
 */
package org.teleneos.log.network.disk;

/**
 * @author Dian Aditya
 * 
 */
public interface DiskSpaceService {

	/**
	 * 
	 * @param telecentre
	 *            ID of telecentre
	 * @return [FreeSwapSpace, TotalSwapSpace]
	 */
	DiskSpace[] findSwapSpace(String telecentre);

	/**
	 * 
	 * @param telecentre
	 *            ID of telecentre
	 * @return [FreeDiskSpace, TotalDiskSpace]
	 */
	DiskSpace[] findDiskSpace(String telecentre);
}
