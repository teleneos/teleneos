<html>
	<head>
		<title><@s.text name="page.configuration.title" /></title>
		<meta name="header" content="<@s.text name="page.configuration.header" />">
		<content tag="sidenav">/view/decorator/nav/configuration-sidenav.ftl</content>
	</head>
	<body>
		<div class="block-content collapse in">
			<@s.form theme="bootstrap" cssClass="form-horizontal" action="/admin/configuration/add" method="post">
				<h4>Report Refresh Interval</h4>
				<@s.hidden name="configurations[0].id" />
				<@s.textfield key="label.telecentre.availability" required="true" name="configurations[0].value" cssClass="span2" />
				<@s.hidden name="configurations[1].id" />
				<@s.textfield key="label.telecentre.monitoring" required="true" name="configurations[1].value" cssClass="span2" />
				<@s.hidden name="configurations[2].id" />
				<@s.textfield key="label.telecentre.performance" required="true" name="configurations[2].value" cssClass="span2" />
				<@s.hidden name="configurations[3].id" />
				<@s.textfield key="label.telecentre.utilization" required="true" name="configurations[3].value" cssClass="span2" />
				<h4>Log</h4>
				<@s.hidden name="configurations[4].id" />
				<@s.textfield key="label.telecentre.log" required="true" name="configurations[4].value" cssClass="span2" />
				<h4>Telecentre Detail</h4>
				<#if telecentre.id??>
				<@s.textfield key="label.telecentre.id" name="telecentre.id" cssClass="span4" readonly="true" />
				</#if>
				<@s.textfield key="label.telecentre.name" required="true" name="telecentre.name" cssClass="span4" />
				<#if telecentre.id??>
				<@s.textfield key="label.telecentre.password" name="telecentre.password" cssClass="span4" readonly="true" />
				</#if>
				<@s.textfield key="label.telecentre.phone" required="true" name="telecentre.phone" cssClass="span4" />
				<@s.textfield key="label.telecentre.email" required="true" name="telecentre.email" cssClass="span4" />
				<@s.textfield key="label.telecentre.address" required="true" name="telecentre.address.street1" cssClass="span4" />
				<@s.textfield key="label.telecentre.zip" required="true" name="telecentre.address.zip" cssClass="span2" />
				<@s.textfield key="label.telecentre.latitude"  required="false" name="telecentre.lat" cssClass="span4" />
				<@s.textfield key="label.telecentre.longitude" required="false" name="telecentre.lng" cssClass="span4" />
				<div class="form-actions">
					<@s.submit key="button.save" cssClass="btn btn-primary" />
				</div>
			</@s.form>
		</div>		
	</body>
</html>