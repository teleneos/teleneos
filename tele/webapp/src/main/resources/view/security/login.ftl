<html>
	<head>
		<title><@s.text name="page.login.title" /></title>
	</head>
	<body>
		<div class="row-fluid">
			<div class="span12">
				<div class="login-dialog modal modal-static">
					<div class="modal-header">
						<h2><@s.text name="page.login.header" /></h2>
					</div>
					<@s.form id="login-form" theme="bootstrap" action="/login" cssClass="form-horizontal">
						<div class="modal-body">
							<@s.actionmessage theme="bootstrap" />
							<@s.textfield key="label.login.username" name="username" cssClass="span9" autofocus="" /> 
							<@s.password key="label.login.password" name="password" cssClass="span9" />
							<#-- <@s.hidden name="_spring_security_remember_me" value="true" /> -->
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
		jQuery.fn.center = function () {
			this.css("position", "absolute");
			this.css("top", Math.max(0, (($(window).height() - $(this).outerHeight()) / 2) + $(window).scrollTop()) + "px");
			this.css("left", Math.max(0, (($(window).width() - $(this).outerWidth()) / 2) + $(window).scrollLeft()) + "px");
			
			return this;
		}
		
		$(function() {
			$('input[name="username"]').focus();
			$('.navbar').remove();
		});
		</script>
	</body>
</html>