<html>
	<head>
		<title><@s.text name="page.availability.title" /></title>
		<meta name="header" content="<@s.text name="page.availability.header" />">
	</head>
	<body>
		<div class="row-fluid">
			<#include "/view/decorator/nav/network-sidenav.ftl" />
			<div class="span10">
				
				<table class="table table-striped table-condensed">
					<thead>
						<tr>
							<th class="span1">#</th>
							<th>Host ID</th>
							<th>Host</th>
							<th>Name</th>
							<th>Status</th>
						</tr>
					</thead>
					<tbody>
						<#assign no = 1 />
						<#list hosts.result as h>
						<tr>
							<td>${no}</td>
							<td>${h.hostid!}</td>
							<td>${h.host!}</td>
							<td>${h.name!}</td>
							<td>
								<#if h.available == '1'>
								<span class="label label-success">Monitored</span>
								<#else>
								<span class="label label-important">Inactive</span>
								</#if>
							</td>
							<#assign no = no + 1 />
						</tr>
						</#list>
					</tbody>
				</table>
			</div>
		</div>
		<script type="text/javascript">
		$(function() {
		});
		</script>
	</body>
</html>