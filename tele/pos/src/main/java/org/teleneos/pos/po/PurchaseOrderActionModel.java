package org.teleneos.pos.po;

import org.meruvian.yama.actions.DefaultActionModel;
import org.meruvian.yama.persistence.EntityListWrapper;
import org.teleneos.pos.requisition.RequisitionDetail;

/**
 * @author Edy Setiawan
 * 
 */
public class PurchaseOrderActionModel extends DefaultActionModel {

	private PurchaseOrder purchaseOrder = new PurchaseOrder();
	private EntityListWrapper<PurchaseOrder> purchaseOrders = new EntityListWrapper<PurchaseOrder>();
	private PurchaseOrderDetail purchaseOrderDetail = new PurchaseOrderDetail();
	private EntityListWrapper<PurchaseOrderDetail> purchaseOrderDetails = new EntityListWrapper<PurchaseOrderDetail>();
	private EntityListWrapper<RequisitionDetail> requisitionDetails = new EntityListWrapper<RequisitionDetail>();
	private RequisitionDetail requisitionDetail = new RequisitionDetail();
	private String price;
	private String quantity;
	private String item;

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

	public EntityListWrapper<RequisitionDetail> getRequisitionDetails() {
		return requisitionDetails;
	}

	public void setRequisitionDetails(
			EntityListWrapper<RequisitionDetail> requisitionDetails) {
		this.requisitionDetails = requisitionDetails;
	}

	public RequisitionDetail getRequisitionDetail() {
		return requisitionDetail;
	}

	public void setRequisitionDetail(RequisitionDetail requisitionDetail) {
		this.requisitionDetail = requisitionDetail;
	}

	public String getItem() {
		return item;
	}

	public void setItem(String item) {
		this.item = item;
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

}
