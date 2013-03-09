package net.bogor.itu.action.admin;

import net.bogor.itu.entity.admin.ServicePackage;

import org.meruvian.yama.actions.DefaultActionModel;
import org.meruvian.yama.persistence.EntityListWrapper;

/**
 * @author Dian Aditya
 * 
 */
public class ServicePackageActionModel extends DefaultActionModel {
	private ServicePackage service = new ServicePackage();
	private EntityListWrapper<ServicePackage> services = new EntityListWrapper<ServicePackage>();

	public ServicePackage getService() {
		return service;
	}

	public void setService(ServicePackage service) {
		this.service = service;
	}

	public EntityListWrapper<ServicePackage> getServices() {
		return services;
	}

	public void setServices(EntityListWrapper<ServicePackage> services) {
		this.services = services;
	}
}