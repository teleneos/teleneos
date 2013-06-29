<html>
	<head>
		<title><@s.text name="page.telecentre.title" /></title>
		<meta name="header" content="<@s.text name="page.telecentre.title" />">
		<content tag="sidenav">/view/decorator/nav/telecentre-sidenav.ftl</content>
	</head>
	<body>
		<div class="block-content collapse in">
			<@s.form theme="bootstrap" cssClass="form-horizontal">
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
					<#if telecentre.id??>
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