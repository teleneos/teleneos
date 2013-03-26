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
				<div class="row-fluid">
				<form class="form-inline span10 offset2" method="get">
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
							<th><@s.text name="label.admin.onlineuser.id" /></th>
							<th><@s.text name="label.admin.onlineuser.username" /></th>
							<th><@s.text name="label.admin.onlineuser.ipaddress" /></th>
							<th><@s.text name="label.admin.onlineuser.starttime" /></th>
							<th><@s.text name="label.admin.onlineuser.download" /></th>
							<th><@s.text name="label.admin.onlineuser.upload" /></th>
							<th></th>
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
							<td>
								<a href="<@s.url value="/admin/user/disconnect/${a.calledstationid!}" />" title="<@s.text name="tooltip.onlineuser.disconnect"><@s.param>${a.username!}</@s.param></@s.text>">
									<i class="icon-off"></i>
								</a>
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
				items: ${accts.rowCount?string('#')},
				itemsOnPage: ${max?string('#')},
				currentPage: ${(accts.currentPage + 1)?string('#')},
				hrefTextPrefix: '?q=${q}&page='
			});
		});
		</script>
	</body>
</html>