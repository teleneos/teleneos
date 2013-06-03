package org.meruvian.yama.configuration;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.meruvian.yama.persistence.DefaultPersistence;

@Entity
@Table(name = "tc_configuration")
public class Configuration extends DefaultPersistence {

	private static final long serialVersionUID = 7514437254971198489L;

	private String key;
	private String value;

	@Column(name = "key_", unique = true)
	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}
