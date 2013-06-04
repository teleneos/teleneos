/**
 * 
 */
package org.teleneos.log.network.host;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.meruvian.yama.persistence.NocDefaultPersistence;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author Dian Aditya
 * 
 */
@Entity
@Table(name = "noc_network_host")
public class Host extends NocDefaultPersistence {
	private List<String> maintenances = new ArrayList<String>();
	private String hostid;
	private String proxyHostid;
	private String host;
	private String status;
	private String disableUntil;
	private String error;
	private String available;
	private String errorsFrom;
	private String lastaccess;
	private String ipmiAuthtype;
	private String ipmiPrivilege;
	private String ipmiUsername;
	private String ipmiPassword;
	private String ipmiDisableUntil;
	private String ipmiAvailable;
	private String snmpDisableUntil;
	private String snmpAvailable;
	private String maintenanceid;
	private String maintenanceStatus;
	private String maintenanceType;
	private String maintenanceFrom;
	private String ipmiErrorsFrom;
	private String snmpErrorsFrom;
	private String ipmiError;
	private String snmpError;
	private String jmxDisableUntil;
	private String jmxAvailable;
	private String jmxErrorsFrom;
	private String jmxError;
	private String name;

	@Transient
	public List<String> getMaintenances() {
		return maintenances;
	}

	public void setMaintenances(List<String> maintenances) {
		this.maintenances = maintenances;
	}

	public String getHostid() {
		return hostid;
	}

	public void setHostid(String hostid) {
		this.hostid = hostid;
	}

	@Column(name = "proxy_hostid")
	@JsonProperty("proxy_hostid")
	public String getProxyHostid() {
		return proxyHostid;
	}

