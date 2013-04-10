package id.co.bonet.itu.noc.service;

import java.math.BigDecimal;

import id.co.bonet.itu.noc.Telecentre;
import id.co.bonet.itu.noc.TelecentreComputer;
import id.co.bonet.itu.noc.repository.TelecentreComputerRepository;
import id.co.bonet.itu.noc.repository.TelecentreRepository;

import javax.inject.Inject;

import org.apache.commons.lang.StringUtils;
import org.meruvian.yama.persistence.EntityListWrapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class TelecentreServiceImpl implements TelecentreService {
	
	@Inject
	private TelecentreRepository repository;
	
	@Inject
	private TelecentreComputerRepository computerRepository;

	@Override
	@Transactional
	public Telecentre save(Telecentre telecentre) {
		Telecentre tDb = repository.checkIfExist(telecentre.getTelecentre_id());
		if (tDb == null) {
			telecentre.setLatitude(new BigDecimal(0));
			telecentre.setLongitude(new BigDecimal(0));
			repository.persist(telecentre);
		} else {
			for (TelecentreComputer tc : telecentre.getTelecentreComputers()) {
				TelecentreComputer tcdb = computerRepository.checkIfExist(tDb.getId(), tc.getHostid());
				if (tcdb == null) {
					tc.setTelecentre(tDb);
					computerRepository.persist(tc);
				} else {
					tcdb.setAvailable(tc.getAvailable());
					tcdb.setDisable_until(tc.getDisable_until());
					tcdb.setError(tc.getError());
					tcdb.setErrors_from(tc.getErrors_from());
					tcdb.setHost(tc.getHost());
					tcdb.setHostid(tc.getHostid());
					tcdb.setIpmi_authtype(tc.getIpmi_authtype());
					tcdb.setIpmi_available(tc.getIpmi_available());
					tcdb.setIpmi_disable_until(tc.getIpmi_disable_until());
					tcdb.setIpmi_error(tc.getIpmi_error());
					tcdb.setIpmi_errors_from(tc.getIpmi_errors_from());
					tcdb.setIpmi_password(tc.getIpmi_password());
					tcdb.setIpmi_privilege(tc.getIpmi_privilege());
					tcdb.setIpmi_username(tc.getIpmi_username());
					tcdb.setJmx_available(tc.getJmx_available());
					tcdb.setJmx_disable_until(tc.getJmx_disable_until());
					tcdb.setJmx_error(tc.getJmx_error());
					tcdb.setJmx_errors_from(tc.getJmx_errors_from());
					tcdb.setLastaccess(tc.getLastaccess());
					tcdb.setMaintenance_from(tc.getMaintenance_from());
					tcdb.setMaintenance_status(tc.getMaintenance_status());
					tcdb.setMaintenance_type(tc.getMaintenance_type());
					tcdb.setMaintenanceid(tc.getMaintenanceid());
					tcdb.setName(tc.getName());
					tcdb.setProxy_hostid(tc.getProxy_hostid());
					tcdb.setSnmp_available(tc.getSnmp_available());
					tcdb.setSnmp_disable_until(tc.getSnmp_disable_until());
					tcdb.setSnmp_error(tc.getSnmp_error());
					tcdb.setSnmp_errors_from(tc.getSnmp_errors_from());
					tcdb.setStatus(tc.getStatus());
				}
				telecentre = tDb;
			}
		}
		return telecentre;
	}

	@Override
	public EntityListWrapper<Telecentre> all(int limit, int page) {
		return repository.findAll(0, 0);
	}

	@Override
	public Telecentre findById(String id) {
		return repository.findById(id);
	}

}
