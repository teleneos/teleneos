<html>
	<head>
		<title><@s.text name="page.monitoring.availability.title" /></title>
		<meta name="header" content="<@s.text name="page.monitoring.availability.header" />">
	</head>
	<body>
		<div class="row-fluid">
			<#include "/view/decorator/nav/monitoring-sidenav.ftl" />
			<div class="span10">
				<h3>Telecentre</h3>
				<table class="table table-striped table-condensed">
					<thead>
						<tr>
							<th class="span1">#</th>
							<th><@s.text name="label.telecentre.id" /></th>
							<th><@s.text name="label.telecentre.name" /></th>
							<th><@s.text name="label.telecentre.address" /></th>
							<th><@s.text name="label.telecentre.phone" /></th>
							<th><@s.text name="label.telecentre.email" /></th>
						</tr>
					</thead>
					<tbody>
						<#assign no = 1 />
						<tr>
							<td>${no}</td>
							<td><a href="<@s.url value="/tele/edit/${tele.id!}" />">${tele.id!}</a></td>
							<td>${tele.name!}</td>
							<td>${tele.address.street1!}</td>
							<td>${tele.phone!}</td>
							<td>${tele.email!}</td>
						</tr>
					</tbody>
				</table>
				
				<h4>Clients</h4>
				<table class="table table-striped table-condensed">
					<thead>
						<tr>
							<th class="span1">#</th>
							<th><@s.text name="label.monitoring.availability.clienthostid" /></th>
							<th><@s.text name="label.monitoring.availability.clienthost" /></th>
							<th><@s.text name="label.monitoring.availability.clientname" /></th>
							<th><@s.text name="label.monitoring.availability.laststatus" /></th>
							<th><@s.text name="label.monitoring.availability.clientlateststatus" /></th>
							<th><@s.text name="label.monitoring.availability.clientcurrentstatus" /></th>
						</tr>
					</thead>
					<tbody>
						<#assign no = 1 />
						<#list hosts.entityList as h>
						<tr>
							<td>${no}</td>
							<td>${h.hostid!}</td>
							<td>${h.host}</td>
							<td>${h.name!}</td>
							<td>${h.logInformation.createDate?string('dd-MM-yyyy HH:mm')}</td>
							<#if (h.available == '1')>
							<td>
								<img title="Offline" src="<@s.url value="/images/map/marker-comp-red.png" />" width="20" />
							</td>
							<#else>
							<td>
								<img title="Online" src="<@s.url value="/images/map/marker-comp-green.png" />" width="20" />
							</td>
							</#if>
							<#if (model.getDate(-5).compareTo(h.logInformation.createDate) > 0)>
							<td>
								<img title="Offline" src="<@s.url value="/images/map/marker-comp-red.png" />" width="20" />
							</td>
							<#else>
							<td>
								<img title="Online" src="<@s.url value="/images/map/marker-comp-green.png" />" width="20" />
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