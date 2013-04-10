package id.co.bonet.itu.noc;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.meruvian.yama.persistence.DefaultPersistence;

@Entity
@Table(name = "tc_computer")
@JsonIgnoreProperties(ignoreUnknown=true)
public class TelecentreComputer extends DefaultPersistence {

	private static final long serialVersionUID = -2029715796344806291L;

	private Telecentre telecentre;

	private String hostid;
	private String proxy_hostid;
	private String host;
	private int status;
	private long disable_until;
	private String error;
	private int available;
	private long errors_from;
	private long lastaccess;
	private int ipmi_authtype;
	private int ipmi_privilege;
	private String ipmi_username;
	private String ipmi_password;
	private long ipmi_disable_until;
	private int ipmi_available;
	private long snmp_disable_until;
	private int snmp_available;
	private String maintenanceid;
	private int maintenance_status;
	private int maintenance_type;
	private long maintenance_from;
	private long ipmi_errors_from;
	private long snmp_errors_from;
	private String ipmi_error;
	private String snmp_error;
	private long jmx_disable_until;
	private int jmx_available;
	private long jmx_errors_from;
	private String jmx_error;
	private String name;
	
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "telecentre_id")
	public Telecentre getTelecentre() {
		return telecentre;
	}

	public void setTelecentre(Telecentre telecentre) {
		this.telecentre = telecentre;
	}

	public String getHostid() {
		return hostid;
	}

	public void setHostid(String hostid) {
		this.hostid = hostid;
	}

	public String getProxy_hostid() {
		return proxy_hostid;
	}

	public void setProxy_hostid(String proxy_hostid) {
		this.proxy_hostid = proxy_hostid;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public long getDisable_until() {
		return disable_until;
	}

	public void setDisable_until(long disable_until) {
		this.disable_until = disable_until;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public int getAvailable() {
		return available;
	}

	public void setAvailable(int available) {
		this.available = available;
	}

	public long getErrors_from() {
		return errors_from;
	}

	public void setErrors_from(long errors_from) {
		this.errors_from = errors_from;
	}

	public long getLastaccess() {
		return lastaccess;
	}

	public void setLastaccess(long lastaccess) {
		this.lastaccess = lastaccess;
	}

	public int getIpmi_authtype() {
		return ipmi_authtype;
	}

	public void setIpmi_authtype(int ipmi_authtype) {
		this.ipmi_authtype = ipmi_authtype;
	}

	public int getIpmi_privilege() {
		return ipmi_privilege;
	}

	public void setIpmi_privilege(int ipmi_privilege) {
		this.ipmi_privilege = ipmi_privilege;
	}

	public String getIpmi_username() {
		return ipmi_username;
	}

	public void setIpmi_username(String ipmi_username) {
		this.ipmi_username = ipmi_username;
	}

	public String getIpmi_password() {
		return ipmi_password;
	}

	public void setIpmi_password(String ipmi_password) {
		this.ipmi_password = ipmi_password;
	}

	public long getIpmi_disable_until() {
		return ipmi_disable_until;
	}

	public void setIpmi_disable_until(long ipmi_disable_until) {
		this.ipmi_disable_until = ipmi_disable_until;
	}

	public int getIpmi_available() {
		return ipmi_available;
	}

	public void setIpmi_available(int ipmi_available) {
		this.ipmi_available = ipmi_available;
	}

	public long getSnmp_disable_until() {
		return snmp_disable_until;
	}

	public void setSnmp_disable_until(long snmp_disable_until) {
		this.snmp_disable_until = snmp_disable_until;
	}

	public int getSnmp_available() {
		return snmp_available;
	}

	public void setSnmp_available(int snmp_available) {
		this.snmp_available = snmp_available;
	}

	public String getMaintenanceid() {
		return maintenanceid;
	}

	public void setMaintenanceid(String maintenanceid) {
		this.maintenanceid = maintenanceid;
	}

	public int getMaintenance_status() {
		return maintenance_status;
	}

	public void setMaintenance_status(int maintenance_status) {
		this.maintenance_status = maintenance_status;
	}

	public int getMaintenance_type() {
		return maintenance_type;
	}

	public void setMaintenance_type(int maintenance_type) {
		this.maintenance_type = maintenance_type;
	}

	public long getMaintenance_from() {
		return maintenance_from;
	}

	public void setMaintenance_from(long maintenance_from) {
		this.maintenance_from = maintenance_from;
	}

	public long getIpmi_errors_from() {
		return ipmi_errors_from;
	}

	public void setIpmi_errors_from(long ipmi_errors_from) {
		this.ipmi_errors_from = ipmi_errors_from;
	}

	public long getSnmp_errors_from() {
		return snmp_errors_from;
	}

	public void setSnmp_errors_from(long snmp_errors_from) {
		this.snmp_errors_from = snmp_errors_from;
	}

	public String getIpmi_error() {
		return ipmi_error;
	}

	public void setIpmi_error(String ipmi_error) {
		this.ipmi_error = ipmi_error;
	}

	public String getSnmp_error() {
		return snmp_error;
	}

	public void setSnmp_error(String snmp_error) {
		this.snmp_error = snmp_error;
	}

	public long getJmx_disable_until() {
		return jmx_disable_until;
	}

	public void setJmx_disable_until(long jmx_disable_until) {
		this.jmx_disable_until = jmx_disable_until;
	}

	public int getJmx_available() {
		return jmx_available;
	}

	public void setJmx_available(int jmx_available) {
		this.jmx_available = jmx_available;
	}

	public long getJmx_errors_from() {
		return jmx_errors_from;
	}

	public void setJmx_errors_from(long jmx_errors_from) {
		this.jmx_errors_from = jmx_errors_from;
	}

	public String getJmx_error() {
		return jmx_error;
	}

	public void setJmx_error(String jmx_error) {
		this.jmx_error = jmx_error;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
