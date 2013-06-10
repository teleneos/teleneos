/**
 * 
 */
package org.teleneos.log.network.disk;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.teleneos.log.network.disk.DiskSpaceRepository.DiskSpaceType;

/**
 * @author Dian Aditya
 * 
 */
@Service
@Transactional(readOnly = false)
public class DiskSpaceServiceImpl implements DiskSpaceService {

	@Inject
	private DiskSpaceRepository diskRepository;

	@Override
	public DiskSpace[] findSwapSpace(String telecentre) {
		return diskRepository.findLatestSpace(telecentre, DiskSpaceType.SWAP);
	}

	@Override
	public DiskSpace[] findDiskSpace(String telecentre) {
		return diskRepository.findLatestSpace(telecentre, DiskSpaceType.DISK);
	}

}
