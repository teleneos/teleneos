<html>
	<head>
		<title><@s.text name="page.group.title" /></tile>
		<meta name="header" content="<@s.text name="page.group.header" />">
	</head>
	<body>
		<div class="row-fluid">
			<#include "/view/decorator/nav/admin-sidenav.ftl" />
			<div class="span10">
				<div class="row-fluid">
				<a class="btn btn-primary span2" href="<@s.url value="/admin/user/group/add" />">
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
							<th><@s.text name="label.admin.group.name" /></th>
							<th><@s.text name="label.admin.group.description" /></th>
						</tr>
					</thead>
					<tbody>
						<#assign no = 1 />
						<@s.url value="/admin/user/group/edit/" var="editUrl" />
						<#list groups.entityList as g>
						<tr>
							<td>${no}</td>
							<td><a href="${editUrl + g.id}">${g.name!}</a></td>
							<td>${g.description!}</td>
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