<html>
	<head>
		<title><@s.text name="page.group.title" /></tile>
		<meta name="header" content="<@s.text name="page.group.header" />">
	</head>
	<body>
		<div class="row-fluid">
			<#include "/view/decorator/nav/master-sidenav.ftl" />
			<div class="span10">
				<@s.form theme="bootstrap" cssClass="form-horizontal" method="post" action="add">
					<@s.hidden name="group.id" />
					<@s.textfield key="label.master.group.code" required="true" name="group.code" cssClass="span4" />
					<@s.textfield key="label.master.group.name" required="true" name="group.name" cssClass="span4" />
					<div class="control-group ">
						<label class="control-label">
							<@s.text name="label.master.group.pms" />
						</label>
						<div class="controls">
							<label class="radio inline">
								<input type="radio" name="group.pms" value="true" checked>
								Yes
							</label>
							<label class="radio inline">
								<input type="radio" name="group.pms" value="false">
								No
							</label>
						</div>
					</div>
					<div class="control-group ">
						<label class="control-label">
							<@s.text name="label.master.group.club" />
						</label>
						<div class="controls">
							<label class="radio inline">
								<input type="radio" name="group.club" value="true" checked>
								Yes
							</label>
							<label class="radio inline">
								<input type="radio" name="group.club" value="false">
								No
							</label>
						</div>
					</div>
					<div class="control-group ">
						<label class="control-label">
							<@s.text name="label.master.group.foc" />
						</label>
						<div class="controls">
							<label class="radio inline">
								<input type="radio" name="foc" value="true" checked>
								Yes
							</label>
							<label class="radio inline">
								<input type="radio" name="foc" value="false">
								No
							</label>
						</div>
					</div>
					<div class="control-group ">
						<label class="control-label">
							<@s.text name="label.master.group.package" />
						</label>
						<div class="controls">
							<select name="packageIds" multiple>
							<#list packages.entityList as s>
							<option value="${s.id}">${s.name}</option>
							</#list>
							</select>
						</div>
					</div>
					<div class="form-actions">
						<#if group.id??>
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
	<script type="text/javascript">
	
	</script>
</html>