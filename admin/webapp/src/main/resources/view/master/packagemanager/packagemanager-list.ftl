<html>
	<head>
		<title><@s.text name="page.packagemanager.title" /></title>
		<meta name="header" content="<@s.text name="page.packagemanager.header" />">
	</head>
	<body>
		<div class="row-fluid">
			<#include "/view/decorator/nav/master-sidenav.ftl" />
			<div class="span10">
				<div class="row-fluid">
				<a class="btn btn-primary span2" href="<@s.url value="/master/packages/add" />">
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
							<th>#</th>
							<th><@s.text name="label.master.packagemanager.code" /></th>
							<th><@s.text name="label.master.packagemanager.name" /></th>
							<th>Package Type</th>
							<th><@s.text name="label.master.packagemanager.time" /></th>
							<th><@s.text name="label.master.packagemanager.price" /></th>
							<th>Package Status</th>
						</tr>
					</thead>
					<tbody>
						<#assign no = 1 + ((page - 1) * max) />
						<@s.url value="/master/packages/edit/" var="editUrl" />
						<#list internetPackages.entityList as s>
						<tr <#if s.logInformation.statusFlag.ordinal() == 1>class="muted"</#if>>
							<td>${no}</td>
							<td><a href="${editUrl + s.id!}">${s.code!}</a></td>
							<td>${s.name!}</td>
							<td>${s.type!}</td>
							<td>
							<#if s.type="NON_COUNTDOWN">
								@ ${s.time?string('#')!} minute
							<#else>
								${s.time?string('#')!} minute
							</#if>
							</td>
							<td>${s.price!}</td>
							<td>${s.logInformation.statusFlag!}</td>
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
				items: ${internetPackages.rowCount?string('#')},
				itemsOnPage: ${max?string('#')},
				currentPage: ${(internetPackages.currentPage + 1)?string('#')},
				hrefTextPrefix: '?q=${q}&page='
			});
		});
		</script>
	</body>
</html>