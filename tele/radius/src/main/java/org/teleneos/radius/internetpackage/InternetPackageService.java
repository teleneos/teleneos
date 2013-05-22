package org.teleneos.radius.internetpackage;

import org.meruvian.yama.persistence.EntityListWrapper;

public interface InternetPackageService {
	EntityListWrapper<InternetPackage> getAll(String name, int limit, int page);
}
