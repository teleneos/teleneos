package org.teleneos.pos.conversion;

import org.meruvian.yama.actions.DefaultActionModel;
import org.meruvian.yama.persistence.EntityListWrapper;
import org.teleneos.pos.uom.UnitOfMeasure;

/**
 * @author Edy Setiawan
 * 
 */
public class ConversionActionModel extends DefaultActionModel {

	private Conversion conversion = new Conversion();
	private EntityListWrapper<Conversion> conversions = new EntityListWrapper<Conversion>();
	private EntityListWrapper<UnitOfMeasure> uoms = new EntityListWrapper<UnitOfMeasure>();

	public Conversion getConversion() {
		return conversion;
	}

	public void setConversion(Conversion conversion) {
		this.conversion = conversion;
	}

	public EntityListWrapper<Conversion> getConversions() {
		return conversions;
	}

	public void setConversions(EntityListWrapper<Conversion> conversions) {
		this.conversions = conversions;
	}

	public EntityListWrapper<UnitOfMeasure> getUoms() {
		return uoms;
	}

	public void setUoms(EntityListWrapper<UnitOfMeasure> uoms) {
		this.uoms = uoms;
	}

}
