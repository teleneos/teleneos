package net.bogor.itu.action.pos;

import net.bogor.itu.entity.pos.GoodReceiving;
import net.bogor.itu.entity.pos.GoodReceivingDetail;
import net.bogor.itu.entity.pos.Invoice;
import net.bogor.itu.entity.pos.InvoiceDetail;

import org.meruvian.yama.actions.DefaultActionModel;
import org.meruvian.yama.persistence.EntityListWrapper;

/**
 * @author Edy Setiawan
 * 
 */
public class goodReceivingActionModel extends DefaultActionModel {

	private GoodReceiving goodReceiving = new GoodReceiving();
	private EntityListWrapper<GoodReceiving> goodReceivings = new EntityListWrapper<GoodReceiving>();
	private GoodReceivingDetail goodReceivingDetail = new GoodReceivingDetail();
	private EntityListWrapper<GoodReceivingDetail> goodReceivingDetails = new EntityListWrapper<GoodReceivingDetail>();
	private Invoice invoice = new Invoice();
	private EntityListWrapper<Invoice> invoices = new EntityListWrapper<Invoice>();
	private InvoiceDetail invoiceDetail = new InvoiceDetail();
	private EntityListWrapper<InvoiceDetail> invoiceDetails = new EntityListWrapper<InvoiceDetail>();
	private String quantity;
	private String item;

	public GoodReceiving getGoodReceiving() {
		return goodReceiving;
	}

	public void setGoodReceiving(GoodReceiving goodReceiving) {
		this.goodReceiving = goodReceiving;
	}

	public EntityListWrapper<GoodReceiving> getGoodReceivings() {
		return goodReceivings;
	}

	public void setGoodReceivings(
			EntityListWrapper<GoodReceiving> goodReceivings) {
		this.goodReceivings = goodReceivings;
	}

	public GoodReceivingDetail getGoodReceivingDetail() {
		return goodReceivingDetail;
	}

	public void setGoodReceivingDetail(GoodReceivingDetail goodReceivingDetail) {
		this.goodReceivingDetail = goodReceivingDetail;
	}

	public EntityListWrapper<GoodReceivingDetail> getGoodReceivingDetails() {
		return goodReceivingDetails;
	}

	public void setGoodReceivingDetails(
			EntityListWrapper<GoodReceivingDetail> goodReceivingDetails) {
		this.goodReceivingDetails = goodReceivingDetails;
	}

	public Invoice getInvoice() {
		return invoice;
	}

	public void setInvoice(Invoice invoice) {
		this.invoice = invoice;
	}

	public EntityListWrapper<Invoice> getInvoices() {
		return invoices;
	}

	public void setInvoices(EntityListWrapper<Invoice> invoices) {
		this.invoices = invoices;
	}

	public InvoiceDetail getInvoiceDetail() {
		return invoiceDetail;
	}

	public void setInvoiceDetail(InvoiceDetail invoiceDetail) {
		this.invoiceDetail = invoiceDetail;
	}

	public EntityListWrapper<InvoiceDetail> getInvoiceDetails() {
		return invoiceDetails;
	}

	public void setInvoiceDetails(
			EntityListWrapper<InvoiceDetail> invoiceDetails) {
		this.invoiceDetails = invoiceDetails;
	}

	public String getQuantity() {
		return quantity;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}

	public String getItem() {
		return item;
	}

	public void setItem(String item) {
		this.item = item;
	}

}
