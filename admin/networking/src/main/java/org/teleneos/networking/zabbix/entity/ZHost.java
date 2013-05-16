/**
 * 
 */
package org.teleneos.networking.zabbix.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.annotate.JsonProperty;

/**
 * @author Dian Aditya
 * 
 */
public class ZHost implements Serializable {
	private List<Object> maintenances = new ArrayList<Object>();
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

	public List<Object> getMaintenances() {
		return maintenances;
	}

	public void setMaintenances(List<Object> maintenances) {
		this.maintenances = maintenances;
	}

	public String getHostid() {
		return hostid;
	}

	public void setHostid(String hostid) {
		this.hostid = hostid;
	}

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

	@JsonProperty("ipmi_authtype")
	public String getIpmiAuthtype() {
		return ipmiAuthtype;
	}

	public void setIpmiAuthtype(String ipmiAuthtype) {
		this.ipmiAuthtype = ipmiAuthtype;
	}

	@JsonProperty("ipmi_privilege")
	public String getIpmiPrivilege() {
		return ipmiPrivilege;
	}

	public void setIpmiPrivilege(String ipmiPrivilege) {
		this.ipmiPrivilege = ipmiPrivilege;
	}

	@JsonProperty("ipmi_username")
	public String getIpmiUsername() {
		return ipmiUsername;
	}

	public void setIpmiUsername(String ipmiUsername) {
		this.ipmiUsername = ipmiUsername;
	}

	@JsonProperty("ipmi_password")
	public String getIpmiPassword() {
		return ipmiPassword;
	}

	public void setIpmiPassword(String ipmiPassword) {
		this.ipmiPassword = ipmiPassword;
	}

	@JsonProperty("ipmi_disable_until")
	public String getIpmiDisableUntil() {
		return ipmiDisableUntil;
	}

	public void setIpmiDisableUntil(String ipmiDisableUntil) {
		this.ipmiDisableUntil = ipmiDisableUntil;
	}

	@JsonProperty("ipmi_available")
	public String getIpmiAvailable() {
		return ipmiAvailable;
	}

	public void setIpmiAvailable(String ipmiAvailable) {
		this.ipmiAvailable = ipmiAvailable;
	}

	@JsonProperty("snmp_disable_until")
	public String getSnmpDisableUntil() {
		return snmpDisableUntil;
	}

	public void setSnmpDisableUntil(String snmpDisableUntil) {
		this.snmpDisableUntil = snmpDisableUntil;
	}

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

	@JsonProperty("maintenance_status")
	public String getMaintenanceStatus() {
		return maintenanceStatus;
	}

	public void setMaintenanceStatus(String maintenanceStatus) {
		this.maintenanceStatus = maintenanceStatus;
	}

	@JsonProperty("maintenance_type")
	public String getMaintenanceType() {
		return maintenanceType;
	}

	public void setMaintenanceType(String maintenanceType) {
		this.maintenanceType = maintenanceType;
	}

	@JsonProperty("maintenance_from")
	public String getMaintenanceFrom() {
		return maintenanceFrom;
	}

	public void setMaintenanceFrom(String maintenanceFrom) {
		this.maintenanceFrom = maintenanceFrom;
	}

	@JsonProperty("ipmi_errors_from")
	public String getIpmiErrorsFrom() {
		return ipmiErrorsFrom;
	}

	public void setIpmiErrorsFrom(String ipmiErrorsFrom) {
		this.ipmiErrorsFrom = ipmiErrorsFrom;
	}

	@JsonProperty("snmp_errors_from")
	public String getSnmpErrorsFrom() {
		return snmpErrorsFrom;
	}

	public void setSnmpErrorsFrom(String snmpErrorsFrom) {
		this.snmpErrorsFrom = snmpErrorsFrom;
	}

	@JsonProperty("ipmi_error")
	public String getIpmiError() {
		return ipmiError;
	}

	public void setIpmiError(String ipmiError) {
		this.ipmiError = ipmiError;
	}

	@JsonProperty("snmp_error")
	public String getSnmpError() {
		return snmpError;
	}

	public void setSnmpError(String snmpError) {
		this.snmpError = snmpError;
	}

	@JsonProperty("jmx_disable_until")
	public String getJmxDisableUntil() {
		return jmxDisableUntil;
	}

	public void setJmxDisableUntil(String jmxDisableUntil) {
		this.jmxDisableUntil = jmxDisableUntil;
	}

	@JsonProperty("jmx_available")
	public String getJmxAvailable() {
		return jmxAvailable;
	}

	public void setJmxAvailable(String jmxAvailable) {
		this.jmxAvailable = jmxAvailable;
	}

	@JsonProperty("jmx_errors_from")
	public String getJmxErrorsFrom() {
		return jmxErrorsFrom;
	}

	public void setJmxErrorsFrom(String jmxErrorsFrom) {
		this.jmxErrorsFrom = jmxErrorsFrom;
	}

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
