<html>
	<head>
		<title><@s.text name="page.group.title" /></title>
		<meta name="header" content="<@s.text name="page.group.header" />">
	</head>
	<body>
		<div class="row-fluid">
			<#include "/view/decorator/nav/master-sidenav.ftl" />
			<div class="span10">
				<div class="row-fluid">
				<a class="btn btn-primary span2" href="<@s.url value="/master/group/add" />">
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
							<th><@s.text name="label.master.group.code" /></th>
							<th><@s.text name="label.master.group.name" /></th>
							<th><@s.text name="label.master.group.foc" /></th>
							<th><@s.text name="label.master.group.package" /></th>
						</tr>
					</thead>
					<tbody>
						<#assign no = 1 />
						<@s.url value="/master/group/edit/" var="editUrl" />
						<#list groups.entityList as s>
						<tr>
							<td>${no}</td>
							<td><a href="${editUrl}${s.id}">${s.code!}</a></td>
							<td>${s.name!}</td>
							<td>
								<i class="icon-<#if s.freeOfCharge>ok<#else>remove</#if>"></i>
							</td>
							<td>
								<ul>
									<#list s.groupPackages as g>
									<li>${g.internetPackage.name!}</li>
									</#list>
								</ul>
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
				items: ${groups.rowCount?string('#')},
				itemsOnPage: ${max?string('#')},
				currentPage: ${(groups.currentPage + 1)?string('#')},
				hrefTextPrefix: '?q=${q}&page='
			});
		});
		</script>
	</body>
</html>