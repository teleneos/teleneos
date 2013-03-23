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
					<#--
					<@s.textfield key="label.editprofile.username" name="user.username" cssClass="span4" />
					<@s.password id="newpass1" label="New Password" name="user.password" cssClass="span4 password" />
					<@s.password id="newpass2" label="Confirm password" cssClass="span4 password" />
					<@s.textfield key="label.editprofile.email" name="user.email" cssClass="span4" />
					<@s.textfield key="label.editprofile.website" name="user.website" cssClass="span4" />
					<@s.select key="label.editprofile.role" name="user.role" cssClass="span4" list=['ADMINISTRATOR'] />
					-->
					
					<@s.actionmessage theme="bootstrap" />
					<#if user.id??>
					<@s.textfield key="label.login.username" name="user.user.username" cssClass="span4" readonly="true" /> 
					<#else>
					<@s.textfield key="label.login.username" name="user.user.username" cssClass="span4" />
					</#if>
					<@s.password key="label.login.password" id="pass1" cssClass="span4" />
					<@s.password id="pass2" name="user.user.password" cssClass="span4" />
					
					<@s.textfield key="label.user.name.first" name="user.name.first" cssClass="span4" />
					<@s.textfield key="label.user.name.last" name="user.name.last" cssClass="span4" />
					
					<@s.textfield key="label.user.email" name="user.user.email" cssClass="span4" />
					<@s.textfield key="label.user.phone" name="user.phone" cssClass="span4" />
					
					<#--
					<@s.textfield key="label.user.address.country" name="user.address.countryId" cssClass="span4" />
					-->
					<#assign groups=groups.entityList>
					<div class="control-group ">
						<label class="control-label" for="add_master_group_type">Group Name <span class="required">*</span></label>
							<div class="controls">
								<select name="user.group.id" id="groupselect">
								<option value="">-- Select Group --</option>
								<#list groups as a>
									<option value="${a.id}">${a.name}</option>
								</#list>
								</select>
							</div>
					</div>
					<div class="control-group ">
						<label class="control-label" for="add_master_group_type">Package Name <span class="required">*</span></label>
							<div class="controls">
								<select name="user.internetPackage.id" id="packageselect">
									<option value="">-- Select Package --</option>
								</select>
							</div>
					</div>
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
			
			$('#groupselect').change(function(){
				$.ajax({
			        url: "package/"+this.value+".json",
			        type: 'GET',
			        dataType: 'json', // added data type
			        success: function(res) {
			        	console.log(res);
			        	$('#packageselect').empty();
			        	$('#packageselect').append($('<option></option>').val("").html("-- Select Package --"));
			        	for (var i = 0; i < res.groupPackages.length; i++) {
			        		$('#packageselect').append($('<option></option>').val(res.groupPackages[i].internetPackage.id).html(res.groupPackages[i].internetPackage.name));
			        	}
			        }
			    });
				
			});
			
			$('.password').val('${pass!}');
		});
		</script>
	</body>
</html>