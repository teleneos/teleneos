<html>
	<head>
		<title><@s.text name="page.telecentre.title" /></title>
		<meta name="header" content="<@s.text name="page.telecentre.title" />">
	</head>
	<body>
		<div class="row-fluid">
			<div class="span10">
				<table class="table table-striped table-condensed">
					<thead>
						<tr>
							<th>#</th>
							<th><@s.text name="label.master.telecentre.id" /></th>
							<th><@s.text name="label.master.telecentre.name" /></th>
							<th><@s.text name="label.master.telecentre.latitude" /></th>
							<th><@s.text name="label.master.telecentre.longitude" /></th>
						</tr>
					</thead>
					<tbody>
						<#assign no = 1 + ((page - 1) * max) />
						<@s.url value="/telecentre/edit/" var="editUrl" />
						<#list telecentres.entityList as s>
						<tr>
							<td>${no}</td>
							<td><a href="${editUrl}${s.id}">${s.telecentre_id!}</a></td>
							<td>${s.name!}</td>
							<td><#if s.latitude??>${s.latitude?string("0.0000000000")!}</#if></td>
							<td><#if s.longitude??>${s.longitude?string("0.0000000000")!}</#if></td>
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
				items: ${telecentres.rowCount?string('#')},
				itemsOnPage: ${max?string('#')},
				currentPage: ${(telecentres.currentPage + 1)?string('#')},
				hrefTextPrefix: '?q=${q}&page='
			});
		});
		</script>
	</body>
</html>