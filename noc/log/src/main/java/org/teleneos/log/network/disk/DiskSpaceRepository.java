/**
 * 
 */
package org.teleneos.log.network.disk;

import java.util.List;

import javax.persistence.TypedQuery;

import org.meruvian.yama.persistence.PersistenceRepository;
import org.springframework.stereotype.Repository;
import org.teleneos.log.network.disk.DiskSpace.DiskType;

/**
 * @author Dian Aditya
 * 
 */
@Repository
public class DiskSpaceRepository extends PersistenceRepository<DiskSpace> {
	public enum DiskSpaceType {
		DISK, SWAP
	}

	public DiskSpace[] findLatestSpace(String telecentre, DiskSpaceType type) {
		DiskSpace[] results = new DiskSpace[2];
		DiskType freeDiskType;
		DiskType totalDiskType;

		switch (type) {
		case DISK:
			freeDiskType = DiskType.FREE_DISK;
			totalDiskType = DiskType.TOTAL_DISK;
			break;
		case SWAP:
			freeDiskType = DiskType.FREE_SWAP;
			totalDiskType = DiskType.TOTAL_SWAP;
			break;
		default:
			throw new NullPointerException("DiskSpaceType cannot be null");
		}

		String criteria = "d.telecentre = ?1 AND d.diskType = ?2 ORDER BY "
				+ "d.logInformation.createDate";

		TypedQuery<DiskSpace> query = createQuery(entityClass, "d", "d",
				criteria, telecentre, freeDiskType);
		query.setMaxResults(1);

		List<DiskSpace> d = query.getResultList();
		if (d.size() > 0) {
			results[0] = d.get(0);
		}

		query = createQuery(entityClass, "d", "d", criteria, telecentre,
				totalDiskType);
		query.setMaxResults(1);

		d = query.getResultList();
		if (d.size() > 0) {
			results[1] = d.get(0);
		}

		return results;
	}
}
