<html>
	<head>
		<title><@s.text name="page.signup.title" /></title>
	</head>
	<body>
		<div class="row-fluid">
			<div class="span12">
				<div class="modal modal-static">
					<div class="modal-header">
						<h2>
							<@s.text name="page.signup.header" />
						</h2>
					</div>
					<@s.form id="login-form" theme="bootstrap" cssClass="form-horizontal">
						<div class="modal-body">
							<@s.actionmessage theme="bootstrap" />
							<@s.textfield key="label.login.username" name="user.user.username" cssClass="span9" autofocus="" /> 
							<@s.password key="label.login.password" id="pass1" cssClass="span9" />
							<@s.password id="pass2" name="user.user.password" cssClass="span9" />
							
							<@s.textfield key="label.user.name.first" name="user.name.first" cssClass="span9" />
							<@s.textfield key="label.user.name.last" name="user.name.last" cssClass="span9" />
							
							<@s.textfield key="label.user.email" name="user.user.email" cssClass="span9" />
							<@s.textfield key="label.user.phone" name="user.phone" cssClass="span9" />
							<@s.textfield key="label.user.address.country" name="user.address.countryId" cssClass="span9" />
						</div>
						<div class="modal-footer">
							<@s.submit key="button.signup" cssClass="btn btn-primary" />
							<span id="alert" class="alert alert-error hide"><@s.text name="message.password.doesntmatch" /></span>
						</div>
					</@s.form>
				</div>
			</div>
		</div>
		<script type="text/javascript">
		$(function() {
			$('#pass1, #pass2').keyup(function() {
				if (!($('#newpass1').val() === $('#newpass2').val())) {
					$('#alert').fadeIn();
				} else {
					$('#alert').fadeOut();
				}
			});
		});
		</script>
	</body>
</html>