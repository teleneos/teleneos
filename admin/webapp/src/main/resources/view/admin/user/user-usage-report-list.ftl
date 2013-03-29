<html>
	<head>
		<title><@s.text name="page.onlineuser.title" /></title>
		<meta name="header" content="<@s.text name="page.userstat.header" />">
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
		<div class="row-fluid">
			<#include "/view/decorator/nav/admin-sidenav.ftl" />
			<div class="span10">
				<div class="row-fluid">
					<table class="table-condensed table-bordered">
						<tr>
							<td class="span2"><strong><@s.text name="label.admin.onlineuser.username" /></strong></td>
							<td class="span3">${user.user.username}</td>
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
							<td class="span2"></td>
							<td class="span3"></td>
						</tr>
					</table>
				</div>
				<hr>
				<table class="table table-striped table-condensed">
					<thead>
						<tr>
							<th class="span1">#</th>
							<th><@s.text name="label.admin.onlineuser.ipaddress" /></th>
							<th><@s.text name="label.admin.onlineuser.accid" /></th>
							<th><@s.text name="label.admin.onlineuser.starttime" /></th>
							<th><@s.text name="label.admin.onlineuser.stoptime" /></th>
							<th><@s.text name="label.admin.onlineuser.download" /></th>
							<th><@s.text name="label.admin.onlineuser.upload" /></th>
							<th><@s.text name="label.admin.onlineuser.package" /></th>
						</tr>
					</thead>
					<tbody>
						<#assign no = 1 + ((page - 1) * max) />
						<#list listacc.entityList as x>
						<#assign a = x[0] />
						<#assign b = x[2] />
						<tr>
							<td>${no}</td>
							<td title="${a.callingstationid!}">${a.framedipaddress!}</td>
							<td>${a.radacctid!}</td>
							<td>${a.acctstarttime?string('dd-MM-yyyy')} <strong>${a.acctstarttime?string('hh:mm:ss')}</strong></td>
							<td>
								${a.acctstoptime?string('dd-MM-yyyy')} <strong>${a.acctstoptime?string('hh:mm:ss')}</strong>
								<span class="label">${timeFormat(a.acctsessiontime)}</span>
							</td>
							<td>${byteString(a.acctinputoctets)}</td>
							<td>${byteString(a.acctoutputoctets)}</td>
							<td>${b.internetPackage.name}</td>
							<td></td>
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
				items: ${accts.rowCount?string('#')},
				itemsOnPage: ${max?string('#')},
				currentPage: ${(accts.currentPage + 1)?string('#')},
				hrefTextPrefix: '?page='
			});
		});
		</script>
	</body>
</html>