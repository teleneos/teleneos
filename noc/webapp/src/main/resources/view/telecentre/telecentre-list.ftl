<html>
	<head>
		<title><@s.text name="page.telecentre.title" /></title>
		<meta name="header" content="<@s.text name="page.telecentre.title" />">
		<content tag="sidenav">/view/decorator/nav/telecentre-sidenav.ftl</content>
	</head>
	<body>
		<div class="block-content collapse in">
			<div class="row-fluid">
			<a class="btn btn-primary span3" href="<@s.url value="/tele/add" />">
				<i class="icon-plus icon-white"></i>
				<@s.text name="button.tececentre.manage.add" />
			</a>
			<form class="form-inline span9" method="get">
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
						<th><@s.text name="label.telecentre.id" /></th>
						<th><@s.text name="label.telecentre.name" /></th>
						<th><@s.text name="label.telecentre.address" /></th>
						<th><@s.text name="label.telecentre.phone" /></th>
						<th><@s.text name="label.telecentre.email" /></th>
					</tr>
				</thead>
				<tbody>
					<#assign no = 1 />
					<@s.url value="/tele/edit/" var="editUrl" />
					<#list telecentres.entityList as s>
					<tr>
						<td>${no}</td>
						<td><a href="${editUrl}${s.id}">${s.id!}</a></td>
						<td>${s.name!}</td>
						<td>${s.address.street1!}</td>
						<td>${s.phone!}</td>
						<td><a href="mailto:${s.email!}">${s.email!}</a></td>
					</tr>
					<#assign no = no + 1 />
					</#list>
				</tbody>
			</table>
			<#assign ses = request.session />
			<#--
			<#assign next = ses.getAttribute('NEXT_COOKIE')![] />
			<#assign prev = ses.getAttribute('PREV_COOKIE')![] />
			-->
			<#assign next = [] />
			<#assign prev = [] />
			
			<div id="pagination" class="btn-group">
				<#if (prev?size > 0)>
				<@s.url var = "prvUrl">
					<@s.param name="max">${max}</@s.param>
					<@s.param name="page">-1</@s.param>
				</@s.url>
				<a class="btn prev">
					<i class="icon-backward"></i>&nbsp;
				</a>
				</#if>
				<#if (next?size > 0)>
				<@s.url var = "nxtUrl">
					<@s.param name="max">${max}</@s.param>
					<@s.param name="page">0</@s.param>
				</@s.url>
				<a class="btn next" href="${nxtUrl}">
					&nbsp;<i class="icon-forward"></i>
				</a>
				</#if>
			</div>
		</div>
	</body>
</html>