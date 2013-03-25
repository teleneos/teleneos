<html>
	<head>
		<title><@s.text name="page.packagemanager.title" /></title>
		<meta name="header" content="<@s.text name="page.packagemanager.header" />">
		<link rel="stylesheet" type="text/css" href="<@s.url value="/styles/datepicker.css" />" />
		<script type="text/javascript" src="<@s.url value="/scripts/bootstrap-datepicker.js" />"></script>
		<script type="text/javascript">
		$(function(){
			$('#variabledate').datepicker();
			$("#variabledate").addClass("hide");
			$("#variablemin").attr('name', 'variable');
			$('#type').change(function(){
				if(this.value=="0"){
					$("#variabledate").addClass("hide");
					$("#variablemin").removeClass("hide");
					$(".min").removeClass("hide");
					$("#variablemin").attr('name', 'variable');
					$("#variabledate").attr('name', '');
				}else if(this.value=="1"){
					$("#variabledate").removeClass("hide");
					$("#variablemin").addClass("hide");
					$(".min").addClass("hide");
					$("#variablemin").attr('name', '');
					$("#variabledate").attr('name', 'variable');
				}
			});
		});
		</script>
	</head>
	<body>
		<div class="row-fluid">
			<#include "/view/decorator/nav/master-sidenav.ftl" />
			<div class="span10">
				<@s.form theme="bootstrap" cssClass="form-horizontal" method="post" action="add">
					<@s.hidden name="internetPackage.id" />
					<@s.textfield key="label.master.packagemanager.code" required="true" name="internetPackage.code" cssClass="span4" />
					<@s.textfield key="label.master.packagemanager.name" required="true" name="internetPackage.name" cssClass="span4" />
					<div class="control-group ">
						<label class="control-label" for="add_master_packagemanager_type">Package Type <span class="required">*</span></label>
							<div class="controls">
								<select name="type" id="type">
									<option value="0">Countdown</option>
									<option value="1">Fixtime</option>
								</select>
							</div>
					</div>
					<div class="control-group ">
						<label class="control-label" for="add_master_packagemanager_status">Variable <span class="required">*</span></label>
							<div class="controls">
								<input type="text" name="" value="" id="variabledate" class="span4" class="date"/>
								<input type="text" name="" value="" id="variablemin" class="span4" class="min"/> <span class="min">minutes</span>
							</div>
					</div>
<!-- 					<@s.textfield key="label.master.packagemanager.variable" required="true" name="internetPackage.variable" cssClass="span4" id="variable" /> -->
<!-- 					<@s.textfield key="label.master.packagemanager.price" required="true" name="internetPackage.price" cssClass="span4" /> -->
					<div class="control-group ">
						<label class="control-label" for="add_master_packagemanager_status">Package Status <span class="required">*</span></label>
							<div class="controls">
								<select name="status">
									<option value="0">Enable</option>
									<option value="1">Disable</option>
								</select>
							</div>
					</div>
					<div class="form-actions">
						<#if internetPackage.id??>
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
	</body>
</html>