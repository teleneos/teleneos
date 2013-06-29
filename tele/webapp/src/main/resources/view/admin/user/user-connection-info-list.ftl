<html>
	<head>
		<title><@s.text name="page.statistic.title" /></title>
		<meta name="header" content="<@s.text name="page.statistic.title" />">
		<content tag="sidenav">/view/decorator/nav/admin-sidenav.ftl</content>
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
		<div class="block-content collapse in">
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
						<th><@s.text name="label.admin.onlineuser.download" /></th>
						<th><@s.text name="label.admin.onlineuser.upload" /></th>
						<th><@s.text name="label.admin.onlineuser.totalonline" /></th>
						<th class="span1"><@s.text name="label.admin.onlineuser.statistic" /></th>
						<th class="span1"><@s.text name="label.admin.onlineuser.package" /></th>
					</tr>
				</thead>
				<tbody>
					<#assign no = 1 + ((page - 1) * max) />
					<#list listacc.entityList as d>
					<tr>
						<td>${no}</td>
						<td><a href="<@s.url value="/admin/user/edit/${d[0]!}" />">${d[0]!}</a></td>
						<td>${byteString(d[1]!0)}</td>
						<td>${byteString(d[2]!0)}</td>
						<td>${timeFormat(d[3]!0)}</td>
						<td style="text-align: center;">
							<a href="<@s.url value="/admin/user/report/${d[0]!}" />"><i class="icon-list-alt"></i></a>
						</td>
						<td style="text-align: center;">
							<a href="<@s.url value="/admin/user/subscription/${d[0]!}" />"><i class="icon-qrcode"></i></a>
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
				items: ${listacc.rowCount?string('#')},
				itemsOnPage: ${max?string('#')},
				currentPage: ${(listacc.currentPage + 1)?string('#')},
				hrefTextPrefix: '?q=${q}&page='
			});
		});
		</script>
	</body>
</html>