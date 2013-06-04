/**
 * 
 */
package org.teleneos.log.network.disk;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

import org.teleneos.log.network.Item;

/**
 * @author Dian Aditya
 * 
 */
@Entity
@Table(name = "noc_network_disk")
public class DiskSpace extends Item {
	public enum DiskType {
		FREE_SWAP, TOTAL_SWAP, FREE_DISK, TOTAL_DISK
	}

	private DiskType diskType;

	@Column(name = "disk_type")
	@Enumerated(EnumType.ORDINAL)
	public DiskType getDiskType() {
		return diskType;
	}

	public void setDiskType(DiskType diskType) {
		this.diskType = diskType;
	}
}
