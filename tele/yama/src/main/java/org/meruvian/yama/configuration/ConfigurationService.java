package org.meruvian.yama.configuration;

import org.meruvian.yama.persistence.EntityListWrapper;

public interface ConfigurationService {
	
	EntityListWrapper<Configuration> configurations(String keyword, int limit, int page);
	
	Configuration save(Configuration configuration);

	Configuration findById(String id);

	Configuration findByKey(String id);
	
	void remove(Configuration configuration);
	
}
