<html>
	<head>
		<title><@s.text name="page.editprofile.title" /></tile>
		<meta name="header" content="<@s.text name="page.editprofile.header" />">
	</head>
	<body>
				<@s.form id="change-password" theme="bootstrap" cssClass="form-horizontal">
					<@s.actionerror theme="bootstrap" />
					<#if request.getParameter('success')??>
					<div class="alert alert-success">
						<a class="close" data-dismiss="alert" href="#">&times;</a>
						<@s.text name="message.profile.updatesuccess" />
					</div>
					</#if>
					<@s.hidden name="model.id" />
					<@s.textfield key="label.editprofile.username" name="model.username" cssClass="span4" disabled="true" />
					<@s.textfield key="label.editprofile.email" name="model.email" cssClass="span4" />
					<@s.textfield key="label.editprofile.website" name="model.website" cssClass="span4" />
					<@s.hidden name="model.role" />
					<@s.hidden name="model.password" />

					<div class="form-actions">
						<@s.submit key="button.save" cssClass="btn btn-primary" />
						<@s.reset key="button.reset" cssClass="btn" />
					</div>
				</@s.form>
	</body>
</html>