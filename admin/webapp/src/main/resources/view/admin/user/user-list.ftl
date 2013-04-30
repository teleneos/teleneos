<html>
	<head>
		<title><@s.text name="page.onlineuser.title" /></title>
		<meta name="header" content="<@s.text name="page.user.title" />">
	</head>
	<body>
		<div class="row-fluid">
			<#include "/view/decorator/nav/admin-sidenav.ftl" />
			<div class="span10">
				<div class="row-fluid">
				<a class="btn btn-primary span2" href="<@s.url value="/admin/user/add" />">
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
							<th class="span1">#</th>
							<th><@s.text name="label.login.username" /></th>
							<th><@s.text name="label.user.name" /></th>
							<th><@s.text name="label.user.phone" /></th>
							<th><@s.text name="label.user.idcard" /></th>
							<th><@s.text name="label.user.occupation" /></th>
						</tr>
					</thead>
					<tbody>
						<#assign no = 1 + ((page - 1) * max) />
						<#list users.entityList as u>
						<tr <#if u.logInformation.statusFlag == 'INACTIVE'>class="muted"</#if>>
							<td>${no}</td>
							<td><a href="<@s.url value="/admin/user/edit/${u.user.username}" />">${u.user.username!}</a></td>
							<td>${u.name.first!}<#if u.name.last != '-'> ${u.name.last}</#if></td>
							<td>${u.phone!}</td>
							<td>${u.idcard!}</td>
							<td>${u.occupation!}</td>
							<td>
								<#--
								<a href="<@s.url value="/admin/user/report/${u.user.username!}" />" title="<@s.text name="tooltip.user.report"><@s.param>${u.user.username!}</@s.param></@s.text>">
									<i class="icon-list"></i>
								</a>
								-->
							</td>
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
				items: ${details.rowCount?string('#')},
				itemsOnPage: ${max?string('#')},
				currentPage: ${(details.currentPage + 1)?string('#')},
				hrefTextPrefix: '?q=${q}&page='
			});
		});
		</script>
	</body>
</html>