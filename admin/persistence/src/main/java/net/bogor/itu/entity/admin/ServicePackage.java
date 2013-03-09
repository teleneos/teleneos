package net.bogor.itu.entity.admin;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.solr.analysis.LowerCaseFilterFactory;
import org.apache.solr.analysis.StandardTokenizerFactory;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.hibernate.search.annotations.Analyzer;
import org.hibernate.search.annotations.AnalyzerDef;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.NumericField;
import org.hibernate.search.annotations.TokenFilterDef;
import org.hibernate.search.annotations.TokenizerDef;
import org.meruvian.yama.persistence.DefaultPersistence;

/**
 * @author Dian Aditya
 * 
 */
@Entity
@Indexed
@Table(name = "tc_service_package")
@AnalyzerDef(name = "serviceanalyzer", tokenizer = @TokenizerDef(factory = StandardTokenizerFactory.class), filters = { @TokenFilterDef(factory = LowerCaseFilterFactory.class) })
public class ServicePackage extends DefaultPersistence {
	public enum ServiceType {
		PREPAID, POSTPAID
	}

	public enum PostpaidCalculation {
		DOWNLOAD_TRAFFIC, UPLOAD_TRAFFIC, ONLINE_TIME
	}

	private String name;
	private ServiceType type = ServiceType.PREPAID;
	private long quotaLimit = 0;
	private long timeLimit = 0;
	private boolean limitQuota = false;
	private boolean limitTime = false;
	private PostpaidCalculation calculation = PostpaidCalculation.DOWNLOAD_TRAFFIC;
	private long unitPrice = 0;
	private List<ServicePackageGroup> servicePackageGroups = new ArrayList<ServicePackageGroup>();

	@Field
	@Analyzer(definition = "serviceanalyzer")
	@Column(nullable = false)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(nullable = false)
	@Enumerated(EnumType.ORDINAL)
	public ServiceType getType() {
		return type;
	}

	public void setType(ServiceType type) {
		this.type = type;
	}

	@Field
	@NumericField
	@Column(name = "quota_limit", nullable = false)
	public long getQuotaLimit() {
		return quotaLimit;
	}

	/**
	 * 
	 * @param quotaLimit
	 *            Total traffic limit in MB
	 */
	public void setQuotaLimit(long quotaLimit) {
		this.quotaLimit = quotaLimit;
	}

	@Field
	@NumericField
	@Column(name = "time_limit", nullable = false)
	public long getTimeLimit() {
		return timeLimit;
	}

	/**
	 * 
	 * @param timeLimit
	 *            Total traffic limit in minute
	 */
	public void setTimeLimit(long timeLimit) {
		this.timeLimit = timeLimit;
	}

	@Column(name = "limit_quota", nullable = false)
	public boolean isLimitQuota() {
		return limitQuota;
	}

	public void setLimitQuota(boolean limitQuota) {
		this.limitQuota = limitQuota;
	}

	@Column(name = "limit_time", nullable = false)
	public boolean isLimitTime() {
		return limitTime;
	}

	public void setLimitTime(boolean limitTime) {
		this.limitTime = limitTime;
	}

	@Enumerated(EnumType.ORDINAL)
	public PostpaidCalculation getCalculation() {
		return calculation;
	}

	public void setCalculation(PostpaidCalculation calculation) {
		this.calculation = calculation;
	}

	@Field
	@NumericField
	@Column(name = "unit_price")
	public long getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(long unitPrice) {
		this.unitPrice = unitPrice;
	}

	@JsonIgnore
	@OneToMany(mappedBy = "servicePackage", fetch = FetchType.LAZY)
	public List<ServicePackageGroup> getServicePackageGroups() {
		return servicePackageGroups;
	}

	public void setServicePackageGroups(
			List<ServicePackageGroup> servicePackageGroups) {
		this.servicePackageGroups = servicePackageGroups;
	}

	@Transient
	public void setQuotaLimitInGigaBytes(long quotaLimit) {
		setQuotaLimit(quotaLimit * 1024);
	}

	@Transient
	public void setTimeLimitInHour(long hour) {
		setTimeLimit(hour * 60);
	}
}