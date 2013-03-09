<html>
	<head>
		<title><@s.text name="page.changepassword.title" /></tile>
		<meta name="header" content="<@s.text name="page.changepassword.header" />">
	</head>
	<body>
				<@s.form id="change-password" theme="bootstrap" cssClass="form-horizontal">
					<@s.text name="tooltip.changepassword.newpass" var="ttnewpass" />
					<@s.text name="tooltip.changepassword.confirm" var="ttconfirm" />
					<@s.text name="placeholder.changepassword.newpass" var="phnewpass" />
					<@s.text name="placeholder.changepassword.confirm" var="phconfirm" />

					<@s.password id="newpass1" label="New Password" name="password" tooltip="%{ttnewpass}" placeholder="%{phnewpass}" cssClass="span4" />
					<@s.password id="newpass2" label="Confirm password" tooltip="%{ttconfirm}" placeholder="%{phconfirm}" cssClass="span4" />

					<div class="form-actions">
						<@s.submit key="button.save" cssClass="btn btn-primary" />
						<@s.reset key="button.reset" cssClass="btn" />
						<span id="alert" class="alert alert-error hide"><@s.text name="message.password.doesntmatch" /></span>
					</div>
				</@s.form>		

		<script type="text/javascript">
		$(function() {
			$('#newpass1, #newpass2').keyup(function() {
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