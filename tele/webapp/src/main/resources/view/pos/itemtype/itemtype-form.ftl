<html>
	<head>
		<title><@s.text name="page.itemtype.title" /></title>
		<meta name="header" content="<@s.text name="page.itemtype.header" />">
		<content tag="sidenav">/view/decorator/nav/pos-sidenav.ftl</content>
	</head>
	<body>
		<div class="block-content collapse in">
			<@s.form theme="bootstrap" cssClass="form-horizontal">
				<@s.hidden name="itemType.id" />
				<@s.textfield key="label.admin.itemtype.name" required="true" name="itemType.name" cssClass="span4" />
				<@s.textfield key="label.admin.itemtype.unit" required="true" name="itemType.unit" cssClass="span4" />
				<@s.select name="itemType.uom.id" list="uoms.entityList" listValue="name" listKey="id"/>
				<@s.textarea key="label.admin.itemtype.description"  name="itemType.description" cssClass="span4" />
				<div class="form-actions">
					<#if itemType.id??>
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