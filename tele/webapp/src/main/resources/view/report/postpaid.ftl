<html>
	<head>
		<title><@s.text name="page.statistic.title" /></title>
		<meta name="header" content="<@s.text name="page.statistic.title" />">
		<content tag="sidenav">/view/decorator/nav/report-statistic-sidenav.ftl</content>
	</head>
	<body>
		<#function noInvoice counter>
			<#if counter &gt;= 100000>
				<#return counter?string('#')/>
			<#elseif counter &gt;= 10000>
				<#return '0' + counter?string('#')/>
			<#elseif counter &gt;= 1000>
				<#return '00' + counter?string('#')/>
			<#elseif counter &gt;= 100>
				<#return '000' + counter?string('#') />
			<#elseif counter &gt;= 10>
				<#return '0000' + counter?string('#') />
			<#else>
				<#return '00000' + counter?string('#') />
			</#if>
		</#function>
		<#function timeFormat time>
			<#if time &gt; (60 * 60 * 24 * 7)>
				<#return (time / (60 * 60 * 24 * 7))?string('#') + ' Weeks' />
			<#elseif time &gt; (60 * 60 * 24)>
				<#return (time / (60 * 60 * 24))?string('#') + ' Days' />
			<#elseif time &gt; (60 * 60)>
				<#return (time / (60 * 60))?string('#') + ' Hours' />
			<#elseif time &gt; 60>
				<#return (time / 60)?string('#') + ' Min' />
			<#else>
				<#return time?string('#') + ' Sec' />
			</#if>
		</#function>
		<div class="block-content collapse in">
			<div class="row-fluid">
			<form class="form-inline span12" method="get">
				<div class=" pull-right">
					Search : <input type="text" name="q" value="${q!}" />
					<button class="btn" type="submit">
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
						<th><@s.text name="label.report.postpaid.user" /></th>
						<th><@s.text name="label.report.postpaid.inv" /></th>
						<th><@s.text name="label.report.postpaid.package" /></th>
						<th><@s.text name="label.report.postpaid.start" /></th>
						<th><@s.text name="label.report.postpaid.price" /></th>
						<th><@s.text name="label.report.postpaid.status" /></th>
						<th><@s.text name="label.report.postpaid.total" /></th>
					</tr>
				</thead>
				<tbody>
					<#assign no = 1 + ((page - 1) * max) />
					<#list transactionDetails.entityList as d>
					<tr>
						<td>${no}</td>
						<td>${d.transactionHeader.username}</td>
						<td>${noInvoice(d.transactionHeader.counter)}</td>
						<td>${d.internetPackage.name}</td>
						<td><#if d.postpaidStart??>${d.postpaidStart} - ${d.postpaidEnd}</#if></td>
						<td>${d.internetPackage.price!} @ ${timeFormat(d.internetPackage.time!0)}</td>
						<td><#if d.reportPaidStatus> PAID <#else> UNPAID </#if></td>
						<td style="text-align: right;">${d.internetPackage.price!}</td>
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
				items: ${transactionDetails.rowCount?string('#')},
				itemsOnPage: ${max?string('#')},
				currentPage: ${(transactionDetails.currentPage + 1)?string('#')},
				hrefTextPrefix: '?q=${q}&page='
			});
		});
		</script>
	</body>
</html>