package net.bogor.itu.action.master;

import net.bogor.itu.entity.master.InternetPackage;

import org.meruvian.yama.actions.DefaultActionModel;
import org.meruvian.yama.persistence.EntityListWrapper;

public class PackageManagerActionModel extends DefaultActionModel {
	private InternetPackage internetPackage = new InternetPackage();
	private EntityListWrapper<InternetPackage> internetPackages = new EntityListWrapper<InternetPackage>();
	private int type;
	private int status;

	public InternetPackage getInternetPackage() {
		return internetPackage;
	}

	public void setInternetPackage(InternetPackage internetPackage) {
		this.internetPackage = internetPackage;
	}

	public EntityListWrapper<InternetPackage> getInternetPackages() {
		return internetPackages;
	}

	public void setInternetPackages(
			EntityListWrapper<InternetPackage> internetPackages) {
		this.internetPackages = internetPackages;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

}
