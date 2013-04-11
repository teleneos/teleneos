<html>
	<head>
		<title><@s.text name="page.packagemanager.title" /></title>
		<meta name="header" content="<@s.text name="page.packagemanager.header" />">
		<link rel="stylesheet" type="text/css" href="<@s.url value="/styles/datepicker.css" />" />
		<script type="text/javascript" src="<@s.url value="/scripts/bootstrap-datepicker.js" />"></script>
	</head>
	<body>
		<div class="row-fluid">
			<#include "/view/decorator/nav/master-sidenav.ftl" />
			<div class="span10">
				<@s.form id="packageManager" theme="bootstrap" cssClass="form-horizontal" method="post">
					<@s.hidden name="internetPackage.id" />
					<@s.textfield key="label.master.packagemanager.code" required="true" name="internetPackage.code" cssClass="span4" readonly="${internetPackage.id?exists?string}" />
					<@s.textfield key="label.master.packagemanager.name" required="true" name="internetPackage.name" cssClass="span4" />
					<@s.select key="label.master.group.paymentmethod" name="group.paymentMethod" list={'PREPAID' : 'Prepaid', 'POSTPAID' : 'Postpaid'} listKey="key" listValue="value" />
					<#--
					<div class="control-group " id="package">
						<label class="control-label" for="packageManager_internetPackage_type"><span class="required">*</span> Package Type </label>
						<div class="controls">
							<select name="internetPackage.type" id="packageManager_internetPackage_type">
								<option value="">-- Select Package --</option>
							    <option value="COUNTDOWN">Countdown</option>
							    <option value="FIXTIME">Fixtime</option>
							</select>
						</div>
					</div>
					<div class="control-group" id="variables">
						<label class="control-label" for="add_master_packagemanager_status"><span class="required">*</span> Variable</label>
							<div class="controls">
								<input type="text" name="" value="${internetPackage.variable?number_to_datetime?string("MM/dd/yyyy")}" id="variabledate" class="span4" class="date"/> 
									<span id="var">
										<input class="span1" id="variablemin" name="internetPackage.variable" value="${internetPackage.variable}" type="text">
											<select class="span2" name="q">
												<option value="${(60 * 24)?string('#')}">day</option>
												<option value="${(60 * 24 * 7)?string('#')}">week</option>
												<option value="${(60 * 24 * 30)?string('#')}">mon</option>
											</select>
									</span>
									
										<input type="text" name="" value="${internetPackage.variable}" id="variablemin" class="span4" class="min"/>&nbsp;&nbsp;<span class="min">minutes</span>
									
							</div>
					</div>
					-->
	 				<#--<@s.textfield key="label.master.packagemanager.variable" required="true" name="internetPackage.variable" cssClass="span4" id="variable" /> -->
					<@s.textfield key="label.master.packagemanager.price" required="true" name="internetPackage.price" cssClass="span4" />
					<div class="control-group " >
						<label class="control-label" ><span class="required">*</span> Time</label>
						<div class="controls">
							<input class="span2" name="internetPackage.monthDay" value="" type="text">
							<select name="q" style="width: 70px;">
								<option value="${(60 * 24)?string('#')}">day</option>
								<option value="${(60 * 24 * 7)?string('#')}">week</option>
								<option value="${(60 * 24 * 30)?string('#')}">mon</option>
							</select>
						</div>
					</div>
					<div class="control-group " >
						<label class="control-label" ><span class="required">*</span> Bandwidth Quota</label>
						<div class="controls">
							<input class="span2" name="internetPackage.bandwidth" value="" type="text">
							<select name="q" style="width: 70px;">
								<option value="">MB</option>
								<option value="">GB</option>
							</select>
						</div>
					</div>
					<div class="control-group " >
						<label class="control-label" ><span class="required">*</span> Speed</label>
						<div class="controls">
							<input class="span2" name="internetPackage.bandwidth" value="" type="text">
							<select name="q" style="width: 70px;">
								<option value="">kbps</option>
								<option value="">mbps</option>
							</select>
						</div>
					</div>
					<div class="control-group " >
						<label class="control-label" ><span class="required">*</span> Next Speed</label>
						<div class="controls">
							<input class="span2" name="internetPackage.bandwidth" value="" type="text">
							<select name="q" style="width: 70px;">
								<option value="">kbps</option>
								<option value="">mbps</option>
							</select>
						</div>
					</div>
					<@s.select key="label.master.packagemanager.status" name="internetPackage.status" list={'ENABLE' : 'Active', 'DISABLE' : 'Inactive'} listKey="key" listValue="value" />
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
		<script type="text/javascript">
		$(function(){
			$('#variabledate').datepicker().keypress(false);
			$("#variabledate").addClass("hide");
			$("#variablemin").attr('name', 'variable');
			$('#packageManager_internetPackage_type').change(function() {
				if(this.value=="COUNTDOWN") {
					$("#variables").fadeIn();
					$("#variabledate").addClass("hide");
					$("#variablemin").removeClass("hide");
					$(".min").fadeIn();
					$("#variablemin").attr('name', 'variable');
					$("#variabledate").attr('name', '');
				} else if(this.value=="FIXTIME") {
					$("#variables").fadeIn();
					$("#variabledate").removeClass("hide");
					$("#variablemin").addClass("hide");
					$(".min").hide();
					$("#variablemin").attr('name', '');
					$("#variabledate").attr('name', 'variable');
				} else {
					$("#variables").hide();
				}
			}).change();
			$('select[name="group.paymentMethod"]').change(function() {
				if ($(this).val() == 'POSTPAID') {
					$('#package').hide();
					$("#packageManager_internetPackage_type").val("").attr('selected',true);
					$("#variables").hide();
					$("#variablemin").attr('name', '');
					$("#variabledate").attr('name', '');
					$(this).after(cont);
				} else {
					$('#package').show();
					$('#var').remove();
				}
			}).change();
		});
		</script>		
	</body>
</html>