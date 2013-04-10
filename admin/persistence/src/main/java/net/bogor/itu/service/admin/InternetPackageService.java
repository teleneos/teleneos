package net.bogor.itu.service.admin;

import net.bogor.itu.entity.master.InternetPackage;

import org.meruvian.yama.persistence.EntityListWrapper;

public interface InternetPackageService {
	EntityListWrapper<InternetPackage> getAll(String name, int limit, int page);
}
