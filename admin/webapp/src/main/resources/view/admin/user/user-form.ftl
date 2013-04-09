<html>
	<head>
		<title><@s.text name="page.editprofile.title" /></title>
		<meta name="header" content="<@s.text name="page.editprofile.header" />">
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
					
					<@s.textfield key="label.user.name.first" name="user.name.first" cssClass="span4" required="true" />
					<@s.textfield key="label.user.name.last" name="user.name.last" cssClass="span4" />
					
					<@s.textfield key="label.user.email" name="user.user.email" cssClass="span4" required="true" />
					<@s.textfield key="label.user.phone" name="user.phone" cssClass="span4" />
					
					<#--
					<@s.textfield key="label.user.address.country" name="user.address.countryId" cssClass="span4" />
					-->
					
					<@s.select key="label.editprofile.role" list={'':'-- Select Role --','ADMINISTRATOR':'ADMINISTRATOR','USER':'USER'} listKey="key" listValue="value" name="user.user.role"  />
					<#assign groups=groups.entityList>
					<@s.select key="label.master.group.name" name="user.group.id" list="groups.entityList" listKey="id" listValue="name" />
					<@s.select key="label.master.packagemanager.name" name="user.internetPackage.id" list={'':'-- Select Package --'} listKey="key" listValue="value"/>
					<@s.select key="label.editprofile.status" list={'':'-- Select Status --','ACTIVE':'ACTIVE', 'INACTIVE':'INACTIVE'} name="user.user.logInformation.statusFlag" listKey="key" listValue="value" />					
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
			$('input[name="user.user.username"]').keyup(function() {
				$(this).val($(this).val().toLowerCase());
			});
			
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
			
			$('#user_user_group_id').change(function(){
				var val = $(this).val();
			
				$.ajax({
			        url: "<@s.url value="/admin/user/package/" />" + val +".json",
			        type: 'GET',
			        dataType: 'json', // added data type
			        success: function(res) {
			        	console.log(res);
			        	$('#user_user_internetPackage_id').empty();
			        	$('#user_user_internetPackage_id').append($('<option></option>').val("").html("-- Select Package --"));
			        	for (var i = 0; i < res.groupPackages.length; i++) {
			        		var opt = $('<option></option>');
			        		opt.val(res.groupPackages[i].internetPackage.id);
			        		opt.html(res.groupPackages[i].internetPackage.name);
			        		<#if user.internetPackage??>
			        		if (opt.val() == '${user.internetPackage.id}') {
			        			opt.attr('selected', 'selected');
			        		}
			        		</#if>
			        		$('#user_user_internetPackage_id').append(opt);
			        	}
			        }
			    });
				
			}).change();
			
			$('.password').val('${pass!}');
		});
		</script>
	</body>
</html>