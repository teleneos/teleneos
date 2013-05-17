<html>
	<head>
		<title><@s.text name="page.login.title" /></title>
		
		<script type="text/javascript" src="<@s.url value="/scripts/chilli-library.js" />"></script>
	</head>
	<body>
		<div class="row-fluid">
			<div class="span12">
				<div class="modal modal-static">
					<div class="modal-header">
						<h2>
							<@s.text name="page.login.header" />
							<#--
							<small>
								<@s.text name="page.login.header.signup">
									<@s.param>
										<@s.url value="/user/signup" />
									</@s.param>
								</@s.text>
							</small>
							-->
						</h2>
					</div>
					<@s.form action="/user/login" id="login-form" theme="bootstrap" cssClass="form-horizontal">
						<div class="modal-body">
							<@s.actionmessage theme="bootstrap" />
							<@s.textfield key="label.login.username" name="username" cssClass="span9" autofocus="" /> 
							<@s.password key="label.login.password" name="password" cssClass="span9" />
							<#-- <@s.checkbox name="_spring_security_remember_me" key="label.login.rememberme" />-->
						</div>
						<div class="modal-footer">
							<@s.submit key="button.login" cssClass="btn btn-primary" />
						</div>
					</@s.form>
				</div>
			</div>
		</div>
		<a id="open-popup" href="<@s.url value="/user/signin/status" />" ></a>
		<div id="overlay" class="hide" style="width: 100%; height: 100%; position: absolute; top: 0; left: 0; background: rgba(0, 0, 0, .5);z-index: 99999;">Loading...</div>
		<script type="text/javascript">
		$(function() {
			$('input[name="username"]').focus();
			
			chilliController.host = '${model.host!}';
			chilliController.port = ${model.port!};
			chilliController.interval = 5;
			<#if model.uamService??>
			chilliController.uamService = '${model.uamService}';
			<#else>
			chilliController.uamService = '<@s.url forceAddSchemeHostAndPort="true" value="/cgi-bin/uam.pl" />';
			</#if>
			
			chilliController.onError = function(cmd) {
				alert("Error: " + cmd);
				
				$('#overlay').hide();
			}
			
			chilliController.onUpdate = function(cmd) {
				if (chilliController.clientState == 1) {
					window.open('<@s.url value="/user/signin/status" />', 'Status', 'height=500,width=400,resizable=no');
					location.href = '${model.userurl!}';
				}
				
				if (chilliController.clientState == 0) {
					$('input[name="password"]').val('');
				}
				
				$('#overlay').hide();
			}
			
			$('#login-form').submit(function() {
				$('#overlay').fadeIn();
			
				var username = $('input[name="username"]').val();
				var password = $('input[name="password"]').val();
				
				chilliController.logon(username, password);
			
				return false;
			});
			
			chilliController.refresh();
		});
		</script>
	</body>
</html>