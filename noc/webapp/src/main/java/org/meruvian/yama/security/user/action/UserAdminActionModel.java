package org.meruvian.yama.security.user.action;

import org.meruvian.yama.persistence.EntityListWrapper;
import org.meruvian.yama.security.user.BackendUser;

public class UserAdminActionModel {
	private BackendUser user = new BackendUser();
	private EntityListWrapper<BackendUser> users = new EntityListWrapper<BackendUser>();
	protected String q = "";
	protected int limit = 10;
	protected int page = 1;

	public BackendUser getUser() {
		return user;
	}

	public void setUser(BackendUser user) {
		this.user = user;
	}

	public EntityListWrapper<BackendUser> getUsers() {
		return users;
	}

	public void setUsers(EntityListWrapper<BackendUser> users) {
		this.users = users;
	}

	public String getQ() {
		return q;
	}

	public void setQ(String q) {
		this.q = q;
	}

	public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}
}