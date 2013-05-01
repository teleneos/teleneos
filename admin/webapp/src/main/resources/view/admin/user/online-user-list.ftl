<html>
	<head>
		<title><@s.text name="page.onlineuser.title" /></title>
		<meta name="header" content="<@s.text name="page.onlineuser.header" />">
		<script type="text/javascript" src="<@s.url value="/scripts/bootbox.min.js" />"></script>
		<script type="text/javascript" src="<@s.url value="/scripts/cimande-popup.js" />"></script>
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
							<th><@s.text name="label.admin.onlineuser.package" /></th>
							<th></th>
						</tr>
					</thead>
					<tbody>
						<@s.url value="/admin/user/report/" var="reportUrl" />
						<#assign no = 1 + ((page - 1) * max) />
						<#list listacc.entityList as l>
						<#assign a = l[0] />
						<#assign history = l[1] />
						<tr>
							<td>${no}</td>
							<td>${a.radacctid?string('#')}</td>
							<td><a href="${reportUrl}${a.username!}">${a.username!}</a></td>
							<td>${a.framedipaddress!}</td>
							<td>${a.acctstarttime?string('dd-MM-yyyy')} <strong>${a.acctstarttime?string('HH:mm:ss')}</strong></td>
							<td>${byteString(a.acctinputoctets)}</td>
							<td>${byteString(a.acctoutputoctets)}</td>
							<#assign ip = history.userPackage.internetPackage />
							<td title="${ip.name!}"><a href="<@s.url value="/master/packages?q=${ip.code!}" />">${ip.code!}</a></td>
							<td>
								<a class="confirm" data-message="Disconnect ${a.username!}?" href="<@s.url value="/admin/user/disconnect/${a.callingstationid!}" />" title="<@s.text name="tooltip.onlineuser.disconnect"><@s.param>${a.username!}</@s.param></@s.text>">
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
				items: ${listacc.rowCount?string('#')},
				itemsOnPage: ${max?string('#')},
				currentPage: ${(listacc.currentPage + 1)?string('#')},
				hrefTextPrefix: '?q=${q}&page='
			});
		});
		</script>
	</body>
</html>