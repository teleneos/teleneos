/**
 * Copyright 2012 BlueOxygen Technology
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0 
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.meruvian.yama.security.user;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.hibernate.search.annotations.Analyzer;
import org.hibernate.search.annotations.Field;

/**
 * @author Dian Aditya
 * 
 */
// @Entity
// @Indexed
// @Table(name = "yama_backend_user")
// @AnalyzerDef(name = "customanalyzer", tokenizer = @TokenizerDef(factory =
// StandardTokenizerFactory.class), filters = { @TokenFilterDef(factory =
// LowerCaseFilterFactory.class) })
@Embeddable
public class BackendUser {
	private String username;
	private String password;
	private String email;
	private String website;
	private String role;

	@Field
	@Column(unique = true)
	@Analyzer(definition = "customanalyzer")
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@JsonIgnore
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Field
	@Analyzer(definition = "customanalyzer")
	@Column(unique = true)
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Field
	@Analyzer(definition = "customanalyzer")
	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

}