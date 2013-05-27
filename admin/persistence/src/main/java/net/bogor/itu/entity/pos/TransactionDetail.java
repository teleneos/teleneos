package net.bogor.itu.entity.pos;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import net.bogor.itu.entity.master.InternetPackage;
import net.bogor.itu.entity.radius.UserPackage;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.hibernate.envers.Audited;
import org.hibernate.envers.RelationTargetAuditMode;
import org.meruvian.yama.persistence.DefaultPersistence;

/**
 * @author Edy Setiawan
 * 
 */
@Entity
@Audited
@Table(name = "tc_transaction_detail")
public class TransactionDetail extends DefaultPersistence {

	private static final long serialVersionUID = -4878165625212527205L;
	
	private Item item;
	private int quantity = 1;
	private Long price = 0L;
	private TransactionHeader transactionHeader;
	private InternetPackage internetPackage;
	private UserPackage userPackage;
	private UnitOfMeasure uom;
	private Conversion conversion;

	@ManyToOne
	@JoinColumn(name = "item_id")
	@Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public Long getPrice() {
		return price;
	}

	public void setPrice(Long price) {
		this.price = price;
	}

	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "transcation_header_id")
	@Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
	public TransactionHeader getTransactionHeader() {
		return transactionHeader;
	}

	public void setTransactionHeader(TransactionHeader transactionHeader) {
		this.transactionHeader = transactionHeader;
	}

	@ManyToOne
	@JoinColumn(name = "internetpackage_id")
	@Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
	public InternetPackage getInternetPackage() {
		return internetPackage;
	}

	public void setInternetPackage(InternetPackage internetPackage) {
		this.internetPackage = internetPackage;
	}
	
	@ManyToOne
	@JoinColumn(name = "userpackage_id")
	@Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
	public UserPackage getUserPackage() {
		return userPackage;
	}

	public void setUserPackage(UserPackage userPackage) {
		this.userPackage = userPackage;
	}

	@ManyToOne
	@JoinColumn(name = "uom_id")
	@Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
	public UnitOfMeasure getUom() {
		return uom;
	}

	public void setUom(UnitOfMeasure uom) {
		this.uom = uom;
	}

	@ManyToOne
	@JoinColumn(name = "conversion_id")
	@Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
	public Conversion getConversion() {
		return conversion;
	}

	public void setConversion(Conversion conversion) {
		this.conversion = conversion;
	}

}
