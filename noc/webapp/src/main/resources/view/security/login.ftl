<html>
	<head>
		<title><@s.text name="page.login.title" /></title>
		<#--
		<script type="text/javascript" src="<@s.url value="/scripts/chilli-library.js" />"></script>
		-->
	</head>
	<body>
		<div class="row-fluid">
			<div class="span12">
				<div class="modal modal-static">
					<div class="modal-header">
						<h2><@s.text name="page.login.header" /></h2>
					</div>
					<@s.form id="login-form" theme="bootstrap" action="/login" cssClass="form-horizontal">
						<div class="modal-body">
							<@s.actionmessage theme="bootstrap" />
							<@s.textfield key="label.login.username" name="username" cssClass="span9" autofocus="" /> 
							<@s.password key="label.login.password" name="password" cssClass="span9" />
							<#-- <@s.checkbox name="_spring_security_remember_me" key="label.login.rememberme" /> -->
						</div>
						<div class="modal-footer">
							<@s.submit key="button.login" cssClass="btn btn-primary" />
						</div>
					</@s.form>
				</div>
			</div>
		</div>
		<script type="text/javascript">
		$(function() {
			$('input[name="username"]').focus();
			<#--
			chilliController.host = '${model.host!}';
			chilliController.port = ${model.port!};
			chilliController.interval = 30;
			chilliController.uamService = '${model.uamService}';
			
			chilliController.onError = function() {
				
			}
			
			chilliController.onUpdate = function() {
				window.refresh();
			}
			
			$('#login-form').submit(function() {
				var username = $('input[name="username"]').val();
				var password = $('input[name="password"]').val();
				
				chilliController.logon(username, password);
			
				return false;
			});
			-->
		});
		</script>
	</body>
</html>