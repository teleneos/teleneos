<html>
	<head>
		<title><@s.text name="page.packagemanager.title" /></tile>
		<meta name="header" content="<@s.text name="page.packagemanager.header" />">
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
								<select name="type">
									<option value="0">Countdown</option>
									<option value="1">Fixtime</option>
								</select>
							</div>
					</div>
					<@s.textfield key="label.master.packagemanager.variable" required="true" name="internetPackage.variable" cssClass="span4" />
					<@s.textfield key="label.master.packagemanager.price" required="true" name="internetPackage.price" cssClass="span4" />
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