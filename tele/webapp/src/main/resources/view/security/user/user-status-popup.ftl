<html>
	<head>
		<title><@s.text name="page.login.title" /></title>
		
		<script type="text/javascript" src="<@s.url value="/scripts/chilli-library.js" />"></script>
	</head>
	<body>
		<div class="row-fluid">
			<div class="span12">
				<@s.form action="/user/login" id="login-form" theme="bootstrap" cssClass="form-horizontal">
					<div class="modal-body">
						<@s.actionmessage theme="bootstrap" />
						<@s.textfield key="label.login.username" id="username" cssClass="span9" readonly="true" />
						<@s.textfield key="label.admin.onlineuser.activepackage" id="package" cssClass="span9" readonly="true" />
						<@s.textfield key="label.user.subscription.enddate" id="end-date" cssClass="span9" readonly="true" />
						<@s.textfield key="label.user.subscription.quota" id="quota" cssClass="span9" readonly="true" />
					</div>
					<div class="modal-footer">
						<a href="http://logout" class="btn btn-primary"><@s.text name="menu.logout" /></a>
					</div>
				</@s.form>
			</div>
		</div>
		<div id="overlay" class="hide" style="width: 100%; height: 100%; position: absolute; top: 0; left: 0; background: rgba(0, 0, 0, .5);z-index: 99999;">Loading...</div>
		<script type="text/javascript">
		function byteString(st) {
			st = new Number(st);
			
			if (st > (1024 * 1024 * 1024)) {
				return (st / (1024 * 1024 * 1024)).toFixed(2) + 'GB';
			} else if (st > (1024 * 1024)) {
				return (st / (1024 * 1024)).toFixed(2) + 'MB';
			} else if (st > (1024)) {
				return (st / (1024)).toFixed(2) + 'KB';
			} else {
				return (st < 0 ? 0 : st).toFixed(2) + 'B';
			}
		}
		
		$(function() {
			$('input[name="username"]').focus();
			
			chilliController.host = '${model.host!}';
			chilliController.port = ${model.port!};
			chilliController.interval = 120;
			chilliController.uamService = '${model.uamService}';
			
			chilliController.onError = function(cmd) {
				alert("Error: " + cmd);
				
				$('#overlay').hide();
			}
			
			chilliController.onUpdate = function(cmd) {
				if (chilliController.clientState == 1) {
					$.getJSON('<@s.url />.json?userurl=' + chilliController.session.userName, function(data) {
						data = data.userPackage;
						var username = data.username;
						var endDate = new Date(data.endDate);
						var balance = data.quotaBalance;
						var inet = data.internetPackage;
						var inetCode = inet.code;
						var inetName = inet.name;
						
						$('#username').val(username);
						$('#end-date').val(endDate.getDate() + '-' + endDate.getMonth() + '-' + endDate.getFullYear() + ' ' + endDate.getHours() + ':' + endDate.getMinutes() + ':' + endDate.getSeconds());
						$('#package').val(inetCode + ' (' + inet.name + ')');
						$('#quota').val(byteString(balance));
					});
				
					// $('input[name="username"]').val($('input[name="username"]').val() + "x");
				}
				
				if (chilliController.clientState == 0) {
					$('input[name="password"]').val('');
				}
				
				$('#overlay').hide();
			}
			
			chilliController.refresh();
		});
		</script>
	</body>
</html>