package id.co.bonet.itu.noc;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;
import org.meruvian.yama.persistence.DefaultPersistence;

@Entity
@Table(name = "tc_telecentre")
@JsonIgnoreProperties(ignoreUnknown=true)
public class Telecentre extends DefaultPersistence {

	private static final long serialVersionUID = 3159531161800430146L;
	private int telecentre_id;
	private String name;
	private BigDecimal latitude;
	private BigDecimal longitude;
	
	private Set<TelecentreComputer> telecentreComputers = new HashSet<TelecentreComputer>(
			0);
	
	public int getTelecentre_id() {
		return telecentre_id;
	}
	
	@JsonProperty("id")
	public void setTelecentre_id(int telecentre_id) {
		this.telecentre_id = telecentre_id;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "telecentre")
	public Set<TelecentreComputer> getTelecentreComputers() {
		return telecentreComputers;
	}

	@JsonProperty(value = "result")
	public void setTelecentreComputers(
			Set<TelecentreComputer> telecentreComputers) {
		this.telecentreComputers = telecentreComputers;
	}
	
	@Column(precision=20, scale=10)
	public BigDecimal getLatitude() {
		return latitude;
	}

	public void setLatitude(BigDecimal latitude) {
		this.latitude = latitude;
	}

	@Column(precision = 20, scale = 10)
	public BigDecimal getLongitude() {
		return longitude;
	}

	public void setLongitude(BigDecimal longitude) {
		this.longitude = longitude;
	}

}
