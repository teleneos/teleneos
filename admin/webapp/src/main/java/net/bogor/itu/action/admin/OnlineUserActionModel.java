package net.bogor.itu.action.admin;

import net.bogor.itu.entity.admin.User;
import net.bogor.itu.entity.radius.Radacct;
import net.bogor.itu.entity.radius.UserPackage;

import org.meruvian.yama.actions.DefaultActionModel;
import org.meruvian.yama.persistence.EntityListWrapper;

/**
 * @author Dian Aditya
 * 
 */
public class OnlineUserActionModel extends DefaultActionModel {
	private Radacct acct = new Radacct();
	private User user = new User();
	private UserPackage userPackage = new UserPackage();
	private EntityListWrapper<Radacct> accts = new EntityListWrapper<Radacct>();
	private EntityListWrapper<Object[]> listacc = new EntityListWrapper<Object[]>();
	private EntityListWrapper<UserPackage> userPackages = new EntityListWrapper<UserPackage>();

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

	public UserPackage getUserPackage() {
		return userPackage;
	}

	public void setUserPackage(UserPackage userPackage) {
		this.userPackage = userPackage;
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

	public EntityListWrapper<Object[]> getListacc() {
		return listacc;
	}

	public void setListacc(EntityListWrapper<Object[]> listacc) {
		this.listacc = listacc;
	}

	public EntityListWrapper<UserPackage> getUserPackages() {
		return userPackages;
	}

	public void setUserPackages(EntityListWrapper<UserPackage> userPackages) {
		this.userPackages = userPackages;
	}

}