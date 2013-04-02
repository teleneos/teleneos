<html>
	<head>
		<title><@s.text name="page.onlineuser.title" /></title>
		<meta name="header" content="<@s.text name="page.user.title" />">
	</head>
	<body>
		<#function byteString byte>
			<#if byte &gt; (1024 * 1024 * 1024)>
				<#return (byte / (1024 * 1024 * 1024))?string('0.#') + 'GB' />
			<#elseif byte &gt; (1024 * 1024)>
				<#return (byte / (1024 * 1024))?string('0.#') + 'MB' />
			<#elseif byte &gt; 1024>
				<#return (byte / 1024)?string('0.#') + 'KB' />
			<#else>
				<#return byte?string('0.#') + 'B' />
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
		<div class="row-fluid">
			<#include "/view/decorator/nav/admin-sidenav.ftl" />
			<div class="span10">
				<div class="row-fluid">
				<a class="btn btn-primary span2" href="<@s.url value="/admin/user/add" />">
					<i class="icon-plus icon-white"></i>
					<@s.text name="button.add" />
				</a>
				<form class="form-inline span10" method="get">
					<div class="input-append pull-right">
						<input type="text" name="q" value="${q}" />
						<button class="btn">
							<i class="icon-search"></i>
							<@s.text name="button.search" />
						</button>
					</div>
				</form>
				</div>
				<table class="table table-striped table-condensed">
					<thead>
						<tr>
							<th class="span1">#</th>
							<th><@s.text name="label.login.username" /></th>
							<th><@s.text name="label.user.name" /></th>
							<th><@s.text name="label.admin.onlineuser.download" /></th>
							<th><@s.text name="label.admin.onlineuser.upload" /></th>
							<th><@s.text name="label.admin.onlineuser.totalonline" /></th>
							<th><@s.text name="label.admin.group.name" /></th>
							<th><@s.text name="label.master.packagemanager.name" /></th>
						</tr>
					</thead>
					<tbody>
						<#assign no = 1 + ((page - 1) * max) />
						<#list details.entityList as d>
						<#assign u = d[0] />
						<tr <#if u.user.logInformation.statusFlag == 'INACTIVE'>class="muted"</#if>>
							<td>${no}</td>
							<td><a href="<@s.url value="/admin/user/edit/${u.user.username}" />">${u.user.username!}</a></td>
							<td>${u.name.first!} ${u.name.last!}</td>
							<td>${byteString(d[1]!0)}</td>
							<td>${byteString(d[2]!0)}</td>
							<td>${timeFormat(d[3]!0)}</td>
							<td><#if u.group??>${u.group.name!}<#else></#if></td>
							<td><#if u.internetPackage??>${u.internetPackage.name!}<#else></#if></td>
							<td>
								<a href="<@s.url value="/admin/user/report/${u.user.username!}" />" title="<@s.text name="tooltip.user.report"><@s.param>${u.user.username!}</@s.param></@s.text>">
									<i class="icon-list"></i>
								</a>
							</td>
							<#--
							<td>
								<a href="<@s.url value="/admin/user/disconnect/${a.username!}" />" title="<@s.text name="tooltip.onlineuser.disconnect"><@s.param>${a.username!}</@s.param></@s.text>">
									<i class="icon-off"></i>
								</a>
							</td>
							-->
						</tr>
						<#assign no = no + 1 />
						</#list>
					</tbody>
				</table>
				<div id="pagination"></div>
			</div>
		</div>		
		<script type="text/javascript" src="<@s.url value="/scripts/jq/pagination.js" />"></script>
		<script type="text/javascript">
		$(function() {
			$('#pagination').pagination({
				items: ${details.rowCount?string('#')},
				itemsOnPage: ${max?string('#')},
				currentPage: ${(details.currentPage + 1)?string('#')},
				hrefTextPrefix: '?q=${q}&page='
			});
		});
		</script>
	</body>
</html>