package net.bogor.itu.entity.radius;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import net.bogor.itu.entity.admin.User;

import org.meruvian.yama.persistence.DefaultPersistence;

@Entity
@Table(name = "tc_connection_history")
public class ConnectionHistory extends DefaultPersistence {

	private static final long serialVersionUID = -2124655892933568537L;

//	private Radacct radacct;
	private User user;
	private String radacct;
	
//	@OneToOne(cascade = CascadeType.DETACH)
//	@JoinColumn(referencedColumnName = "acctuniqueid", name = "raddact_uniqueid")
//	public Radacct getRadacct() {
//		return radacct;
//	}
//
//	public void setRadacct(Radacct radacct) {
//		this.radacct = radacct;
//	}

	@OneToOne
	@JoinColumn(name = "user_id")
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getRadacct() {
		return radacct;
	}

	public void setRadacct(String radacct) {
		this.radacct = radacct;
	}

}
