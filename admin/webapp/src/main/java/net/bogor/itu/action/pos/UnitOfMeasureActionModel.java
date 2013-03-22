package net.bogor.itu.action.pos;

import net.bogor.itu.entity.pos.UnitOfMeasure;

import org.meruvian.yama.actions.DefaultActionModel;
import org.meruvian.yama.persistence.EntityListWrapper;

/**
 * @author Edy Setiawan
 * 
 */
public class UnitOfMeasureActionModel extends DefaultActionModel {

	private UnitOfMeasure uom = new UnitOfMeasure();
	private EntityListWrapper<UnitOfMeasure> uoms = new EntityListWrapper<UnitOfMeasure>();

	public UnitOfMeasure getUom() {
		return uom;
	}

	public void setUom(UnitOfMeasure uom) {
		this.uom = uom;
	}

	public EntityListWrapper<UnitOfMeasure> getUoms() {
		return uoms;
	}

	public void setUoms(EntityListWrapper<UnitOfMeasure> uoms) {
		this.uoms = uoms;
	}

}
