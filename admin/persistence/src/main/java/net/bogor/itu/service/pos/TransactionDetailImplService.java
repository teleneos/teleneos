package net.bogor.itu.service.pos;

import java.text.ParseException;

import javax.inject.Inject;

import net.bogor.itu.entity.pos.TransactionDetail;
import net.bogor.itu.repository.pos.TransactionDetailRepository;

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
public class TransactionDetailImplService implements TransactionDetailService {

	@Inject
	private TransactionDetailRepository tDetailRepository;

	@Override
	public TransactionDetail findById(String id) {
		return tDetailRepository.findById(id);
	}

	@Override
	@Transactional
	public TransactionDetail save(TransactionDetail transactionDetail) {
		if (StringUtils.isBlank(transactionDetail.getId())) {
			transactionDetail.setId(null);

			tDetailRepository.persist(transactionDetail);
		} else {
			TransactionDetail td = tDetailRepository.load(transactionDetail
					.getId());
			td.setItem(transactionDetail.getItem());
			td.setQuantity(transactionDetail.getQuantity());
			td.setTransactionHeader(transactionDetail.getTransactionHeader());
			td.setInternetPackage(transactionDetail.getInternetPackage());

			transactionDetail = td;
		}
		return transactionDetail;
	}

	@Override
	public EntityListWrapper<TransactionDetail> findByKeyword(String keyword,
			int limit, int page) {
		if (StringUtils.isBlank(keyword))
			return tDetailRepository.findAll(limit, page);
		return tDetailRepository.findByKeyword(keyword, limit, page);
	}

	@Override
	public EntityListWrapper<Object[]> report(String from, String to) {
		try {
			return tDetailRepository.report(from, to);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public EntityListWrapper<Object[]> internet(String periodfrom,
			String periodto) {
		try {
			return tDetailRepository.internet(periodfrom, periodto);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	@Transactional
	public void remove(TransactionDetail detail) {
		tDetailRepository.delete(tDetailRepository.load(detail.getId()));
	}

}
