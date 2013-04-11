<html>
	<head>
		<title><@s.text name="page.editprofile.title" /></title>
		<meta name="header" content="<@s.text name="page.editprofile.header" />">
		<script type="text/javascript" src="<@s.url value="/scripts/cimande-popup.js" />"></script>
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
					<@s.textfield key="label.login.username" name="user.user.username" cssClass="span4" required="true"/>
					</#if>
					<@s.password key="label.login.password" id="pass1" cssClass="span4" name="pass" required="true"/>
					<@s.password key="label.login.confirmpassword" id="pass2" name="user.user.password" cssClass="span4" required="true"/>

					<@s.textfield key="label.user.email" name="user.user.email" cssClass="span4" required="true" />
					
					<@s.textfield key="label.user.name.first" name="user.name.first" cssClass="span4" required="true" />
					<@s.textfield key="label.user.name.last" name="user.name.last" cssClass="span4" />
					
					<@s.textfield key="label.user.idcard" name="user.idcard" cssClass="span4" required="true" />
					<@s.textfield key="label.user.phone" name="user.phone" cssClass="span4" />
					
					<@s.textarea key="label.user.address" name="user.address.street1" cssClass="span4" required="true" />
					<@s.textfield key="label.user.occupation" name="user.occupation" cssClass="span4" />
					
					<#--
					<@s.textfield key="label.user.address.country" name="user.address.countryId" cssClass="span4" />
					<@s.select key="label.editprofile.role" list={'':'-- Select Role --','ADMINISTRATOR':'ADMINISTRATOR','USER':'USER'} listKey="key" listValue="value" name="user.user.role"  />					
					<div class="control-group ">
						<label class="control-label" for="add_id">Package <span class="required">*</span></label>
						<div class="controls">
							<@s.hidden name="user.internetPackage.id" id="package-id" />
							<input type="text" id="package-name" readonly="true" class="span4">
							<button class="btn openpopup" type="button" title="<@s.text name="label.master.packagemanager.name" />" object-name="packages|name" field-target="package-id|package-name" href="<@s.url value="/admin/user/packages" />">Choose</button>
						</div>
					</div>
					<@s.select key="label.editprofile.status" list={'':'-- Select Status --','ACTIVE':'ACTIVE', 'INACTIVE':'INACTIVE'} name="user.user.logInformation.statusFlag" listKey="key" listValue="value" />					
					-->
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
			
			$('.password').val('${pass!}');
		});
		</script>
	</body>
</html>