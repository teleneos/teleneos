package org.teleneos.pos.conversion;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.envers.Audited;
import org.hibernate.envers.RelationTargetAuditMode;
import org.meruvian.yama.persistence.DefaultPersistence;
import org.teleneos.pos.uom.UnitOfMeasure;

@Entity
@Audited
@Table(name = "tc_conversion")
public class Conversion extends DefaultPersistence {

	private static final long serialVersionUID = -6841917448736916762L;

	private UnitOfMeasure uomFrom;
	private UnitOfMeasure uomTo;
	private int multiplyRate;

	@ManyToOne()
	@JoinColumn(name = "uom_from_id")
	@Audited(targetAuditMode=RelationTargetAuditMode.NOT_AUDITED)
	public UnitOfMeasure getUomFrom() {
		return uomFrom;
	}

	public void setUomFrom(UnitOfMeasure uomFrom) {
		this.uomFrom = uomFrom;
	}

	@ManyToOne()
	@JoinColumn(name = "uom_to_id")
	@Audited(targetAuditMode=RelationTargetAuditMode.NOT_AUDITED)
	public UnitOfMeasure getUomTo() {
		return uomTo;
	}

	public void setUomTo(UnitOfMeasure uomTo) {
		this.uomTo = uomTo;
	}

	public int getMultiplyRate() {
		return multiplyRate;
	}

	public void setMultiplyRate(int multiplyRate) {
		this.multiplyRate = multiplyRate;
	}
}
