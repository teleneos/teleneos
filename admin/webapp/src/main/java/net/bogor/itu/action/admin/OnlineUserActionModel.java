package net.bogor.itu.action.admin;

import net.bogor.itu.entity.admin.User;
import net.bogor.itu.entity.radius.Radacct;

import org.meruvian.yama.actions.DefaultActionModel;
import org.meruvian.yama.persistence.EntityListWrapper;

/**
 * @author Dian Aditya
 * 
 */
public class OnlineUserActionModel extends DefaultActionModel {
	private Radacct acct = new Radacct();
	private User user = new User();
	private EntityListWrapper<Radacct> accts = new EntityListWrapper<Radacct>();
	private Object[] statistic;
	private String uid;
	
	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public Radacct getAcct() {
		return acct;
	}

	public void setAcct(Radacct acct) {
		this.acct = acct;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public EntityListWrapper<Radacct> getAccts() {
		return accts;
	}

	public void setAccts(EntityListWrapper<Radacct> accts) {
		this.accts = accts;
	}

	public Object[] getStatistic() {
		return statistic;
	}

	public void setStatistic(Object[] objects) {
		this.statistic = objects;
	}
}