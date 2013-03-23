<html>
	<head>
		<title><@s.text name="page.group.title" /></tile>
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
							<th><@s.text name="label.master.group.status" /></th>
							<th><@s.text name="label.master.group.pms" /></th>
							<th><@s.text name="label.master.group.club" /></th>
						</tr>
					</thead>
					<tbody>
						<#assign no = 1 />
						<@s.url value="master/group/edit/" var="editUrl" />
						<#list groups.entityList as s>
						<tr>
							<td>${no}</td>
							<td>${s.code!}</td>
							<td>${s.name!}</td>
							<td>${s.status!}</td>
							<td>${s.pms?string}</td>
							<td>${s.club?string}</td>
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