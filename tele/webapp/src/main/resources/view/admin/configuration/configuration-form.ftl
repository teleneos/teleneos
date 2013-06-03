<html>
	<head>
		<title><@s.text name="page.configuration.title" /></title>
		<meta name="header" content="<@s.text name="page.configuration.header" />">
	</head>
	<body>
		<div class="row-fluid">
			<#include "/view/decorator/nav/configuration-sidenav.ftl" />
			<div class="span10">
				<@s.form theme="bootstrap" cssClass="form-horizontal" action="configuration/add" method="post">
					<h4>Report Refresh Interval</h4>
						<@s.hidden name="configurations[0].id" />
						<@s.textfield key=configurations[0].key required="true" name="configurations[0].value" cssClass="span2" />
						<@s.hidden name="configurations[1].id" />
						<@s.textfield key=configurations[1].key required="true" name="configurations[1].value" cssClass="span2" />
						<@s.hidden name="configurations[2].id" />
						<@s.textfield key=configurations[2].key required="true" name="configurations[2].value" cssClass="span2" />
						<@s.hidden name="configurations[3].id" />
						<@s.textfield key=configurations[3].key required="true" name="configurations[3].value" cssClass="span2" />
					<h4>Log</h4>
						<@s.hidden name="configurations[4].id" />
						<@s.textfield key=configurations[4].key required="true" name="configurations[4].value" cssClass="span2" />
					<div class="form-actions">
						<@s.submit key="button.save" cssClass="btn btn-primary" />
					</div>
				</@s.form>
			</div>
		</div>		
	</body>
</html>