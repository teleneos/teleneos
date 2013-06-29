<html>
	<head>
		<title><@s.text name="page.ticket.category.title.page" /></title>
		<meta name="header" content="<@s.text name="page.ticket.category.header" />">
		<content tag="sidenav">/view/decorator/nav/ticket-sidenav.ftl</content>
	</head>
	<body>
		<div class="block-content collapse in">
			<@s.form theme="bootstrap" cssClass="form-horizontal">
				<@s.hidden name="category.id" />
				<@s.textfield key="label.ticket.category.name" name="category.name" value="${category.name!}" cssClass="span4" required="true"/>
				<@s.textarea key="label.ticket.category.description" name="category.description" value="${category.description!}" cssClass="span4" />
				<div class="form-actions">
					<#if category.id??>
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