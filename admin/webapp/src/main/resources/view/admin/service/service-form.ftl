<html>
	<head>
		<title><@s.text name="page.service.title" /></tile>
		<meta name="header" content="<@s.text name="page.service.header" />">
	</head>
	<body>
		<div class="row-fluid">
			<#include "/view/decorator/nav/admin-sidenav.ftl" />
			<div class="span10">
				<@s.form theme="bootstrap" cssClass="form-horizontal">
					<@s.hidden name="service.id" />
					<@s.textfield key="label.admin.service.name" required="true" name="service.name" cssClass="span4" />
					<div class="control-group ">
						<label class="control-label">
							<@s.text name="label.admin.service.servicetype" />
						</label>
						<div class="controls">
							<label class="radio inline">
								<input type="radio" name="service.type" value="PREPAID" checked>
								<@s.text name="label.admin.service.servicetype.prepaid" />
							</label>
							<label class="radio inline">
								<input type="radio" name="service.type" value="POSTPAID">
								<@s.text name="label.admin.service.servicetype.postpaid" />
							</label>
						</div>
					</div>
					<@s.checkboxlist key="label.admin.service.limitquota" name="service.limitQuota" list="{'true'}" listValue="" />
					<@s.checkboxlist key="label.admin.service.limittime" name="service.limitTime" list="{'true'}" listValue="" />
					<@s.textfield key="label.admin.service.trafficquota" name="service.quotaLimit" cssClass="span4" />
					<@s.textfield key="label.admin.service.timequota" name="service.timeLimit" cssClass="span4" />
					<hr>
					<div class="control-group ">
						<label class="control-label">
							<@s.text name="label.admin.service.pricecalc" />
						</label>
						<div class="controls">
							<label class="radio">
								<input type="radio" name="service.calculation" value="ONLINE_TIME" checked>
								<@s.text name="label.admin.service.pricecalc.time" />
							</label>
							<label class="radio">
								<input type="radio" name="service.calculation" value="DOWNLOAD_TRAFFIC">
								<@s.text name="label.admin.service.pricecalc.download" />
							</label>
							<label class="radio">
								<input type="radio" name="service.calculation" value="UPLOAD_TRAFFIC">
								<@s.text name="label.admin.service.pricecalc.upload" />
							</label>
						</div>
					</div>
					<@s.textfield key="label.admin.service.unitprice" name="service.unitPrice" cssClass="span4" />
					
					<div class="form-actions">
						<#if service.id??>
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