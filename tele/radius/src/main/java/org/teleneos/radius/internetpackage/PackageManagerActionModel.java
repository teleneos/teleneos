package org.teleneos.radius.internetpackage;

import org.meruvian.yama.actions.DefaultActionModel;
import org.meruvian.yama.persistence.EntityListWrapper;

public class PackageManagerActionModel extends DefaultActionModel {
	private InternetPackage internetPackage = new InternetPackage();
	private EntityListWrapper<InternetPackage> internetPackages = new EntityListWrapper<InternetPackage>();
	private int type;
	private int status;
	private PaymentMethod method;
	private long qt = 0, qb = 0, qs = 0, qns = 0;

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

	public PaymentMethod getMethod() {
		return method;
	}

	public void setMethod(PaymentMethod method) {
		this.method = method;
	}

	public long getQt() {
		return qt;
	}

	public void setQt(long qt) {
		this.qt = qt;
	}

	public long getQb() {
		return qb;
	}

	public void setQb(long qb) {
		this.qb = qb;
	}

	public long getQs() {
		return qs;
	}

	public void setQs(long qs) {
		this.qs = qs;
	}

	public long getQns() {
		return qns;
	}

	public void setQns(long qns) {
		this.qns = qns;
	}
}