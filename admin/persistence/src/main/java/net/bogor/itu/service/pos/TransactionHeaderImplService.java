package net.bogor.itu.service.pos;

import javax.inject.Inject;

import net.bogor.itu.entity.admin.User;
import net.bogor.itu.entity.master.InternetPackage;
import net.bogor.itu.entity.pos.TransactionDetail;
import net.bogor.itu.entity.pos.TransactionHeader;
import net.bogor.itu.repository.admin.UserRepository;
import net.bogor.itu.repository.pos.TransactionHeaderRepository;
import net.bogor.itu.service.admin.UserService;

import org.apache.commons.lang.StringUtils;
import org.meruvian.yama.persistence.EntityListWrapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Edy Setiawan
 * 
 */
@Service
@Transactional(readOnly = true)
public class TransactionHeaderImplService implements TransactionHeaderService {

	@Inject
	private TransactionHeaderRepository tHeaderRepository;

	@Inject
	private UserRepository userRepository;

	@Override
	public TransactionHeader findById(String id) {
		return tHeaderRepository.findById(id);
	}

	@Override
	@Transactional
	public TransactionHeader save(TransactionHeader transactionHeader) {
		if (StringUtils.isBlank(transactionHeader.getId())) {
			transactionHeader.setId(null);

			tHeaderRepository.persist(transactionHeader);
		} else {
			TransactionHeader th = tHeaderRepository.load(transactionHeader
					.getId());
			th.setCash(transactionHeader.getCash());
			User user = userRepository.load(th.getUser().getId());
			for (TransactionDetail d : th.getDetails()) {
				InternetPackage p = d.getInternetPackage();
				if (p != null) {
					user.setInternetPackage(p);
					break;
				}
			}

			transactionHeader = th;
		}
		return transactionHeader;
	}

	@Override
	public EntityListWrapper<TransactionHeader> findByKeyword(String keyword,
			String order, String orderBy, int limit, int page) {
		if (StringUtils.isBlank(keyword))
			return tHeaderRepository.findAll(limit, page);
		return tHeaderRepository.findByKeyword(keyword, order, orderBy, limit,
				page, "OR");
	}

}
