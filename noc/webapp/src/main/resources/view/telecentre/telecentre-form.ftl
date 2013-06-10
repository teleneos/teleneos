<html>
	<head>
		<title><@s.text name="page.telecentre.title" /></title>
		<meta name="header" content="<@s.text name="page.telecentre.title" />">
	</head>
	<body>
		<div class="row-fluid">
			<#include "/view/decorator/nav/telecentre-sidenav.ftl" />
			<div class="span10">
				<@s.form theme="bootstrap" cssClass="form-horizontal">
					<#if telecentre.id??>
					<@s.textfield key="label.telecentre.id" name="telecentre.id" cssClass="span4" readonly="true" />
					</#if>
					<@s.textfield key="label.telecentre.name" required="true" name="telecentre.name" cssClass="span4" />
					<#if telecentre.id??>
					<@s.textfield key="label.telecentre.password" name="telecentre.password" cssClass="span4" readonly="true" />
					</#if>
					<@s.textfield key="label.telecentre.phone" required="true" name="telecentre.phone" cssClass="span4" />
					<@s.textfield key="label.telecentre.email" required="true" name="telecentre.email" cssClass="span4" />
					<@s.textfield key="label.telecentre.address" required="true" name="telecentre.address.street1" cssClass="span4" />
					<@s.textfield key="label.telecentre.zip" required="true" name="telecentre.address.zip" cssClass="span2" />
					<@s.textfield key="label.telecentre.latitude"  required="false" name="telecentre.lat" cssClass="span4" />
					<@s.textfield key="label.telecentre.longitude" required="false" name="telecentre.lng" cssClass="span4" />
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
			<#--
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
			-->
		</div>		
	</body>
</html>