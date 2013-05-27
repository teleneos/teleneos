package org.teleneos.pos.goodreceiving;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.envers.Audited;
import org.hibernate.envers.RelationTargetAuditMode;
import org.meruvian.yama.persistence.DefaultPersistence;
import org.teleneos.pos.bussinesspartner.BusinessPartner;

/**
 * @author Edy Setiawan
 * 
 */
@Entity
@Audited
@Table(name = "tc_good_receiving")
public class GoodReceiving extends DefaultPersistence {

	private static final long serialVersionUID = 8608161031742706348L;
	
	private String invoiceNo;
	private Date date;
	private BusinessPartner businessPartner;

	@Column(name = "invoice_no")
	public String getInvoiceNo() {
		return invoiceNo;
	}

	public void setInvoiceNo(String invoiceNo) {
		this.invoiceNo = invoiceNo;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	@ManyToOne
	@JoinColumn(name = "businesspartner_id")
	@Audited(targetAuditMode=RelationTargetAuditMode.NOT_AUDITED)
	public BusinessPartner getBusinessPartner() {
		return businessPartner;
	}

	public void setBusinessPartner(BusinessPartner businessPartner) {
		this.businessPartner = businessPartner;
	}
}
