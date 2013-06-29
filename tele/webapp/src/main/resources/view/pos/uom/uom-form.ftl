<html>
	<head>
		<title><@s.text name="page.uom.title" /></title>
		<meta name="header" content="<@s.text name="page.uom.header" />">
		<content tag="sidenav">/view/decorator/nav/pos-sidenav.ftl</content>
	</head>
	<body>
		<div class="block-content collapse in">
			<@s.form theme="bootstrap" cssClass="form-horizontal">
				<@s.hidden name="itemType.id" />
				<@s.textfield key="label.admin.uom.name" required="true" name="uom.name" cssClass="span4" />
				<@s.textfield key="label.admin.uom.description"  name="uom.description" cssClass="span6" />
				<div class="form-actions">
					<#if uom.id??>
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