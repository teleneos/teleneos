<html>
	<head>
		<title><@s.text name="page.itemtype.title" /></title>
		<meta name="header" content="<@s.text name="page.itemtype.header" />">
		<content tag="sidenav">/view/decorator/nav/pos-sidenav.ftl</content>
	</head>
	<body>
		<div class="block-content collapse in">
			<div class="row-fluid">
			<a class="btn btn-primary span2" href="<@s.url value="/pos/conversion/add" />">
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
						<th><@s.text name="label.pos.conversion" /></th>
					</tr>
				</thead>
				<tbody>
					<#assign no = 1 + ((page - 1) * max) />
					<@s.url value="/pos/conversion/edit/" var="editUrl" />
					<#list conversions.entityList as s>
					<tr>
						<td>${no}</td>
						<td><a href="${editUrl + s.id}">1 ${s.uomFrom.name!} = ${s.multiplyRate?string('#')!} ${s.uomTo.name!}</a></td>
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
				items: ${conversions.rowCount?string('#')},
				itemsOnPage: ${max?string('#')},
				currentPage: ${(conversions.currentPage + 1)?string('#')},
				hrefTextPrefix: '?q=${q}&page='
			});
		});
		</script>
	</body>
</html>