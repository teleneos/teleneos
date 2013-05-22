<html>
	<head>
		<title><@s.text name="page.theader.title" /></title>
		<meta name="header" content="<@s.text name="page.theader.header" />">
	</head>
	<body>
		<div class="row-fluid">
			<#include "/view/decorator/nav/pos-sidenav.ftl" />
			<div class="span10">
				<div class="row-fluid">
				<a class="btn btn-primary span2" href="<@s.url value="/pos/transaction/add" />">
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
							<th><@s.text name="label.admin.theader.user" /></th>
							<th><@s.text name="label.admin.theader.date" /></th>
						</tr>
					</thead>
					<tbody>
						<#assign no = 1 + ((page - 1) * max) />
						<@s.url value="/pos/transaction/detail/" var="detailUrl" />
						<#list transactionHeaders.entityList as s>
						<tr>
							<td>${no}</td>
							<td><a href="${detailUrl + s.id}">${s.username!}</a></td>
							<td><strong>${s.logInformation.createDate?string('dd-MM-yyyy')!}</strong> ${s.logInformation.createDate?string('HH:mm')!}</td>
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
				items: ${transactionHeaders.rowCount?string('#')},
				itemsOnPage: ${max?string('#')},
				currentPage: ${(transactionHeaders.currentPage + 1)?string('#')},
				hrefTextPrefix: '?q=${q}&page='
			});
		});
		</script>
	</body>
</html>