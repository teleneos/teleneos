<html>
	<head>
		<title><@s.text name="page.editprofile.title" /></title>
		<meta name="header" content="<@s.text name="page.editprofile.header" />">
		<link rel="stylesheet" type="text/css" href="<@s.url value="/styles/datepicker.css" />" />
		<script type="text/javascript" src="<@s.url value="/scripts/bootstrap-datepicker.js" />"></script>
	</head>
	<body>
		<div class="row-fluid">
			<#include "/view/decorator/nav/admin-sidenav.ftl" />
			<div class="span10">
				<#if user??>
					<#assign pass = user.password! />
				</#if>
			
				<@s.form id="user" theme="bootstrap" cssClass="form-horizontal">
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
					
					<@s.actionmessage theme="bootstrap" />
					<#if user.id?? && user.id!="">
					<@s.textfield key="label.login.username" name="user.user.username" cssClass="span4" readonly="true" required="true"/> 
					<#else>
					<@s.textfield key="label.login.username" name="user.user.username" cssClass="span4" required="true" />
					</#if>
					<#--
					<@s.password key="label.login.password" id="pass1" cssClass="span4" name="pass" />
					-->
					<@s.textfield key="label.login.password" id="pass2" name="user.user.password" cssClass="span4" readonly="true"/>
					<@s.textfield key="label.user.email" name="user.user.email" cssClass="span4" required="true" />
					
					<@s.textfield key="label.user.name.first" name="user.name.first" cssClass="span4" required="true" />
					<@s.textfield key="label.user.name.last" name="user.name.last" cssClass="span4" />

					<@s.textfield key="label.user.birthdate" name="user.birthDate" cssClass="span4" required="true" readonly="true" />
					<@s.textfield key="label.user.idcard" name="user.idcard" cssClass="span4" required="true" />
					<@s.textfield key="label.user.phone" name="user.phone" cssClass="span4" />
					
					<@s.textarea key="label.user.address" name="user.address.street1" cssClass="span4" required="true" />
					<@s.textfield key="label.user.occupation" name="user.occupation" cssClass="span4" />
					<@s.select key="label.user.status" name="user.logInformation.statusFlag" list={'ACTIVE' : 'Active', 'INACTIVE' : 'Inactive'} listValue="value" listKey="key" />
					<div class="form-actions">
						<#if user.id??>
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
		<#--
		<script type="text/javascript">
		$(function() {
			$('#pass1, #pass2').keyup(function() {
				if (!($('#pass1').val() === $('#pass2').val())) {
					$('#alert').fadeIn();
				} else {
					$('#alert').fadeOut();
				}
			});
			
			<#if user.user??>
			$('#pass1, #pass2').val('${user.user.password!}');
			</#if>
			$('#user_user_idcard').after(' <span class="idalert help-inline hide">Must be 13 character</span>');
			
			$('.password').val('${pass!}');
			$('#user_user_birthDate').datepicker({
				format : 'dd-mm-yyyy',
				endDate : new Date(),
				autoclose : true
			});
		});
		</script>
		-->
	</body>
</html>