<html>
	<head>
		<title><@s.text name="page.item.title" /></title>
		<meta name="header" content="<@s.text name="page.item.header" />">
	</head>
	<body>
		<div class="row-fluid">
			<#include "/view/decorator/nav/pos-sidenav.ftl" />
			<div class="span10">
				<div class="row-fluid">
				<a class="btn btn-primary span2" href="<@s.url value="/pos/item/add" />">
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
							<th><@s.text name="label.admin.item.code" /></th>
							<th><@s.text name="label.admin.item.name" /></th>
							<th><@s.text name="label.admin.item.description" /></th>
							<th><@s.text name="label.admin.item.uom" /></th>
							<th><@s.text name="label.admin.item.category" /></th>
							<th><@s.text name="label.admin.item.price" /></th>
							
						</tr>
					</thead>
					<tbody>
						<#assign no = 1 + ((page - 1) * max) />
						<@s.url value="/pos/item/edit/" var="editUrl" />
						<#list items.entityList as s>
						<tr>
							<td>${no}</td>
							<td><a href="${editUrl + s.id}">${s.code!}</a></td>
							<td>${s.name!}</td>
							<td>${s.description!}</td>
							<td>${s.uom.name!}</td>
							<td>${s.category.name!}</td>
							<td style="text-align:right;">${s.price!}</td>
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
				items: ${items.rowCount?string('#')},
				itemsOnPage: ${max?string('#')},
				currentPage: ${(items.currentPage + 1)?string('#')},
				hrefTextPrefix: '?q=${q}&page='
			});
		});
		</script>
	</body>
</html>