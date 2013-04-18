<html>
	<head>
		<title><@s.text name="page.businesspartner.title" /></title>
		<meta name="header" content="<@s.text name="page.businesspartner.header" />">
	</head>
	<body>
		<div class="row-fluid">
			<#include "/view/decorator/nav/pos-sidenav.ftl" />
			<div class="span10">
				<@s.form theme="bootstrap" cssClass="form-horizontal">
					<@s.hidden name="businessPartner.id" />
					<#-- <@s.label key="label.admin.businesspartner.category" required="true" /> -->
					<@s.checkboxlist key="label.admin.businesspartner.category" name="businessPartner.category" list={'VENDOR' : 'Vendor', 'CUSTOMER' : 'Customer'} listValue="value" listKey="key" />
					<@s.textfield key="label.admin.businesspartner.name" required="true" name="businessPartner.name" cssClass="span4" />
					<@s.textfield key="label.admin.businesspartner.officePhone"  name="businessPartner.officePhone" cssClass="span6" />
					<@s.textfield key="label.admin.businesspartner.fax" required="true" name="businessPartner.fax" cssClass="span4" />
					<@s.textfield key="label.admin.businesspartner.email"  name="businessPartner.email" cssClass="span6" />
					<@s.textfield key="label.admin.businesspartner.address" required="true" name="businessPartner.address" cssClass="span4" />
					<@s.textfield key="label.admin.businesspartner.city" required="true" name="businessPartner.city" cssClass="span4" />
					<@s.textfield key="label.admin.businesspartner.zipcode"  name="businessPartner.zipCode" cssClass="span6" />
					<@s.textfield key="label.admin.businesspartner.country" required="true" name="businessPartner.country" cssClass="span4" />
					<@s.textfield key="label.admin.businesspartner.description"  name="businessPartner.description" cssClass="span6" />
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
		</div>		
	</body>
</html>