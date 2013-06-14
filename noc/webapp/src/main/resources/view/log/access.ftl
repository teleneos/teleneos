<html>
	<head>
		<title><@s.text name="page.log.access.title" /></title>
		<meta name="header" content="<@s.text name="page.log.access.header" />">
	</head>
	<body>
		<div class="row-fluid">
			<#include "/view/decorator/nav/log-sidenav.ftl" />
			<div class="span10">
				<div class="row-fluid">
					<#assign currentTele = session.getAttribute('currentTele')!'' />
					<form class="form span9" method="get">
						<div class="input-append">
							<input type="text" name="q" value="${q}" />
							<button class="btn">
								<i class="icon-search"></i>
								<@s.text name="button.search" />
							</button>
						</div>
					</form>
					<select id="telecentres" class="span3 pull-right">
						<#list telecentres.entityList as t>
						<option value="${t.id}" <#if t.id == currentTele>selected</#if>>${t.name}</option>
						</#list>
					</select>
				</div>
				<div class="row-fluid">
					<table class="table table-striped table-condensed">
					<thead>
						<tr>
							<th class="span1">#</th>
							<th class="span3"><@s.text name="label.log.access.time" /></th>
							<th class="span2"><@s.text name="label.log.access.user" /></th>
							<th><@s.text name="label.log.access.url" /></th>
						</tr>
					</thead>
					<tbody>
						<#assign no = (page + - 1) * max />
						<#list logs.entityList as l>
						<tr>
							<td>${(no+1)?string('#')}</td>
							<#assign logDate = model.toDate(l.time) />
							<td>${logDate?string('dd-MM-yyyy')} <strong>${logDate?string('HH:mm:ss:SSS')}</strong></td>
							<td>${l.user!}</td>
							<td><a href="${l.url!}" target="_blank">${l.url!}</a></td>
							<#assign no = no + 1 />
						</tr>
						</#list>
					</tbody>
				</table>
				<div class="pagination"></div>
				<div class="btn light-theme"><i class="icon-tasks"></i> <strong>${logs.rowCount}</strong></div>
			</div>
		</div>
		<script type="text/javascript">
		$(function() {
			<#if currentTele == ''>
			location.href = '<@s.url />?telecentre=' + $('#telecentres').val();
			</#if>
			
			$('#telecentres').change(function() {
				location.href = '<@s.url />?telecentre=' + $(this).val();
			});
		});
		</script>
		<script type="text/javascript" src="<@s.url value="/scripts/jq/pagination.js" />"></script>
		<script type="text/javascript">
		$(function() {
			$('.pagination').pagination({
				items: ${logs.rowCount?string('#')},
				itemsOnPage: ${max?string('#')},
				currentPage: ${(logs.currentPage + 1)?string('#')},
				hrefTextPrefix: '?q=${q!}&user=${user!}&telecentre=${currentTele!}&from=${from!}&to=${to!}&page='
			});
		});
		</script>
	</body>
</html>