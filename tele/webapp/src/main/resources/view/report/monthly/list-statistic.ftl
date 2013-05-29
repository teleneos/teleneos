<html>
	<head>
		<title><@s.text name="page.statistic.title" /></title>
		<meta name="header" content="<@s.text name="page.statistic.title" />">
		<link rel="stylesheet" type="text/css" href="<@s.url value="/styles/datepicker.css" />" />
		<script type="text/javascript" src="<@s.url value="/scripts/bootstrap-datepicker.js" />"></script>
		<script type="text/javascript">
		$(function(){
			$('#date').datepicker({
				minViewMode:'months',
				format:'mm/yyyy'
			});
		});
		</script>
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
			<#include "/view/decorator/nav/report-statistic-sidenav.ftl" />
			<div class="span10">
				<div class="row-fluid">
				
				<form class="form-inline span12" method="get">
					<div class=" pull-right">
						Date : <input id="date" type="text" name="date" value="${date!}" readonly /> -
						<button class="btn" type="submit">
							<i class="icon-search"></i>
							<@s.text name="button.generate" />
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
						<#list statistics.entityList as d>
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
		</div>		
		<script type="text/javascript" src="<@s.url value="/scripts/jq/pagination.js" />"></script>
		<script type="text/javascript">
		$(function() {
			$('#pagination').pagination({
				items: ${statistics.rowCount?string('#')},
				itemsOnPage: ${max?string('#')},
				currentPage: ${(statistics.currentPage + 1)?string('#')},
				hrefTextPrefix: '?q=${q}&page='
			});
		});
		</script>
	</body>
</html>