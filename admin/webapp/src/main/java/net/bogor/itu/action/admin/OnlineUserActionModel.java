package net.bogor.itu.action.admin;

import net.bogor.itu.entity.radius.Radacct;

import org.meruvian.yama.actions.DefaultActionModel;
import org.meruvian.yama.persistence.EntityListWrapper;

/**
 * @author Dian Aditya
 * 
 */
public class OnlineUserActionModel extends DefaultActionModel {
	private Radacct acct = new Radacct();
	private EntityListWrapper<Radacct> accts = new EntityListWrapper<Radacct>();

	public Radacct getAcct() {
		return acct;
	}

	public void setAcct(Radacct acct) {
		this.acct = acct;
	}

	public EntityListWrapper<Radacct> getAccts() {
		return accts;
	}

	public void setAccts(EntityListWrapper<Radacct> accts) {
		this.accts = accts;
	}
}