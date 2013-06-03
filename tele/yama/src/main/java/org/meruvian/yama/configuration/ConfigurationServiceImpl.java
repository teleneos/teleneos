package org.meruvian.yama.configuration;

import javax.inject.Inject;

import org.apache.commons.lang.StringUtils;
import org.meruvian.yama.persistence.EntityListWrapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class ConfigurationServiceImpl implements ConfigurationService {

	@Inject
	private ConfigurationRepository configurationRepository;

	@Override
	@Transactional
	public Configuration save(Configuration configuration) {
		if (StringUtils.isEmpty(configuration.getId())) {
			System.err.println("asasas");
			configuration.setId(null);
			configuration.setKey(configuration.getKey());
			configurationRepository.persist(configuration);
		} else {
			Configuration dt = configurationRepository.load(configuration
					.getId());
			System.err.println(configuration.getValue()+"asfdasdfasdf");
			dt.setValue(configuration.getValue());
			configuration = dt;
		}
		return configuration;
	}

	@Override
	public Configuration findById(String id) {
		return configurationRepository.findById(id);
	}

	@Override
	@Transactional
	public void remove(Configuration configuration) {
		configurationRepository.delete(configuration);
	}

	@Override
	@Transactional
	public Configuration findByKey(String key)  {
		Configuration configuration = configurationRepository.findByKey(key);
		if(configuration == null){
			Configuration cf = new Configuration();
			cf.setKey(key);
			save(cf);
			return configuration;
		}else{
			return configuration;
		}
	}

	@Override
	public EntityListWrapper<Configuration> configurations(String keyword,
			int limit, int page) {
		if (StringUtils.isBlank(keyword))
			return configurationRepository.findAll(limit, page);
		return configurationRepository.findByKeyword(keyword, null, null,
				limit, page, "OR");
	}

}
