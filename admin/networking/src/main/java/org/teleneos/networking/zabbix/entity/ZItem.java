/**
 * 
 */
package org.teleneos.networking.zabbix.entity;

import java.io.Serializable;

import org.codehaus.jackson.annotate.JsonProperty;

/**
 * @author Dian Aditya
 * 
 */
public class ZItem implements Serializable {
	private String itemid;
	private String type;
	private String snmpCommunity;
	private String snmpOid;
	private String hostid;
	private String name;
	private String key;
	private String delay;
	private String history;
	private String trends;
	private String lastvalue;
	private String lastclock;
	private String prevvalue;
	private String state;
	private String status;
	private String valueType;
	private String trapperHosts;
	private String units;
	private String multiplier;
	private String delta;
	private String prevorgvalue;
	private String snmpv3Securityname;
	private String snmpv3Securitylevel;
	private String snmpv3Authpassphrase;
	private String snmpv3Privpassphrase;
	private String formula;
	private String error;
	private String lastlogsize;
	private String logtimefmt;
	private String templateid;
	private String valuemapid;
	private String delayFlex;
	private String params;
	private String ipmiSensor;
	private String dataType;
	private String authtype;
	private String username;
	private String password;
	private String publickey;
	private String privatekey;
	private String mtime;
	private String lastns;
	private String flags;
	private String filter;
	private String interfaceid;
	private String port;
	private String description;
	private String inventoryLink;
	private String lifetime;

	public String getItemid() {
		return itemid;
	}

