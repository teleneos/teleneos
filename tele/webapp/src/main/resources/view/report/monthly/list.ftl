<html>
	<head>
		<title><@s.text name="page.report.title" /></title>
		<meta name="header" content="<@s.text name="page.report.header" />">
		<content tag="sidenav">/view/decorator/nav/report-statistic-sidenav.ftl</content>
		<link rel="stylesheet" type="text/css" href="<@s.url value="/styles/datepicker.css" />" />
		<script type="text/javascript" src="<@s.url value="/scripts/bootstrap-datepicker.js" />"></script>
		<script type="text/javascript">
		$(function(){
			$('#date').datepicker({
				minViewMode:'months',
				format:'mm/yyyy'
			});
		});
		</script>
	</head>
	<body>
		<div class="block-content collapse in">
			<div class="row-fluid">
				<form class="form-inline span12" method="get">
					<div class=" pull-right">
						Month : <input id="date" type="text" name="date" value="${date!}" readonly /> -
						<button class="btn" type="submit">
							<i class="icon-search"></i>
							<@s.text name="button.generate" />
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
						<th><@s.text name="label.admin.item.price" /></th>
						<th><@s.text name="label.admin.tdetail.quantity" /></th>
						<th><@s.text name="label.admin.tdetail.subtotal" /></th>
					</tr>
				</thead>
				<tbody>
					<#assign no = 1 + ((page - 1) * max) />
					<#assign qty = 0 />
					<#assign total = 0 />
					<#list itemReports.entityList as x>
					<#assign a = x[0] />
					<#assign b = x[1] />
					<#if a.item??>
					<tr>
						<td>${no}</td>
						<td>${a.item.code!}</td>
						<td>${a.item.name!}</td>
						<td style="text-align:right;">${a.price!}</td>
						<td style="text-align:right;">${b?string('#')!}</td>
						<td style="text-align:right;">${b*a.price}</td>
					</tr>
					<#assign no = no + 1 />
					<#assign qty = qty + b />
					<#assign total = total + (b*a.price) />
					</#if>
					</#list>
					<tr>
						<th colspan="4">Total</th>
						<td style="text-align:right;">${qty}</td>
						<td style="text-align:right;">${total}</td>
					</tr>
				</tbody>
			</table>
		</div>		
	</body>
</html>