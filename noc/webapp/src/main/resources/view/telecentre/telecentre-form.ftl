<html>
	<head>
		<title><@s.text name="page.telecentre.title" /></title>
		<meta name="header" content="<@s.text name="page.telecentre.title" />">
	</head>
	<body>
		<div class="row-fluid">
			<div class="span10">
				<@s.form theme="bootstrap" cssClass="form-horizontal">
					<@s.hidden name="telecentre.id" />
					<@s.textfield key="label.master.telecentre.id" name="telecentre.telecentre_id" cssClass="span4" disabled="true" />
					<@s.textfield key="label.master.telecentre.name" required="true" name="telecentre.name" cssClass="span4" />
					<@s.textfield key="label.master.telecentre.latitude"  required="true" name="telecentre.latitude" cssClass="span4" />
					<@s.textfield key="label.master.telecentre.longitude" required="true" name="telecentre.longitude" cssClass="span4" />
					<div class="form-actions">
						<#if telecentre.id??>
						<@s.submit key="button.update" cssClass="btn btn-primary" />
						<#else>
						<@s.submit key="button.save" cssClass="btn btn-primary" />
						</#if>
						<@s.reset key="button.reset" cssClass="btn" />
						<span id="alert" class="alert alert-error hide"><@s.text name="message.password.doesntmatch" /></span>
					</div>
				</@s.form>
			</div>
			<table class="table table-striped table-condensed">
				<thead>
					<tr>
						<th>#</th>
						<th><@s.text name="label.telecentre.computer.name" /></th>
						<th><@s.text name="label.telecentre.computer.host" /></th>
						<th><@s.text name="label.telecentre.computer.error" /></th>
					</tr>
				</thead>
				<tbody>
					<#assign no = 1 />
					<#list telecentre.telecentreComputers as s>
					<tr>
						<td>${no}</td>
						<td>${s.name!}</td>
						<td>${s.host!}</td>
						<td>${s.error!}</td>
					</tr>
					<#assign no = no + 1 />
					</#list>
				</tbody>
			</table>
		</div>		
	</body>
</html>