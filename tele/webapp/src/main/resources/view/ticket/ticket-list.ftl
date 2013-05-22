<html>
	<head>
		<title><@s.text name="page.ticket.title" /></title>
		<meta name="header" content="<@s.text name="page.ticket.header" />">
	</head>
	<body>
		<div class="row-fluid">
			<#include "/view/decorator/nav/ticket-sidenav.ftl" />
			<div class="span10">
				<div class="row-fluid">
				<form class="form-inline span12" method="get">
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
							<th><@s.text name="label.ticket.code" /></th>
							<th><@s.text name="label.ticket.date" /></th>
							<th><@s.text name="label.ticket.subject" /></th>
							<th><@s.text name="label.ticket.priority" /></th>
							<th><@s.text name="label.ticket.from" /></th>
							<th><@s.text name="label.ticket.status" /></th>
							<th><@s.text name="label.ticket.category" /></th>
						</tr>
					</thead>
					<tbody>
						<#assign no = 1 + ((page - 1) * max) />
						<#list tickets.entityList as s>
						
						<tr>
							<td>${no}</td>
							<td><a href=/ticket/detail/${s.id!}>${s.code!}</a></td>
							<td>${s.logInformation.createDate!}</td>
							<td>${s.subject!}</td>
							<td>${s.priority!}</td>
							<td>${s.logInformation.createBy!}</td>
							<td><#if s.logInformation.statusFlag == 'ACTIVE' >OPEN<#else>CLOSE</#if></td>
							<td>${s.category.name!}</td>
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
				items: ${tickets.rowCount?string('#')},
				itemsOnPage: ${max?string('#')},
				currentPage: ${(tickets.currentPage + 1)?string('#')},
				hrefTextPrefix: '?q=${q}&page='
			});
		});
		</script>
	</body>
</html>