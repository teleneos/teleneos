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
					<@s.select key="label.master.group.paymentmethod" name="internetPackage.paymentMethod" list={'PREPAID' : 'Prepaid', 'POSTPAID' : 'Postpaid'} listKey="key" listValue="value" />
					<@s.textfield key="label.master.packagemanager.price" required="true" name="internetPackage.price" cssClass="span4" />
					<div class="control-group " >
						<label class="control-label" ><span class="required">*</span> Time :</label>
						<div class="controls">
							<input class="span2" name="internetPackage.time" value="" type="text">
							<select name="qt" style="width: 70px;">
								<option value="${(60 * 24)?string('#')}">day</option>
								<option value="${(60 * 24 * 7)?string('#')}">week</option>
								<option value="${(60 * 24 * 30)?string('#')}">mon</option>
							</select>
						</div>
					</div>
					<div class="control-group " >
						<label class="control-label" ><span class="required">*</span> Bandwidth Quota :</label>
						<div class="controls">
							<input class="span2" name="internetPackage.quota" value="" type="text">
							<select name="qb" style="width: 70px;">
								<option value="1">MB</option>
								<option value="1000">GB</option>
							</select>
							<i class="icon-info-sign s2b_tooltip" title="Value zero means unlimited"></i>
						</div>
					</div>
					<div class="control-group " >
						<label class="control-label" ><span class="required">*</span> Speed :</label>
						<div class="controls">
							<input class="span2" name="internetPackage.speed" value="" type="text">
							<select name="qs" style="width: 70px;">
								<option value="1">kbps</option>
								<option value="1000">mbps</option>
							</select>
						</div>
					</div>
					<div class="control-group " >
						<label class="control-label"><span class="required">*</span> Next Speed :</label>
						<div class="controls">
							<input class="span2" name="internetPackage.nextSpeed" value="" type="text">
							<select name="qns" style="width: 70px;">
								<option value="1">kbps</option>
								<option value="1000">mbps</option>
							</select>
						</div>
					</div>
					<@s.select key="label.master.packagemanager.status" name="internetPackage.logInformation.statusFlag" list={'ACTIVE' : 'Active', 'INACTIVE' : 'Inactive'} listKey="key" listValue="value" />
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
			
		});
		</script>
	</body>
</html>