	public void setItemid(String itemid) {
		this.itemid = itemid;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@JsonProperty("snmp_community")
	public String getSnmpCommunity() {
		return snmpCommunity;
	}

	public void setSnmpCommunity(String snmpCommunity) {
		this.snmpCommunity = snmpCommunity;
	}

	@JsonProperty("snmp_oid")
	public String getSnmpOid() {
		return snmpOid;
	}

	public void setSnmpOid(String snmpOid) {
		this.snmpOid = snmpOid;
	}

	public String getHostid() {
		return hostid;
	}

	public void setHostid(String hostid) {
		this.hostid = hostid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@JsonProperty("key_")
	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getDelay() {
		return delay;
	}

	public void setDelay(String delay) {
		this.delay = delay;
	}

	public String getHistory() {
		return history;
	}

	public void setHistory(String history) {
		this.history = history;
	}

	public String getTrends() {
		return trends;
	}

	public void setTrends(String trends) {
		this.trends = trends;
	}

	public String getLastvalue() {
		return lastvalue;
	}

	public void setLastvalue(String lastvalue) {
		this.lastvalue = lastvalue;
	}

	public String getLastclock() {
		return lastclock;
	}

	public void setLastclock(String lastclock) {
		this.lastclock = lastclock;
	}

	public String getPrevvalue() {
		return prevvalue;
	}

	public void setPrevvalue(String prevvalue) {
		this.prevvalue = prevvalue;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@JsonProperty("value_type")
	public String getValueType() {
		return valueType;
	}

	public void setValueType(String valueType) {
		this.valueType = valueType;
	}

	@JsonProperty("trapper_hosts")
	public String getTrapperHosts() {
		return trapperHosts;
	}

	public void setTrapperHosts(String trapperHosts) {
		this.trapperHosts = trapperHosts;
	}

	public String getUnits() {
		return units;
	}

	public void setUnits(String units) {
		this.units = units;
	}

	public String getMultiplier() {
		return multiplier;
	}

	public void setMultiplier(String multiplier) {
		this.multiplier = multiplier;
	}

	public String getDelta() {
		return delta;
	}

	public void setDelta(String delta) {
		this.delta = delta;
	}

	public String getPrevorgvalue() {
		return prevorgvalue;
	}

	public void setPrevorgvalue(String prevorgvalue) {
		this.prevorgvalue = prevorgvalue;
	}

	@JsonProperty("snmpv3_securityname")
	public String getSnmpv3Securityname() {
		return snmpv3Securityname;
	}

	public void setSnmpv3Securityname(String snmpv3Securityname) {
		this.snmpv3Securityname = snmpv3Securityname;
	}

	@JsonProperty("snmpv3_securitylevel")
	public String getSnmpv3Securitylevel() {
		return snmpv3Securitylevel;
	}

	public void setSnmpv3Securitylevel(String snmpv3Securitylevel) {
		this.snmpv3Securitylevel = snmpv3Securitylevel;
	}

	@JsonProperty("snmpv3_authpassphrase")
	public String getSnmpv3Authpassphrase() {
		return snmpv3Authpassphrase;
	}

	public void setSnmpv3Authpassphrase(String snmpv3Authpassphrase) {
		this.snmpv3Authpassphrase = snmpv3Authpassphrase;
	}

	@JsonProperty("snmpv3_privpassphrase")
	public String getSnmpv3Privpassphrase() {
		return snmpv3Privpassphrase;
	}

	public void setSnmpv3Privpassphrase(String snmpv3Privpassphrase) {
		this.snmpv3Privpassphrase = snmpv3Privpassphrase;
	}

	public String getFormula() {
		return formula;
	}

	public void setFormula(String formula) {
		this.formula = formula;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public String getLastlogsize() {
		return lastlogsize;
	}

	public void setLastlogsize(String lastlogsize) {
		this.lastlogsize = lastlogsize;
	}

	public String getLogtimefmt() {
		return logtimefmt;
	}

	public void setLogtimefmt(String logtimefmt) {
		this.logtimefmt = logtimefmt;
	}

	public String getTemplateid() {
		return templateid;
	}

	public void setTemplateid(String templateid) {
		this.templateid = templateid;
	}

	public String getValuemapid() {
		return valuemapid;
	}

	public void setValuemapid(String valuemapid) {
		this.valuemapid = valuemapid;
	}

	@JsonProperty("delay_flex")
	public String getDelayFlex() {
		return delayFlex;
	}

	public void setDelayFlex(String delayFlex) {
		this.delayFlex = delayFlex;
	}

	public String getParams() {
		return params;
	}

	public void setParams(String params) {
		this.params = params;
	}

	@JsonProperty("ipmi_sensor")
	public String getIpmiSensor() {
		return ipmiSensor;
	}

	public void setIpmiSensor(String ipmiSensor) {
		this.ipmiSensor = ipmiSensor;
	}

	@JsonProperty("data_type")
	public String getDataType() {
		return dataType;
	}

	public void setDataType(String dataType) {
		this.dataType = dataType;
	}

	public String getAuthtype() {
		return authtype;
	}

	public void setAuthtype(String authtype) {
		this.authtype = authtype;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPublickey() {
		return publickey;
	}

	public void setPublickey(String publickey) {
		this.publickey = publickey;
	}

	public String getPrivatekey() {
		return privatekey;
	}

	public void setPrivatekey(String privatekey) {
		this.privatekey = privatekey;
	}

	public String getMtime() {
		return mtime;
	}

	public void setMtime(String mtime) {
		this.mtime = mtime;
	}

	public String getLastns() {
		return lastns;
	}

	public void setLastns(String lastns) {
		this.lastns = lastns;
	}

	public String getFlags() {
		return flags;
	}

	public void setFlags(String flags) {
		this.flags = flags;
	}

	public String getFilter() {
		return filter;
	}

	public void setFilter(String filter) {
		this.filter = filter;
	}

	public String getInterfaceid() {
		return interfaceid;
	}

	public void setInterfaceid(String interfaceid) {
		this.interfaceid = interfaceid;
	}

	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@JsonProperty("inventory_link")
	public String getInventoryLink() {
		return inventoryLink;
	}

	public void setInventoryLink(String inventoryLink) {
		this.inventoryLink = inventoryLink;
	}

	public String getLifetime() {
		return lifetime;
	}

	public void setLifetime(String lifetime) {
		this.lifetime = lifetime;
	}

}
