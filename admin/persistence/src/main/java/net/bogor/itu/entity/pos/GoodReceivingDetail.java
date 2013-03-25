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
@Table(name = "tc_good_receiving_detail")
public class GoodReceivingDetail extends DefaultPersistence {

	private GoodReceiving goodReceiving;
	private Item item;
	private int quantity;

	@ManyToOne
	@JoinColumn(name="good_receiving_id")
	public GoodReceiving getGoodReceiving() {
		return goodReceiving;
	}

	public void setGoodReceiving(GoodReceiving goodReceiving) {
		this.goodReceiving = goodReceiving;
	}

	@ManyToOne
	@JoinColumn(name="item_id")
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

}
