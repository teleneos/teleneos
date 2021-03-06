<html>
	<head>
		<title><@s.text name="page.department.title" /></title>
		<meta name="header" content="<@s.text name="page.department.header" />">
	</head>
	<body>
		<div class="row-fluid">
			<#include "/view/decorator/nav/ticket-sidenav.ftl" />
			<div class="span10">
				<@s.form theme="bootstrap" cssClass="form-horizontal">
					<@s.hidden name="department.id" />
					<@s.textfield key="label.ticket.department.name" name="department.name" value="${department.name!}" cssClass="span4" />
					<div class="form-actions">
						<#if department.id??>
						<@s.submit key="button.update" cssClass="btn btn-primary" />
						<#else>
						<@s.submit key="button.save" cssClass="btn btn-primary" />
						</#if>
						<@s.reset key="button.reset" cssClass="btn" />
						<span id="alert" class="alert alert-error hide"><@s.text name="message.password.doesntmatch" /></span>
					</div>
				</@s.form>
			</div>
		</div>
	</body>
</html>