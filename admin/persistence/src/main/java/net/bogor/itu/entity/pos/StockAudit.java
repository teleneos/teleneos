package net.bogor.itu.entity.pos;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.envers.Audited;
import org.hibernate.envers.RelationTargetAuditMode;
import org.meruvian.yama.persistence.DefaultPersistence;

@Entity
@Audited
@Table(name = "tc_stock_audit")
public class StockAudit extends DefaultPersistence {

	private static final long serialVersionUID = 8105224288705355162L;

	public enum Type {
		INBOUND, OUTBOUND;
	}

	private Item item;
	private Type type;
	private int quantity;
	private String description;
	private int stock;

	public StockAudit() {
		
	}

	public StockAudit(Item item, Type type, int quantity, String description,
			int stock) {
		this.item = item;
		this.type = type;
		this.quantity = quantity;
		this.description = description;
		this.stock = stock;
	}

	@ManyToOne
	@JoinColumn(name = "item_id")
	@Audited(targetAuditMode=RelationTargetAuditMode.NOT_AUDITED)
	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	@Enumerated(EnumType.ORDINAL)
	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

}
