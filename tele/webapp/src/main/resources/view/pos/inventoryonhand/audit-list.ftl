<html>
	<head>
		<title><@s.text name="page.inventoryonhand.title" /></title>
		<meta name="header" content="<@s.text name="page.inventoryonhand.header" />">
		<content tag="sidenav">/view/decorator/nav/pos-sidenav.ftl</content>
	</head>
	<body>
		<div class="block-content collapse in">
			<table class="table table-striped table-condensed">
				<thead>
					<tr>
						<th>#</th>
						<th><@s.text name="label.admin.inventoryonhand.date" /></th>
						<th><@s.text name="label.admin.group.description" /></th>
						<th><@s.text name="label.admin.tdetail.quantity" /></th>
						<th><@s.text name="label.admin.inventoryonhand.stock" /></th>
					</tr>
				</thead>
				<tbody>
					<#assign no = 1 + ((page - 1) * max) />
					<#list auditLogs.entityList as s>
					<tr>
						<td>${no}</td>
						<td>${s.logInformation.createDate!}</td>
						<td>${s.description!}</td>
						<td style="text-align: right;"><#if s.type.ordinal() == 1 > (-</#if>${s.quantity?string('#')!}<#if s.type.ordinal() == 1 >)</#if></td>
						<td>${s.stock?string('#')!}</td>
					</tr>
					<#assign no = no + 1 />
					</#list>
				</tbody>
			</table>
			<div id="pagination"></div>
		</div>		
		<script type="text/javascript" src="<@s.url value="/scripts/jq/pagination.js" />"></script>
		<script type="text/javascript">
		$(function() {
			$('#pagination').pagination({
				items: ${auditLogs.rowCount?string('#')},
				itemsOnPage: ${max?string('#')},
				currentPage: ${(auditLogs.currentPage + 1)?string('#')},
				hrefTextPrefix: '?q=${q}&page='
			});
		});
		</script>
	</body>
</html>