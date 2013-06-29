<html>
	<head>
		<title><@s.text name="page.packagemanager.title" /></title>
		<meta name="header" content="<@s.text name="page.packagemanager.header" />">
		<content tag="sidenav">/view/decorator/nav/master-sidenav.ftl</content>
	</head>
	<body>
		<#function timeFormat time>
			<#if (time = (60 * 24 * 7))>
				<#return (time / (60 * 24 * 7))?string('#') + ' Weeks' />
			<#elseif (time >= (60 * 24))>
				<#return (time / (60 * 24))?string('#') + ' Days' />
			<#elseif (time >= 60)>
				<#return (time / (60))?string('#') + ' Hours' />
			<#elseif (time >= 1)>
				<#return (time)?string('#') + ' Min' />
			<#elseif (time == 0)>
				<#return '-' />
			</#if>
		</#function>
		<div class="block-content collapse in">
			<div class="row-fluid">
			<a class="btn btn-primary span2" href="<@s.url value="/master/packages/add" />">
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
						<th><@s.text name="label.master.packagemanager.code" /></th>
						<th><@s.text name="label.master.packagemanager.name" /></th>
						<th><@s.text name="label.master.packagemanager.subscriptiontype" /></th>
						<th><@s.text name="label.master.packagemanager.time" /></th>
						<th><@s.text name="label.master.packagemanager.price" /></th>
						<th><@s.text name="label.master.packagemanager.active" /></th>
					</tr>
				</thead>
				<tbody>
					<#assign no = 1 + ((page - 1) * max) />
					<@s.url value="/master/packages/edit/" var="editUrl" />
					<#list internetPackages.entityList as s>
					<tr <#if s.logInformation.statusFlag.ordinal() == 1>class="muted"</#if>>
						<td>${no}</td>
						<td><a href="${editUrl + s.id!}">${s.code!}</a></td>
						<td>${s.name!}</td>
						<td>${s.paymentMethod!}</td>
						<td>
							${timeFormat(s.time!0)}
						</td>
						<td>${s.price!0?string}</td>
						<td>
							<#if s.logInformation.statusFlag == 'ACTIVE'>
							<i class="icon-ok"></i>
							<#else>
							<i class="icon-remove"></i>
							</#if>
						</td>
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
				items: ${internetPackages.rowCount?string('#')},
				itemsOnPage: ${max?string('#')},
				currentPage: ${(internetPackages.currentPage + 1)?string('#')},
				hrefTextPrefix: '?q=${q}&page='
			});
		});
		</script>
	</body>
</html>