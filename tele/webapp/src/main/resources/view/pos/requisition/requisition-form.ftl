<html>
	<head>
		<title><@s.text name="page.requisition.title" /></title>
		<meta name="header" content="<@s.text name="page.requisition.header" />">
		<content tag="sidenav">/view/decorator/nav/pos-sidenav.ftl</content>
	</head>
	<body>
		<div class="block-content collapse in">
			<@s.form theme="bootstrap" cssClass="form-horizontal">
				<@s.hidden name="requisition.id" />
				<@s.textfield key="label.admin.requisition.title" required="true" name="requisition.title" cssClass="span4" />
				<@s.textfield key="label.admin.requisition.description"  name="requisition.description" cssClass="span6" />
				<!-- <@s.textfield key="label.admin.requisition.duedate"  name="requisition.duedate" cssClass="span4" /> -->
				<div class="form-actions">
					<#if requisition.id??>
					<@s.submit key="button.update" cssClass="btn btn-primary" />
					<#else>
					<@s.submit key="button.save" cssClass="btn btn-primary" />
					</#if>
					<@s.reset key="button.reset" cssClass="btn" />
					<span id="alert" class="alert alert-error hide"><@s.text name="message.password.doesntmatch" /></span>
				</div>
			</@s.form>
		</div>		
	</body>
</html>