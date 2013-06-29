<html>
	<head>
		<title><@s.text name="page.subscription.title" /></title>
		<meta name="header" content="<@s.text name="page.subscription.header" />">
		<content tag="sidenav">/view/decorator/nav/admin-sidenav.ftl</content>
	</head>
	<body>
		<#function byteString byte>
			<#if byte &gt; (1024 * 1024 * 1024)>
				<#return (byte / (1024 * 1024 * 1024))?string('0.#') + ' GB' />
			<#elseif byte &gt; (1024 * 1024)>
				<#return (byte / (1024 * 1024))?string('0.#') + ' MB' />
			<#elseif byte &gt; 1024>
				<#return (byte / 1024)?string('0.#') + ' KB' />
			<#else>
				<#return byte?string('0.#') + ' B' />
			</#if>
		</#function>
		<#function timeFormat time>
			<#if time &gt; (60 * 60 * 24 * 7)>
				<#return (time / (60 * 60 * 24 * 7))?string('#') + ' Weeks' />
			<#elseif time &gt; (60 * 60 * 24)>
				<#return (time / (60 * 60 * 24))?string('#') + ' Days' />
			<#elseif time &gt; (60 * 60)>
				<#return (time / (60 * 60))?string('#') + ' Hours' />
			<#elseif time &gt; 60>
				<#return (time / 60)?string('#') + ' Min' />
			<#else>
				<#return time?string('#') + ' Sec' />
			</#if>
		</#function>
		<div class="block-content collapse in">
			<#assign status = ['In use', 'Active', 'End'] />
			<div class="row-fluid">
			<table class="table-condensed table-bordered">
					<tr>
						<td class="span2"><strong><@s.text name="label.admin.onlineuser.username" /></strong></td>
						<td class="span3">${user.user.username!}</td>
						<td class="span2"><strong><@s.text name="label.user.name" /></strong></td>
						<td class="span3">${user.name.first!} ${user.name.last!}</td>
					</tr>
					<tr>
						<td class="span2"><strong><@s.text name="label.admin.onlineuser.totaldownload" /></strong></td>
						<td class="span3">${byteString(statistic[0]!0)}</td>
						<td class="span2"><strong><@s.text name="label.admin.onlineuser.totalupload" /></strong></td>
						<td class="span3">${byteString(statistic[1]!0)}</td>
					</tr>
					<tr>
						<td class="span2"><strong><@s.text name="label.admin.onlineuser.totalonline" /></strong></td>
						<td class="span3">${timeFormat(statistic[2]!0)}</td>
						<td class="span2"><strong><@s.text name="label.admin.onlineuser.activepackage" /></strong></td>
						<#if userPackage??>
						<#assign ip = userPackage.internetPackage />
						<td class="span3"><a title="${ip.name!}" href="<@s.url value="/master/packages?q=${ip.code!}" />">${ip.code!}</a></td>
						<#else>
						<td class="span3">-</td>
						</#if>
					</tr>
				</table>
			</div>
			<hr>
			<table class="table table-striped table-condensed">
				<thead>
					<tr>
						<th class="span1">#</th>
						<th colspan="2"><@s.text name="label.master.packagemanager.name" /></th>
						<th><@s.text name="label.user.subscription.enddate" /></th>
						<th><@s.text name="label.user.status" /></th>
					</tr>
				</thead>
				<tbody>
					<@s.url var="packageUrl" value="/master/packages?q=" />
					<#assign no = 1 + ((page - 1) * max) />
					<#list userPackages.entityList as p>
					<tr>
						<td>${no}</td>
						<td><a href="${packageUrl + p.internetPackage.code!}">${p.internetPackage.code!}</a></td>
						<td><a href="${packageUrl + p.internetPackage.code!}">${p.internetPackage.name!}</a></td>
						<td><#if p.endDate??>${p.endDate?string('dd-MM-yyyy HH:mm:ss')}<#else>-</#if></td>
						<td <#if (p.status.ordinal() < 2)>title="<@s.text name="label.user.subscription.quota" /> ${byteString(p.quotaBalance)}"</#if>>
							<#assign label = ['success', 'success', 'important'] />
							<span class="label label-${label[p.status.ordinal()]}">${status[p.status.ordinal()]}</span>
						</td>
					</tr>
					<#assign no = no + 1 />
					</#list>
				</tbody>
			</table>
			<div id="pagination"></div>
		</div>		
		<script type="text/javascript" src="<@s.url value="/scripts/jq/pagination.js" />"></script>
		<script type="text/javascript">
		$(function() {
			$('#pagination').pagination({
				items: ${userPackages.rowCount?string('#')},
				itemsOnPage: ${max?string('#')},
				currentPage: ${(userPackages.currentPage + 1)?string('#')},
				hrefTextPrefix: '?page='
			});
		});
		</script>
	</body>
</html>