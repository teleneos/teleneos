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
					<div class="control-group " id="package">
						<label class="control-label" for="packageManager_internetPackage_type">Package Type <span class="required">*</span></label>
						<div class="controls">
							<select name="internetPackage.type" id="packageManager_internetPackage_type">
								<option value="">-- Select Package --</option>
							    <option value="COUNTDOWN">Countdown</option>
							    <option value="FIXTIME">Fixtime</option>
							</select>
						</div>
					</div>
					<div class="control-group" id="variables">
						<label class="control-label" for="add_master_packagemanager_status">Variable <span class="required">*</span></label>
							<div class="controls">
								<input type="text" name="" value="${internetPackage.variable?number_to_datetime?string("MM/dd/yyyy")}" id="variabledate" class="span4" class="date"/>
								<input type="text" name="" value="${internetPackage.variable}" id="variablemin" class="span4" class="min"/>&nbsp;&nbsp;<span class="min">minutes</span>
							</div>
					</div>
	 				<#--<@s.textfield key="label.master.packagemanager.variable" required="true" name="internetPackage.variable" cssClass="span4" id="variable" /> -->
					<@s.textfield key="label.master.packagemanager.price" required="true" name="internetPackage.price" cssClass="span4" />
					<@s.select key="label.master.packagemanager.status" name="internetPackage.status" list={'ENABLE' : 'Enable', 'DISABLE' : 'Disable'} listKey="key" listValue="value" />
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
					var cont = $('<span id="var"></span>');
					var txtfld = $('<input class="span1" type="text" name="internetPackage.variable" value="${internetPackage.variable!}" />');
					var cmb = $('<select class="span2" name="q"></select>');
					cmb.append('<option value="1">Minutes</option>');
					cmb.append('<option value="60">Hours</option>');
					cmb.append('<option value="${(60 * 24)?string('#')}">Days</option>');
					cmb.append('<option value="${(60 * 24 * 7)?string('#')}">Weeks</option>');
					cmb.append('<option value="${(60 * 24 * 30)?string('#')}">Months</option>');
					cont.append('&nbsp;').append(txtfld).append('&nbsp;').append(cmb).append('&nbsp;once');
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