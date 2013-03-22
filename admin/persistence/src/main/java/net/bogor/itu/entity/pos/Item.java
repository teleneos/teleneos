package net.bogor.itu.entity.pos;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.meruvian.yama.persistence.DefaultPersistence;

/**
 * @author Edy Setiawan
 * 
 */
@Entity
@Table(name = "tc_item")
public class Item extends DefaultPersistence {

	private String code;
	private String name;
	private String description;
	private Long price;
	private UnitOfMeasure uom;
	private ItemType itemType;
	private ItemCategory category;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
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

	public Long getPrice() {
		return price;
	}

	public void setPrice(Long price) {
		this.price = price;
	}
	
	@ManyToOne
	@JoinColumn(name = "uom_id")
	public UnitOfMeasure getUom() {
		return uom;
	}

	public void setUom(UnitOfMeasure uom) {
		this.uom = uom;
	}

	@ManyToOne
	@JoinColumn(name = "item_type_id")
	public ItemType getItemType() {
		return itemType;
	}

	public void setItemType(ItemType itemType) {
		this.itemType = itemType;
	}

	@ManyToOne
	@JoinColumn(name = "category_id")
	public ItemCategory getCategory() {
		return category;
	}

	public void setCategory(ItemCategory category) {
		this.category = category;
	}

}