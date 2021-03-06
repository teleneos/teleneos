/**
 * 
 */
package org.teleneos.log.radius.accounting;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.meruvian.yama.persistence.NocDefaultPersistence;

/**
 * @author Dian Aditya
 * 
 */
@Entity
@Table(name = "noc_radius_accounting")
public class RadiusAccounting extends NocDefaultPersistence {
	private Long radacctid;
	private String acctsessionid;
	private String acctuniqueid;
	private String username;
	private String groupname;
	private String realm;
	private String nasipaddress;
	private String nasportid;
	private String nasporttype;
	private Date acctstarttime;
	private Date acctstoptime;
	private Integer acctsessiontime;
	private String acctauthentic;
	private String connectinfoStart;
	private String connectinfoStop;
	private Long acctinputoctets;
	private Long acctoutputoctets;
	private String calledstationid;
	private String callingstationid;
	private String acctterminatecause;
	private String servicetype;
	private String framedprotocol;
	private String framedipaddress;
	private Integer acctstartdelay;
	private Integer acctstopdelay;
	private String xascendsessionsvrkey;
	private Date acctTime;
	private Integer srvId;

	public RadiusAccounting() {
	}

	public RadiusAccounting(String acctsessionid, String acctuniqueid,
			String username, String groupname, String nasipaddress,
			String calledstationid, String callingstationid,
			String acctterminatecause, String framedipaddress) {
		this.acctsessionid = acctsessionid;
		this.acctuniqueid = acctuniqueid;
		this.username = username;
		this.groupname = groupname;
		this.nasipaddress = nasipaddress;
		this.calledstationid = calledstationid;
		this.callingstationid = callingstationid;
		this.acctterminatecause = acctterminatecause;
		this.framedipaddress = framedipaddress;
	}

	public RadiusAccounting(String acctsessionid, String acctuniqueid,
			String username, String groupname, String realm,
			String nasipaddress, String nasportid, String nasporttype,
			Date acctstarttime, Date acctstoptime, Integer acctsessiontime,
			String acctauthentic, String connectinfoStart,
			String connectinfoStop, Long acctinputoctets,
			Long acctoutputoctets, String calledstationid,
			String callingstationid, String acctterminatecause,
			String servicetype, String framedprotocol, String framedipaddress,
			Integer acctstartdelay, Integer acctstopdelay,
			String xascendsessionsvrkey, Date acctTime, Integer srvId) {
		this.acctsessionid = acctsessionid;
		this.acctuniqueid = acctuniqueid;
		this.username = username;
		this.groupname = groupname;
		this.realm = realm;
		this.nasipaddress = nasipaddress;
		this.nasportid = nasportid;
		this.nasporttype = nasporttype;
		this.acctstarttime = acctstarttime;
		this.acctstoptime = acctstoptime;
		this.acctsessiontime = acctsessiontime;
		this.acctauthentic = acctauthentic;
		this.connectinfoStart = connectinfoStart;
		this.connectinfoStop = connectinfoStop;
		this.acctinputoctets = acctinputoctets;
		this.acctoutputoctets = acctoutputoctets;
		this.calledstationid = calledstationid;
		this.callingstationid = callingstationid;
		this.acctterminatecause = acctterminatecause;
		this.servicetype = servicetype;
		this.framedprotocol = framedprotocol;
		this.framedipaddress = framedipaddress;
		this.acctstartdelay = acctstartdelay;
		this.acctstopdelay = acctstopdelay;
		this.xascendsessionsvrkey = xascendsessionsvrkey;
		this.acctTime = acctTime;
		this.srvId = srvId;
	}

	@Column(name = "radacctid", nullable = false)
	public Long getRadacctid() {
		return this.radacctid;
	}

	public void setRadacctid(Long radacctid) {
		this.radacctid = radacctid;
	}

	@Column(name = "acctsessionid", nullable = false, length = 64)
	public String getAcctsessionid() {
		return this.acctsessionid;
	}

	public void setAcctsessionid(String acctsessionid) {
		this.acctsessionid = acctsessionid;
	}

	@Column(name = "acctuniqueid", nullable = false, length = 32)
	public String getAcctuniqueid() {
		return this.acctuniqueid;
	}

	public void setAcctuniqueid(String acctuniqueid) {
		this.acctuniqueid = acctuniqueid;
	}

	@Column(name = "username", nullable = false, length = 64)
	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Column(name = "groupname", nullable = false, length = 64)
	public String getGroupname() {
		return this.groupname;
	}

	public void setGroupname(String groupname) {
		this.groupname = groupname;
	}

	@Column(name = "realm", length = 64)
	public String getRealm() {
		return this.realm;
	}

	public void setRealm(String realm) {
		this.realm = realm;
	}

	@Column(name = "nasipaddress", nullable = false, length = 15)
	public String getNasipaddress() {
		return this.nasipaddress;
	}

	public void setNasipaddress(String nasipaddress) {
		this.nasipaddress = nasipaddress;
	}

