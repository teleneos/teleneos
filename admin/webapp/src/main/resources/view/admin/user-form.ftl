<html>
	<head>
		<title><@s.text name="page.editprofile.title" /></tile>
		<meta name="header" content="<@s.text name="page.editprofile.header" />">
	</head>
	<body>
		<div class="row-fluid">
			<#include "/view/decorator/nav/admin-sidenav.ftl" />
			<div class="span10">
				<#if user??>
					<#assign pass = user.password! />
				</#if>
			
				<@s.form id="change-password" theme="bootstrap" cssClass="form-horizontal">
					<@s.text name="tooltip.changepassword.newpass" var="ttnewpass" />
					<@s.text name="tooltip.changepassword.confirm" var="ttconfirm" />
				
					<@s.actionerror theme="bootstrap" />
					<#if request.getParameter('success')??>
					<div class="alert alert-success">
						<a class="close" data-dismiss="alert" href="#">&times;</a>
						<@s.text name="message.profile.updatesuccess" />
					</div>
					</#if>
					
					<@s.hidden name="user.id" />
					<@s.textfield key="label.editprofile.username" name="user.username" cssClass="span4" />
					<@s.password id="newpass1" label="New Password" name="user.password" cssClass="span4 password" />
					<@s.password id="newpass2" label="Confirm password" cssClass="span4 password" />
					<@s.textfield key="label.editprofile.email" name="user.email" cssClass="span4" />
					<@s.textfield key="label.editprofile.website" name="user.website" cssClass="span4" />
					<@s.select key="label.editprofile.role" name="user.role" cssClass="span4" list=['ADMINISTRATOR'] />

					<div class="form-actions">
						<#if user??>
						<@s.submit key="button.update" cssClass="btn btn-primary" />
						<#else>
						<@s.submit key="button.save" cssClass="btn btn-primary" />
						</#if>
						<@s.reset key="button.reset" cssClass="btn" />
						<span id="alert" class="alert alert-error hide"><@s.text name="message.password.doesntmatch" /></span>
					</div>
				</@s.form>
			</div>
		</div>
		<script type="text/javascript">
		$(function() {
			$('#newpass1, #newpass2').keyup(function() {
				if (!($('#newpass1').val() === $('#newpass2').val())) {
					$('#alert').fadeIn();
				} else {
					$('#alert').fadeOut();
				}
			});
			
			$('.password').val('${pass!}');
		});
		</script>
	</body>
</html>