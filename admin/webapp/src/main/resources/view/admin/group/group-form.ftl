<html>
	<head>
		<title><@s.text name="page.group.title" /></title>
		<meta name="header" content="<@s.text name="page.group.header" />">
	</head>
	<body>
		<div class="row-fluid">
			<#include "/view/decorator/nav/admin-sidenav.ftl" />
			<div class="span10">
				<@s.form theme="bootstrap" cssClass="form-horizontal">
					<@s.hidden name="group.id" />
					<@s.textfield key="label.admin.group.name" required="true" name="group.name" cssClass="span4" />
					<@s.textfield key="label.admin.group.description" name="group.description" cssClass="span4" />
					
					<div class="form-actions">
						<#if group.id??>
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