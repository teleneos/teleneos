<html>
	<head>
		<title><@s.text name="page.monitoring.availability.title" /></title>
		<meta name="header" content="<@s.text name="page.monitoring.availability.header" />">
	</head>
	<body>
		<div class="row-fluid">
			<#include "/view/decorator/nav/monitoring-sidenav.ftl" />
			<div class="span10">
				<table class="table table-striped table-condensed">
					<thead>
						<tr>
							<th class="span1">#</th>
							<th><@s.text name="label.monitoring.availability.teleid" /></th>
							<th><@s.text name="label.monitoring.availability.laststatus" /></th>
							<th><@s.text name="label.monitoring.availability.status" /></th>
						</tr>
					</thead>
					<tbody>
						<#assign no = 1 />
						<#list availabilities.entityList as h>
						<tr>
							<td>${no}</td>
							<td><a href="<@s.url value="/tele/edit/${h[0]!}" />">${h[0]!}</a></td>
							<td>${h[1]?string('dd-MM-yyyy HH:mm')}</td>
							<#if (model.getDate(-1).compareTo(h[1]) > 0)>
							<td>
								<a href="<@s.url value="/monitoring/availability/clients/${h[0]}" />">
									<img title="Offline" src="<@s.url value="/images/map/marker-comp-red.png" />" width="20" />
								</a>
							</td>
							<#else>
							<td>
								<a href="<@s.url value="/monitoring/availability/clients/${h[0]}" />">
									<img title="Online" src="<@s.url value="/images/map/marker-comp-green.png" />" width="20" />
								</a>
							</td>
							</#if>
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