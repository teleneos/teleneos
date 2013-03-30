<html>
	<head>
		<title><@s.text name="page.report.title" /></title>
		<meta name="header" content="<@s.text name="page.report.header" />">
		<link rel="stylesheet" type="text/css" href="<@s.url value="/styles/datepicker.css" />" />
		<script type="text/javascript" src="<@s.url value="/scripts/bootstrap-datepicker.js" />"></script>
		<script type="text/javascript">
		$(function(){
			$('#periodfrom').datepicker();
			$('#periodto').datepicker();
			});
		</script>
	</head>
	<body>
		<div class="row-fluid">
				<#include "/view/decorator/nav/report-sidenav.ftl" />
			<div class="span10">
				<div class="row-fluid">
				<form class="form-inline span10" method="get">
					<div class=" pull-right">
						<input id="periodfrom" type="text" name="periodfrom" value="${periodfrom!}" readonly /> -
						<input id="periodto" type="text" name="periodto" value="${periodto!}" readonly />
						<button class="btn" type="submit">
							<i class="icon-search"></i>
							<@s.text name="button.generate" />
						</button>
					</div>
				</form>
				</div>
				<ul class="nav nav-tabs" id="myTab">
					<li><a href="/report/store">Store</a></li>
					<li class="active"><a href="/report/internet">Internet</a></li>
				</ul>
				<div class="tab-content">
					<div class="tab-pane active" id="store">
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
							<tr>
								<td>${no}</td>
								<td>${a.internetPackage.code!}</td>
								<td>${a.internetPackage.name!}</td>
								<td>${a.price!}</td>
								<td>${a.quantity!}</td>
								<td>${a.quantity*a.price}</td>
							</tr>
							<#assign no = no + 1 />
							<#assign qty = qty + a.quantity />
							<#assign total = total + (a.quantity*a.price) />
							</#list>
							<tr>
								<th colspan="4">Total</th>
								<td>${qty}</td>
								<td>${total}</td>
							</tr>
						</tbody>
					</table>
					</div>
					<div class="tab-pane" id="internet">tab 2</div>
				</div>
			</div>
		</div>		
	</body>
</html>