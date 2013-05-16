<html>
	<head>
		<title><@s.text name="page.ticket.category.title.page" /></title>
		<meta name="header" content="<@s.text name="page.ticket.category.header" />">
	</head>
	<body>
		<div class="row-fluid">
			<#include "/view/decorator/nav/ticket-sidenav.ftl" />
			<div class="span10">
				<div class="row-fluid">
				<a class="btn btn-primary span2" href="<@s.url value="/ticket/category/add" />">
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
							<th><@s.text name="label.ticket.category.name" /></th>
							<th><@s.text name="label.ticket.category.description" /></th>
							<th>Action</th>
						</tr>
					</thead>
					<tbody>
						<#assign no = 1 + ((page - 1) * max) />
						<@s.url value="/ticket/category/edit/" var="editUrl" />
						<@s.url value="/ticket/category/remove/" var="deleteUrl" />
						<#list categories.entityList as s>
						<tr>
							<td>${no}</td>
							<td><a href="${editUrl}${s.id}">${s.name!}</a></td>
							<td>${s.description!}</td>
							<td><a class="btn confirm" title="Confirmation" href="${deleteUrl}${s.id!}" data-message="Are you sure to remove ${s.title!}?"><i class="icon-ban-circle"></i></a></td>
						</tr>
						<#assign no = no + 1 />
						</#list>
					</tbody>
				</table>
				<div id="pagination"></div>
			</div>
		</div>		
		<script type="text/javascript" src="<@s.url value="/scripts/jq/pagination.js" />"></script>
		<script type="text/javascript" src="<@s.url value="/scripts/bootbox.min.js" />"></script>
		<script type="text/javascript" src="<@s.url value="/scripts/cimande-popup.js" />"></script>
		<script type="text/javascript">
		$(function() {
			$('#pagination').pagination({
				items: ${categories.rowCount?string('#')},
				itemsOnPage: ${max?string('#')},
				currentPage: ${(categories.currentPage + 1)?string('#')},
				hrefTextPrefix: '?q=${q}&page='
			});
		});
		</script>
	</body>
</html>