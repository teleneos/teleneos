<html>
	<head>
		<title><@s.text name="page.service.title" /></title>
		<meta name="header" content="<@s.text name="page.service.header" />">
	</head>
	<body>
		<div class="row-fluid">
			<#include "/view/decorator/nav/admin-sidenav.ftl" />
			<div class="span10">
				<div class="row-fluid">
				<a class="btn btn-primary span2" href="<@s.url value="/admin/service/add" />">
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
							<th><@s.text name="label.admin.service.name" /></th>
							<th><@s.text name="label.admin.service.servicetype" /></th>
							<th><@s.text name="label.admin.service.limitquota" /></th>
							<th><@s.text name="label.admin.service.limittime" /></th>
							<th><@s.text name="label.admin.service.trafficquota" /></th>
							<th><@s.text name="label.admin.service.timequota" /></th>
							<th><@s.text name="label.admin.service.pricecalc" /></th>
							<th><@s.text name="label.admin.service.unitprice" /></th>
						</tr>
					</thead>
					<tbody>
						<#assign serviceTypes = [action.getText('label.admin.service.servicetype.prepaid'), action.getText('label.admin.service.servicetype.postpaid')] />
						<#assign priceCalcs = [action.getText('label.admin.service.pricecalc.download'), action.getText('label.admin.service.pricecalc.upload'), action.getText('label.admin.service.pricecalc.time')]>
						<#assign no = 1 + ((page - 1) * max) />
						<@s.url value="/admin/service/edit/" var="editUrl" />
						<#list services.entityList as s>
						<tr>
							<td>${no}</td>
							<td><a href="${editUrl + s.id}">${s.name!}</a></td>
							<td>${serviceTypes[s.type.ordinal()]}</td>
							<td>${s.limitQuota?string('<i class="icon-ok"></i>', '<i class="icon-remove"></i>')}</td>
							<td>${s.limitTime?string('<i class="icon-ok"></i>', '<i class="icon-remove"></i>')}</td>
							<td>${s.quotaLimit?string('#')}</td>
							<td>${s.timeLimit?string('#')}</td>
							<td>${priceCalcs[s.calculation.ordinal()]}</td>
							<td>${s.unitPrice?string('#,00')}</td>
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
				items: ${services.rowCount?string('#')},
				itemsOnPage: ${max?string('#')},
				currentPage: ${(services.currentPage + 1)?string('#')},
				hrefTextPrefix: '?q=${q}&page='
			});
		});
		</script>
	</body>
</html>