<html>
	<head>
		<title><@s.text name="page.onlineuser.title" /></tile>
		<meta name="header" content="<@s.text name="page.onlineuser.header" />">
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
		<div class="row-fluid">
			<#include "/view/decorator/nav/admin-sidenav.ftl" />
			<div class="span10">
				<table class="table table-striped table-condensed">
					<thead>
						<tr>
							<th class="span1">#</th>
							<th>ID</th>
							<th>Username</th>
							<th>IP Address</th>
							<th>Start Time</th>
							<th>Download</th>
							<th>Upload</th>
						</tr>
					</thead>
					<tbody>
						<#assign no = 1 />
						<#list accts.entityList as a>
						<tr>
							<td>${no}</td>
							<td>${a.radacctid?string('#')}</td>
							<td>${a.username!}</td>
							<td>${a.nasipaddress!}</td>
							<td>${a.acctstarttime?string('dd-MM-yyyy')} <strong>${a.acctstarttime?string('hh:mm:ss')}</strong></td>
							<td>${byteString(a.acctinputoctets)}</td>
							<td>${byteString(a.acctoutputoctets)}</td>
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
				hrefTextPrefix: '?q=${q}&page='
			});
		});
		</script>
	</body>
</html>