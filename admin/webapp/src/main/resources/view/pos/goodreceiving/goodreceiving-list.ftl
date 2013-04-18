<html>
	<head>
		<title><@s.text name="page.goodreceiving.title" /></title>
		<meta name="header" content="<@s.text name="page.goodreceiving.header" />">
	</head>
	<body>
		<div class="row-fluid">
			<#include "/view/decorator/nav/pos-sidenav.ftl" />
			<div class="span10">
				<div class="row-fluid">
				<a class="btn btn-primary span2" href="<@s.url value="/pos/goodreceiving/add" />">
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
							<th><@s.text name="label.admin.goodreceiving.invoice" /></th>
							<th><@s.text name="label.admin.goodreceiving.date" /></th>
							<th><@s.text name="label.admin.goodreceiving.businesspartner" /></th>
							<th><@s.text name="label.admin.goodreceiving.item" /></th>
							<th><@s.text name="label.admin.goodreceiving.quantity" /></th>
							<th><@s.text name="label.admin.goodreceiving.description" /></th>
						</tr>
					</thead>
					<tbody>
						<#assign no = 1 + ((page - 1) * max) />
						<@s.url value="/pos/goodreceiving/detail/" var="editUrl" />
						<#list goodReceivings.entityList as s>
						<tr>
							<td>${no}</td>
							<td><a href="${editUrl + s.id}">${s.invoiceNo!}</a></td>
							<td>${s.date!}</td>
							<td>${s.businessPartner.name!}</td>
							<td>${s.item.code!}</td>
							<td>${s.quantity!}</td>
							<td>${s.description!}</td>
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
				items: ${goodReceivings.rowCount?string('#')},
				itemsOnPage: ${max?string('#')},
				currentPage: ${(goodReceivings.currentPage + 1)?string('#')},
				hrefTextPrefix: '?q=${q}&page='
			});
		});
		</script>
	</body>
</html>