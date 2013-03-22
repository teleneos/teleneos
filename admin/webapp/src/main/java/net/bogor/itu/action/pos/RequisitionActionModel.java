package net.bogor.itu.action.pos;

import net.bogor.itu.entity.pos.Requisition;
import net.bogor.itu.entity.pos.RequisitionDetail;

import org.meruvian.yama.actions.DefaultActionModel;
import org.meruvian.yama.persistence.EntityListWrapper;

/**
 * @author Edy Setiawan
 * 
 */
public class RequisitionActionModel extends DefaultActionModel {

	private Requisition requisition = new Requisition();
	private EntityListWrapper<Requisition> requisitions = new EntityListWrapper<Requisition>();
	private RequisitionDetail requisitionDetail = new RequisitionDetail();
	private EntityListWrapper<RequisitionDetail> requisitionDetails = new EntityListWrapper<RequisitionDetail>();

	public Requisition getRequisition() {
		return requisition;
	}

	public void setRequisition(Requisition requisition) {
		this.requisition = requisition;
	}

	public EntityListWrapper<Requisition> getRequisitions() {
		return requisitions;
	}

	public void setRequisitions(EntityListWrapper<Requisition> requisitions) {
		this.requisitions = requisitions;
	}

	public RequisitionDetail getRequisitionDetail() {
		return requisitionDetail;
	}

	public void setRequisitionDetail(RequisitionDetail requisitionDetail) {
		this.requisitionDetail = requisitionDetail;
	}

	public EntityListWrapper<RequisitionDetail> getRequisitionDetails() {
		return requisitionDetails;
	}

	public void setRequisitionDetails(
			EntityListWrapper<RequisitionDetail> requisitionDetails) {
		this.requisitionDetails = requisitionDetails;
	}

}
