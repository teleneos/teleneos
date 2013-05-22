package org.teleneos.pos.item;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.meruvian.yama.persistence.DefaultPersistence;
import org.teleneos.pos.uom.UnitOfMeasure;

/**
 * @author Edy Setiawan
 * 
 */
@Entity
@Table(name = "tc_item_type")
public class ItemType extends DefaultPersistence {

	private String name;
	private int unit;
	private UnitOfMeasure uom;
	private String description;

	public int getUnit() {
		return unit;
	}

	public void setUnit(int unit) {
		this.unit = unit;
	}

	@ManyToOne()
	@JoinColumn(name = "uom_id")
	public UnitOfMeasure getUom() {
		return uom;
	}

	public void setUom(UnitOfMeasure uom) {
		this.uom = uom;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
