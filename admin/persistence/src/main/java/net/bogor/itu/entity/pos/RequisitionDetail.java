package net.bogor.itu.entity.pos;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.envers.Audited;
import org.hibernate.envers.RelationTargetAuditMode;
import org.meruvian.yama.persistence.DefaultPersistence;
/**
 * @author Edy Setiawan
 * 
 */
@Entity()
@Audited
@Table(name = "tc_requisition_detail")
public class RequisitionDetail extends DefaultPersistence {

	private Requisition requisition;
	private Item item;
	private int quantity;
	private Date dueDate;

	@ManyToOne
	@JoinColumn(name="requisition_id")
	@Audited(targetAuditMode=RelationTargetAuditMode.NOT_AUDITED)
	public Requisition getRequisition() {
		return requisition;
	}

	public void setRequisition(Requisition requisition) {
		this.requisition = requisition;
	}

	@ManyToOne
	@JoinColumn(name="item_id")
	@Audited(targetAuditMode=RelationTargetAuditMode.NOT_AUDITED)
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

	public Date getDueDate() {
		return dueDate;
	}

	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}

}
