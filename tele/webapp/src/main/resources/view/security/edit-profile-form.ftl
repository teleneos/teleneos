<html>
	<head>
		<title><@s.text name="page.editprofile.title" /></title>
		<meta name="header" content="<@s.text name="page.editprofile.header" />">
		<link rel="stylesheet" type="text/css" href="<@s.url value="/styles/datepicker.css" />" />
		<script type="text/javascript" src="<@s.url value="/scripts/bootstrap-datepicker.js" />"></script>
	</head>
	<body>
		<div class="block-content collapse in">
			<@s.form id="user" theme="bootstrap" cssClass="form-horizontal">
				<@s.actionerror theme="bootstrap" />
				<#if request.getParameter('success')??>
				<div class="alert alert-success">
					<a class="close" data-dismiss="alert" href="#">&times;</a>
					<@s.text name="message.profile.updatesuccess" />
				</div>
				</#if>
				
				<@s.hidden name="model.id" />
				<@s.hidden name="model.user.password" />
				
				<@s.actionmessage theme="bootstrap" />
				<@s.textfield key="label.login.username" name="model.user.username" cssClass="span4" readonly="true" required="true"/> 
				<@s.textfield key="label.user.email" name="model.user.email" cssClass="span4" required="true" />
						
				<@s.textfield key="label.user.name.first" name="model.name.first" cssClass="span4" required="true" />
				<@s.textfield key="label.user.name.last" name="model.name.last" cssClass="span4" />
				<@s.textfield key="label.user.birthdate" name="model.birthDate" cssClass="span4" required="true" readonly="true" />
				<@s.textfield key="label.user.idcard" name="model.idcard" cssClass="span4" required="true" />
				<@s.textfield key="label.user.phone" name="model.phone" cssClass="span4" />
				
				<@s.textarea key="label.user.address" name="model.address.street1" cssClass="span4" required="true" />
				<@s.textfield key="label.user.occupation" name="model.occupation" cssClass="span4" />
				<div class="form-actions">
					<@s.submit key="button.update" cssClass="btn btn-primary" />
					<@s.reset key="button.reset" cssClass="btn" />
					<span id="alert" class="alert alert-error hide"><@s.text name="message.password.doesntmatch" /></span>
				</div>
			</@s.form>
		</div>
		<script type="text/javascript">
		$(function() {
			$('#user_model_birthDate').datepicker({
				format : 'dd-mm-yyyy',
				endDate : new Date(),
				autoclose : true
			});
		});
		</script>
	</body>
</html>