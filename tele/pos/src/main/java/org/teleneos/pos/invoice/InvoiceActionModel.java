package org.teleneos.pos.invoice;

import org.meruvian.yama.actions.DefaultActionModel;
import org.meruvian.yama.persistence.EntityListWrapper;
import org.teleneos.pos.po.PurchaseOrder;
import org.teleneos.pos.po.PurchaseOrderDetail;

/**
 * @author Edy Setiawan
 * 
 */
public class InvoiceActionModel extends DefaultActionModel {

	private Invoice invoice = new Invoice();
	private EntityListWrapper<Invoice> invoices = new EntityListWrapper<Invoice>();
	private InvoiceDetail invoiceDetail = new InvoiceDetail();
	private EntityListWrapper<InvoiceDetail> invoiceDetails = new EntityListWrapper<InvoiceDetail>();
	private PurchaseOrder purchaseOrder = new PurchaseOrder();
	private EntityListWrapper<PurchaseOrder> purchaseOrders = new EntityListWrapper<PurchaseOrder>();
	private PurchaseOrderDetail purchaseOrderDetail = new PurchaseOrderDetail();
	private EntityListWrapper<PurchaseOrderDetail> purchaseOrderDetails = new EntityListWrapper<PurchaseOrderDetail>();
	private String price;
	private String quantity;
	private String item;

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

	public PurchaseOrder getPurchaseOrder() {
		return purchaseOrder;
	}

	public void setPurchaseOrder(PurchaseOrder purchaseOrder) {
		this.purchaseOrder = purchaseOrder;
	}

	public EntityListWrapper<PurchaseOrder> getPurchaseOrders() {
		return purchaseOrders;
	}

	public void setPurchaseOrders(
			EntityListWrapper<PurchaseOrder> purchaseOrders) {
		this.purchaseOrders = purchaseOrders;
	}

	public PurchaseOrderDetail getPurchaseOrderDetail() {
		return purchaseOrderDetail;
	}

	public void setPurchaseOrderDetail(PurchaseOrderDetail purchaseOrderDetail) {
		this.purchaseOrderDetail = purchaseOrderDetail;
	}

	public EntityListWrapper<PurchaseOrderDetail> getPurchaseOrderDetails() {
		return purchaseOrderDetails;
	}

	public void setPurchaseOrderDetails(
			EntityListWrapper<PurchaseOrderDetail> purchaseOrderDetails) {
		this.purchaseOrderDetails = purchaseOrderDetails;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
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
