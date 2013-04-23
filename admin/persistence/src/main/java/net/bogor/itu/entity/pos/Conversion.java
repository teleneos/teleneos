package net.bogor.itu.entity.pos;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.meruvian.yama.persistence.DefaultPersistence;

@Entity
@Table(name = "tc_conversion")
public class Conversion extends DefaultPersistence {

	private static final long serialVersionUID = -6841917448736916762L;

	private int qty;
	private UnitOfMeasure uomFrom;
	private UnitOfMeasure uomTo;
	private int multiplyRate;

	public int getQty() {
		return qty;
	}

	public void setQty(int qty) {
		this.qty = qty;
	}

	@ManyToOne()
	@JoinColumn(name = "uom_from_id")
	public UnitOfMeasure getUomFrom() {
		return uomFrom;
	}

	public void setUomFrom(UnitOfMeasure uomFrom) {
		this.uomFrom = uomFrom;
	}

	@ManyToOne()
	@JoinColumn(name = "uom_to_id")
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
