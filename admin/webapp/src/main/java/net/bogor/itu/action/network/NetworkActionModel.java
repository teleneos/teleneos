/**
 * 
 */
package net.bogor.itu.action.network;

import java.util.ArrayList;
import java.util.List;

import org.meruvian.yama.actions.DefaultActionModel;
import org.teleneos.networking.zabbix.entity.ZHistoryResponse;
import org.teleneos.networking.zabbix.entity.ZHostResponse;
import org.teleneos.networking.zabbix.entity.ZItem;

/**
 * @author Dian Aditya
 * 
 */
public class NetworkActionModel extends DefaultActionModel {
	private List<ZHistoryResponse> histories = new ArrayList<ZHistoryResponse>();
	private ZHistoryResponse history = new ZHistoryResponse();
	private List<ZItem> items = new ArrayList<ZItem>();
	private ZItem item = new ZItem();
	private ZHostResponse hosts = new ZHostResponse();

	public List<ZHistoryResponse> getHistories() {
		return histories;
	}

	public void setHistories(List<ZHistoryResponse> histories) {
		this.histories = histories;
	}

	public ZHistoryResponse getHistory() {
		return history;
	}

	public void setHistory(ZHistoryResponse history) {
		this.history = history;
	}

	public List<ZItem> getItems() {
		return items;
	}

	public void setItems(List<ZItem> items) {
		this.items = items;
	}

	public ZItem getItem() {
		return item;
	}

	public void setItem(ZItem item) {
		this.item = item;
	}

	public ZHostResponse getHosts() {
		return hosts;
	}

	public void setHosts(ZHostResponse hosts) {
		this.hosts = hosts;
	}

}
