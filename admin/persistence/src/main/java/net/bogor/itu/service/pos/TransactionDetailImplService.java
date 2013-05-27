package net.bogor.itu.service.pos;

import java.text.ParseException;
import java.util.Date;

import javax.inject.Inject;

import net.bogor.itu.entity.pos.Conversion;
import net.bogor.itu.entity.pos.InventoryOnhand;
import net.bogor.itu.entity.pos.Item;
import net.bogor.itu.entity.pos.TransactionDetail;
import net.bogor.itu.entity.radius.UserPackage.Status;
import net.bogor.itu.repository.pos.InventoryOnhandRepository;
import net.bogor.itu.repository.pos.TransactionDetailRepository;
import net.bogor.itu.service.radius.UserPackageService;

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
	
	@Inject
	private ConversionService conversionService;
	
	@Inject
	private ItemService itemService;
	
	@Inject
	private InventoryOnhandRepository onhandRepository;
	
	@Inject
	private UserPackageService userPackageService;
	
	@Override
	public TransactionDetail findById(String id) {
		return tDetailRepository.findById(id);
	}

	@Override
	@Transactional
	public TransactionDetail save(TransactionDetail transactionDetail) throws StockNotFoundException, InvaidUnitOfMeasurementException {
		if (StringUtils.isBlank(transactionDetail.getId())) {
			transactionDetail.setId(null);
			if (transactionDetail.getItem() != null) {
				Item item = itemService.findById(transactionDetail.getItem()
						.getId());
				Conversion conversion = conversionService.findConversion(
						transactionDetail.getUom().getId(), item.getUom()
								.getId());
				transactionDetail.setConversion(conversion);
				InventoryOnhand stock = onhandRepository.findByItem(transactionDetail
						.getItem().getId());
				if (transactionDetail.getConversion() != null) {
					if (stock == null) {
						throw new StockNotFoundException();
					} else if (stock.getStock() < (transactionDetail
							.getQuantity() * transactionDetail.getConversion()
							.getMultiplyRate())) {
						throw new StockNotFoundException();
					} else {

					}
				} else if(transactionDetail.getUom().getId().equals(item.getUom().getId())){
					if (stock == null) {
						throw new StockNotFoundException();
					} else if (stock.getStock() < transactionDetail
							.getQuantity()) {
						throw new StockNotFoundException();
					} else {

					}
				}else{
					throw new InvaidUnitOfMeasurementException();
				}
			}
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
	
	public class StockNotFoundException extends Exception{

		private static final long serialVersionUID = 4029360388588030554L;
		
		public StockNotFoundException() {
			super("Stock Not Found");
		}
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
		TransactionDetail transactionDetail = tDetailRepository.load(detail.getId()); 
		if (transactionDetail.getUserPackage() != null) {
			transactionDetail.getUserPackage().setStatus(Status.ACTIVE);
			userPackageService.save(transactionDetail.getUserPackage());
		}
		tDetailRepository.delete(transactionDetail);
	}

	@Override
	public EntityListWrapper<Object[]> daily(String date) {
		return tDetailRepository.daily(date);
	}

	@Override
	public EntityListWrapper<Object[]> monthly(String date) {
		return tDetailRepository.monthly(date);
	}

	@Override
	public Date getFirstTransaction() {
		return tDetailRepository.getFirstTransaction();
	}

	@Override
	public EntityListWrapper<Object[]> weekly(String date) {
		return tDetailRepository.weekly(date);
	}

}
