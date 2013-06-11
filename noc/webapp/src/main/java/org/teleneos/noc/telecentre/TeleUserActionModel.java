/**
 * 
 */
package org.teleneos.noc.telecentre;

import java.util.Calendar;
import java.util.Date;

import org.meruvian.yama.actions.DefaultActionModel;
import org.meruvian.yama.persistence.EntityListWrapper;
import org.meruvian.yama.security.user.User;

/**
 * @author Dian Aditya
 * 
 */
public class TeleUserActionModel extends DefaultActionModel {
	private EntityListWrapper<User> users = new EntityListWrapper<User>();

	public EntityListWrapper<User> getUsers() {
		return users;
	}

	public void setUsers(EntityListWrapper<User> users) {
		this.users = users;
	}

	public Date getDate(int minute) {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.MINUTE, minute);

		return calendar.getTime();
	}
}
