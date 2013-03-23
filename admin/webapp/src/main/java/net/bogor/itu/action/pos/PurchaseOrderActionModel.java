package net.bogor.itu.action.pos;

import net.bogor.itu.entity.pos.PurchaseOrder;
import net.bogor.itu.entity.pos.PurchaseOrderDetail;

import org.meruvian.yama.actions.DefaultActionModel;
import org.meruvian.yama.persistence.EntityListWrapper;

/**
 * @author Edy Setiawan
 * 
 */
public class PurchaseOrderActionModel extends DefaultActionModel {

	private PurchaseOrder purchaseOrder = new PurchaseOrder();
	private EntityListWrapper<PurchaseOrder> purchaseOrders = new EntityListWrapper<PurchaseOrder>();
	private PurchaseOrderDetail purchaseOrderDetail = new PurchaseOrderDetail();
	private EntityListWrapper<PurchaseOrderDetail> purchaseOrderDetails = new EntityListWrapper<PurchaseOrderDetail>();

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

}
