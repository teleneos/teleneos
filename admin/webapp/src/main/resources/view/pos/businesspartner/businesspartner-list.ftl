<html>
	<head>
		<title><@s.text name="page.businesspartner.title" /></title>
		<meta name="header" content="<@s.text name="page.businesspartner.header" />">
	</head>
	<body>
		<div class="row-fluid">
			<#include "/view/decorator/nav/pos-sidenav.ftl" />
			<div class="span10">
				<div class="row-fluid">
				<a class="btn btn-primary span2" href="<@s.url value="/pos/businesspartner/add" />">
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
							<th><@s.text name="label.admin.businesspartner.category" /></th>
							<th><@s.text name="label.admin.businesspartner.name" /></th>
							<th><@s.text name="label.admin.businesspartner.officePhone" /></th>
							<th><@s.text name="label.admin.businesspartner.fax" /></th>
							<th><@s.text name="label.admin.businesspartner.email" /></th>
							<th><@s.text name="label.admin.businesspartner.address" /></th>
							<th><@s.text name="label.admin.businesspartner.city" /></th>
							<th><@s.text name="label.admin.businesspartner.zipcode" /></th>
							<th><@s.text name="label.admin.businesspartner.country" /></th>
							<th><@s.text name="label.admin.businesspartner.description" /></th>
						</tr>
					</thead>
					<tbody>
						<#assign no = 1 />
						<@s.url value="/pos/businesspartner/edit/" var="editUrl" />
						<#list businessPartners.entityList as s>
						<tr>
							<td>${no}</td>
							<td>${s.category!}</td>
							<td><a href="${editUrl + s.id}">${s.name!}</a></td>
							<td>${s.officePhone!}</td>
							<td>${s.fax!}</td>
							<td>${s.email!}</td>
							<td>${s.address!}</td>
							<td>${s.city!}</td>
							<td>${s.zipCode!}</td>
							<td>${s.country!}</td>
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
				items: ${businessPartners.rowCount?string('#')},
				itemsOnPage: ${max?string('#')},
				currentPage: ${(businessPartners.currentPage + 1)?string('#')},
				hrefTextPrefix: '?q=${q}&page='
			});
		});
		</script>
	</body>
</html>