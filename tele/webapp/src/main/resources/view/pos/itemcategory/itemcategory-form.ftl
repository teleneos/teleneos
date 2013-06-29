<html>
	<head>
		<title><@s.text name="page.itemcategory.title" /></title>
		<meta name="header" content="<@s.text name="page.itemcategory.header" />">
		<content tag="sidenav">/view/decorator/nav/pos-sidenav.ftl</content>
	</head>
	<body>
		<div class="block-content collapse in">
			<@s.form theme="bootstrap" cssClass="form-horizontal">
				<@s.hidden name="itemCategory.id" />
				<@s.textfield key="label.admin.itemcategory.name" required="true" name="itemCategory.name" cssClass="span4" />
				<@s.textfield key="label.admin.itemcategory.description"  name="itemCategory.description" cssClass="span6" />
				<div class="form-actions">
					<#if itemCategory.id??>
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