	public void setProxyHostid(String proxyHostid) {
		this.proxyHostid = proxyHostid;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Column(name = "disable_until")
	@JsonProperty("disable_until")
	public String getDisableUntil() {
		return disableUntil;
	}

	public void setDisableUntil(String disableUntil) {
		this.disableUntil = disableUntil;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public String getAvailable() {
		return available;
	}

	public void setAvailable(String available) {
		this.available = available;
	}

	@Column(name = "errors_from")
	@JsonProperty("errors_from")
	public String getErrorsFrom() {
		return errorsFrom;
	}

	public void setErrorsFrom(String errorsFrom) {
		this.errorsFrom = errorsFrom;
	}

	public String getLastaccess() {
		return lastaccess;
	}

	public void setLastaccess(String lastaccess) {
		this.lastaccess = lastaccess;
	}

	@Column(name = "ipmi_authtype")
	@JsonProperty("ipmi_authtype")
	public String getIpmiAuthtype() {
		return ipmiAuthtype;
	}

	public void setIpmiAuthtype(String ipmiAuthtype) {
		this.ipmiAuthtype = ipmiAuthtype;
	}

	@Column(name = "ipmi_privilege")
	@JsonProperty("ipmi_privilege")
	public String getIpmiPrivilege() {
		return ipmiPrivilege;
	}

	public void setIpmiPrivilege(String ipmiPrivilege) {
		this.ipmiPrivilege = ipmiPrivilege;
	}

	@Column(name = "ipmi_username")
	@JsonProperty("ipmi_username")
	public String getIpmiUsername() {
		return ipmiUsername;
	}

	public void setIpmiUsername(String ipmiUsername) {
		this.ipmiUsername = ipmiUsername;
	}

	@Column(name = "ipmi_password")
	@JsonProperty("ipmi_password")
	public String getIpmiPassword() {
		return ipmiPassword;
	}

	public void setIpmiPassword(String ipmiPassword) {
		this.ipmiPassword = ipmiPassword;
	}

	@Column(name = "ipmi_disable_until")
	@JsonProperty("ipmi_disable_until")
	public String getIpmiDisableUntil() {
		return ipmiDisableUntil;
	}

	public void setIpmiDisableUntil(String ipmiDisableUntil) {
		this.ipmiDisableUntil = ipmiDisableUntil;
	}

	@Column(name = "ipmi_available")
	@JsonProperty("ipmi_available")
	public String getIpmiAvailable() {
		return ipmiAvailable;
	}

	public void setIpmiAvailable(String ipmiAvailable) {
		this.ipmiAvailable = ipmiAvailable;
	}

	@Column(name = "snmp_disable_until")
	@JsonProperty("snmp_disable_until")
	public String getSnmpDisableUntil() {
		return snmpDisableUntil;
	}

	public void setSnmpDisableUntil(String snmpDisableUntil) {
		this.snmpDisableUntil = snmpDisableUntil;
	}

	@Column(name = "snmp_available")
	@JsonProperty("snmp_available")
	public String getSnmpAvailable() {
		return snmpAvailable;
	}

	public void setSnmpAvailable(String snmpAvailable) {
		this.snmpAvailable = snmpAvailable;
	}

	public String getMaintenanceid() {
		return maintenanceid;
	}

	public void setMaintenanceid(String maintenanceid) {
		this.maintenanceid = maintenanceid;
	}

	@Column(name = "maintenance_status")
	@JsonProperty("maintenance_status")
	public String getMaintenanceStatus() {
		return maintenanceStatus;
	}

	public void setMaintenanceStatus(String maintenanceStatus) {
		this.maintenanceStatus = maintenanceStatus;
	}

	@Column(name = "maintenance_type")
	@JsonProperty("maintenance_type")
	public String getMaintenanceType() {
		return maintenanceType;
	}

	public void setMaintenanceType(String maintenanceType) {
		this.maintenanceType = maintenanceType;
	}

	@Column(name = "maintenance_from")
	@JsonProperty("maintenance_from")
	public String getMaintenanceFrom() {
		return maintenanceFrom;
	}

	public void setMaintenanceFrom(String maintenanceFrom) {
		this.maintenanceFrom = maintenanceFrom;
	}

	@Column(name = "ipmi_errors_from")
	@JsonProperty("ipmi_errors_from")
	public String getIpmiErrorsFrom() {
		return ipmiErrorsFrom;
	}

	public void setIpmiErrorsFrom(String ipmiErrorsFrom) {
		this.ipmiErrorsFrom = ipmiErrorsFrom;
	}

	@Column(name = "snmp_errors_from")
	@JsonProperty("snmp_errors_from")
	public String getSnmpErrorsFrom() {
		return snmpErrorsFrom;
	}

	public void setSnmpErrorsFrom(String snmpErrorsFrom) {
		this.snmpErrorsFrom = snmpErrorsFrom;
	}

	@Column(name = "ipmi_error")
	@JsonProperty("ipmi_error")
	public String getIpmiError() {
		return ipmiError;
	}

	public void setIpmiError(String ipmiError) {
		this.ipmiError = ipmiError;
	}

	@Column(name = "snmp_error")
	@JsonProperty("snmp_error")
	public String getSnmpError() {
		return snmpError;
	}

	public void setSnmpError(String snmpError) {
		this.snmpError = snmpError;
	}

	@Column(name = "jmx_disable_until")
	@JsonProperty("jmx_disable_until")
	public String getJmxDisableUntil() {
		return jmxDisableUntil;
	}

	public void setJmxDisableUntil(String jmxDisableUntil) {
		this.jmxDisableUntil = jmxDisableUntil;
	}

	@Column(name = "jmx_available")
	@JsonProperty("jmx_available")
	public String getJmxAvailable() {
		return jmxAvailable;
	}

	public void setJmxAvailable(String jmxAvailable) {
		this.jmxAvailable = jmxAvailable;
	}

	@Column(name = "jmx_errors_from")
	@JsonProperty("jmx_errors_from")
	public String getJmxErrorsFrom() {
		return jmxErrorsFrom;
	}

	public void setJmxErrorsFrom(String jmxErrorsFrom) {
		this.jmxErrorsFrom = jmxErrorsFrom;
	}

	@Column(name = "jmx_error")
	@JsonProperty("jmx_error")
	public String getJmxError() {
		return jmxError;
	}

	public void setJmxError(String jmxError) {
		this.jmxError = jmxError;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
