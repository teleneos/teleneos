<html>
	<head>
		<title><@s.text name="page.businesspartner.title" /></title>
		<meta name="header" content="<@s.text name="page.businesspartner.header" />">
		<content tag="sidenav">/view/decorator/nav/pos-sidenav.ftl</content>
		<script type="text/javascript">
		var states = new Array("Afghanistan", "Albania", "Algeria",
				"Andorra", "Angola", "Antarctica",
				"Antigua and Barbuda", "Argentina", "Armenia",
				"Australia", "Austria", "Azerbaijan", "Bahamas",
				"Bahrain", "Bangladesh", "Barbados", "Belarus",
				"Belgium", "Belize", "Benin", "Bermuda", "Bhutan",
				"Bolivia", "Bosnia and Herzegovina", "Botswana",
				"Brazil", "Brunei", "Bulgaria", "Burkina Faso",
				"Burma", "Burundi", "Cambodia", "Cameroon",
				"Canada", "Cape Verde", "Central African Republic",
				"Chad", "Chile", "China", "Colombia", "Comoros",
				"Congo, Democratic Republic",
				"Congo, Republic of the", "Costa Rica",
				"Cote d'Ivoire", "Croatia", "Cuba", "Cyprus",
				"Czech Republic", "Denmark", "Djibouti",
				"Dominica", "Dominican Republic", "East Timor",
				"Ecuador", "Egypt", "El Salvador",
				"Equatorial Guinea", "Eritrea", "Estonia",
				"Ethiopia", "Fiji", "Finland", "France", "Gabon",
				"Gambia", "Georgia", "Germany", "Ghana", "Greece",
				"Greenland", "Grenada", "Guatemala", "Guinea",
				"Guinea-Bissau", "Guyana", "Haiti", "Honduras",
				"Hong Kong", "Hungary", "Iceland", "India",
				"Indonesia", "Iran", "Iraq", "Ireland", "Israel",
				"Italy", "Jamaica", "Japan", "Jordan",
				"Kazakhstan", "Kenya", "Kiribati", "Korea, North",
				"Korea, South", "Kuwait", "Kyrgyzstan", "Laos",
				"Latvia", "Lebanon", "Lesotho", "Liberia", "Libya",
				"Liechtenstein", "Lithuania", "Luxembourg",
				"Macedonia", "Madagascar", "Malawi", "Malaysia",
				"Maldives", "Mali", "Malta", "Marshall Islands",
				"Mauritania", "Mauritius", "Mexico", "Micronesia",
				"Moldova", "Mongolia", "Morocco", "Monaco",
				"Mozambique", "Namibia", "Nauru", "Nepal",
				"Netherlands", "New Zealand", "Nicaragua", "Niger",
				"Nigeria", "Norway", "Oman", "Pakistan", "Panama",
				"Papua New Guinea", "Paraguay", "Peru",
				"Philippines", "Poland", "Portugal", "Qatar",
				"Romania", "Russia", "Rwanda", "Samoa",
				"San Marino", " Sao Tome", "Saudi Arabia",
				"Senegal", "Serbia and Montenegro", "Seychelles",
				"Sierra Leone", "Singapore", "Slovakia",
				"Slovenia", "Solomon Islands", "Somalia",
				"South Africa", "Spain", "Sri Lanka", "Sudan",
				"Suriname", "Swaziland", "Sweden", "Switzerland",
				"Syria", "Taiwan", "Tajikistan", "Tanzania",
				"Thailand", "Togo", "Tonga", "Trinidad and Tobago",
				"Tunisia", "Turkey", "Turkmenistan", "Uganda",
				"Ukraine", "United Arab Emirates",
				"United Kingdom", "United States", "Uruguay",
				"Uzbekistan", "Vanuatu", "Venezuela", "Vietnam",
				"Yemen", "Zambia", "Zimbabwe");
		</script>
	</head>
	<body>
		<div class="block-content collapse in">
			<@s.form theme="bootstrap" cssClass="form-horizontal">
				<@s.hidden name="businessPartner.id" />
				<#-- <@s.label key="label.admin.businesspartner.category" required="true" /> -->
				<@s.checkboxlist key="label.admin.businesspartner.category" name="businessPartner.category" list={'VENDOR' : 'Vendor', 'CUSTOMER' : 'Customer'} listValue="value" listKey="key" />
				<@s.textfield key="label.admin.businesspartner.name" required="true" name="businessPartner.name" cssClass="span4" />
				<@s.textfield key="label.admin.businesspartner.officePhone"  name="businessPartner.officePhone" cssClass="span4" />
				<@s.textfield key="label.admin.businesspartner.fax"  name="businessPartner.fax" cssClass="span4" />
				<@s.textfield key="label.admin.businesspartner.email"  name="businessPartner.email" cssClass="span4" />
				<@s.textarea key="label.admin.businesspartner.address"  name="businessPartner.address" cssClass="span4" />
				<@s.textfield key="label.admin.businesspartner.city"  name="businessPartner.city" cssClass="span4" />
				<@s.textfield key="label.admin.businesspartner.zipcode"  name="businessPartner.zipCode" cssClass="span4" />
				<div class="control-group ">
					<label class="control-label" for="">Country
					</label>
					<div class="controls">
						<select name='businessPartner.country'>
							<script language="javascript">
								for ( var hi = 0; hi < states.length; hi++)
									document.write("<option value=\""+states[hi]+"\">"
											+ states[hi] + "</option>");
							</script>
						</select>
					</div>
				</div>
				<@s.textarea key="label.admin.businesspartner.description"  name="businessPartner.description" cssClass="span4" />
				<div class="form-actions">
					<#if businessPartner.id??>
					<@s.submit key="button.update" cssClass="btn btn-primary" />
					<#else>
					<@s.submit key="button.save" cssClass="btn btn-primary" />
					</#if>
					<@s.reset key="button.reset" cssClass="btn" />
				</div>
			</@s.form>
		</div>		
	</body>
</html>