	@Column(name = "nasportid", length = 15)
	public String getNasportid() {
		return this.nasportid;
	}

	public void setNasportid(String nasportid) {
		this.nasportid = nasportid;
	}

	@Column(name = "nasporttype", length = 32)
	public String getNasporttype() {
		return this.nasporttype;
	}

	public void setNasporttype(String nasporttype) {
		this.nasporttype = nasporttype;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "acctstarttime", length = 19)
	public Date getAcctstarttime() {
		return this.acctstarttime;
	}

	public void setAcctstarttime(Date acctstarttime) {
		this.acctstarttime = acctstarttime;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "acctstoptime", length = 19)
	public Date getAcctstoptime() {
		return this.acctstoptime;
	}

	public void setAcctstoptime(Date acctstoptime) {
		this.acctstoptime = acctstoptime;
	}

	@Column(name = "acctsessiontime")
	public Integer getAcctsessiontime() {
		return this.acctsessiontime;
	}

	public void setAcctsessiontime(Integer acctsessiontime) {
		this.acctsessiontime = acctsessiontime;
	}

	@Column(name = "acctauthentic", length = 32)
	public String getAcctauthentic() {
		return this.acctauthentic;
	}

	public void setAcctauthentic(String acctauthentic) {
		this.acctauthentic = acctauthentic;
	}

	@Column(name = "connectinfo_start", length = 50)
	public String getConnectinfoStart() {
		return this.connectinfoStart;
	}

	public void setConnectinfoStart(String connectinfoStart) {
		this.connectinfoStart = connectinfoStart;
	}

	@Column(name = "connectinfo_stop", length = 50)
	public String getConnectinfoStop() {
		return this.connectinfoStop;
	}

	public void setConnectinfoStop(String connectinfoStop) {
		this.connectinfoStop = connectinfoStop;
	}

	@Column(name = "acctinputoctets")
	public Long getAcctinputoctets() {
		return this.acctinputoctets;
	}

	public void setAcctinputoctets(Long acctinputoctets) {
		this.acctinputoctets = acctinputoctets;
	}

	@Column(name = "acctoutputoctets")
	public Long getAcctoutputoctets() {
		return this.acctoutputoctets;
	}

	public void setAcctoutputoctets(Long acctoutputoctets) {
		this.acctoutputoctets = acctoutputoctets;
	}

	@Column(name = "calledstationid", nullable = false, length = 50)
	public String getCalledstationid() {
		return this.calledstationid;
	}

	public void setCalledstationid(String calledstationid) {
		this.calledstationid = calledstationid;
	}

	@Column(name = "callingstationid", nullable = false, length = 50)
	public String getCallingstationid() {
		return this.callingstationid;
	}

	public void setCallingstationid(String callingstationid) {
		this.callingstationid = callingstationid;
	}

	@Column(name = "acctterminatecause", nullable = false, length = 32)
	public String getAcctterminatecause() {
		return this.acctterminatecause;
	}

	public void setAcctterminatecause(String acctterminatecause) {
		this.acctterminatecause = acctterminatecause;
	}

	@Column(name = "servicetype", length = 32)
	public String getServicetype() {
		return this.servicetype;
	}

	public void setServicetype(String servicetype) {
		this.servicetype = servicetype;
	}

	@Column(name = "framedprotocol", length = 32)
	public String getFramedprotocol() {
		return this.framedprotocol;
	}

	public void setFramedprotocol(String framedprotocol) {
		this.framedprotocol = framedprotocol;
	}

	@Column(name = "framedipaddress", nullable = false, length = 15)
	public String getFramedipaddress() {
		return this.framedipaddress;
	}

	public void setFramedipaddress(String framedipaddress) {
		this.framedipaddress = framedipaddress;
	}

	@Column(name = "acctstartdelay")
	public Integer getAcctstartdelay() {
		return this.acctstartdelay;
	}

	public void setAcctstartdelay(Integer acctstartdelay) {
		this.acctstartdelay = acctstartdelay;
	}

	@Column(name = "acctstopdelay")
	public Integer getAcctstopdelay() {
		return this.acctstopdelay;
	}

	public void setAcctstopdelay(Integer acctstopdelay) {
		this.acctstopdelay = acctstopdelay;
	}

	@Column(name = "xascendsessionsvrkey", length = 10)
	public String getXascendsessionsvrkey() {
		return this.xascendsessionsvrkey;
	}

	public void setXascendsessionsvrkey(String xascendsessionsvrkey) {
		this.xascendsessionsvrkey = xascendsessionsvrkey;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "_AcctTime", length = 19)
	public Date getAcctTime() {
		return this.acctTime;
	}

	public void setAcctTime(Date acctTime) {
		this.acctTime = acctTime;
	}

	@Column(name = "_SrvId")
	public Integer getSrvId() {
		return this.srvId;
	}

	public void setSrvId(Integer srvId) {
		this.srvId = srvId;
	}
}
