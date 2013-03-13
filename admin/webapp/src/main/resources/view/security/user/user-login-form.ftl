<html>
	<head>
		<title><@s.text name="page.login.title" /></title>
	</head>
	<body>
		<div class="row-fluid">
			<div class="span12">
				<div class="modal modal-static">
					<div class="modal-header">
						<h2>
							<@s.text name="page.login.header" />
							<small>
								<@s.text name="page.login.header.signup">
									<@s.param>
										<@s.url value="/user/signup" />
									</@s.param>
								</@s.text>
							</small>
						</h2>
					</div>
					<@s.form action="/user/login" id="login-form" theme="bootstrap" cssClass="form-horizontal">
						<div class="modal-body">
							<@s.actionmessage theme="bootstrap" />
							<@s.textfield key="label.login.username" name="username" cssClass="span9" autofocus="" /> 
							<@s.password key="label.login.password" name="password" cssClass="span9" />
							<@s.checkbox name="_spring_security_remember_me" key="label.login.rememberme" />
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
		});
		</script>
	</body>
</html>