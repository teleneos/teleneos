<html>
	<head>
		<title><@s.text name="page.editprofile.title" /></tile>
		<meta name="header" content="<@s.text name="page.editprofile.header" />">
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
							<th class="span3"><@s.text name="label.editprofile.username" /></th>
							<th class="span4"><@s.text name="label.editprofile.email" /></th>
							<th class="span4"><@s.text name="label.editprofile.website" /></th>
						</tr>
					</thead>
					<tbody>
						<#assign no = 1 />
						<#list users.entityList as u>
						<tr>
							<td>${no}</td>
							<td>
								<a href="<@s.url value="/admin/user/edit/${u.id!}" />">
								${u.username!}
								</a>
							</td>
							<td>${u.email!}</td>
							<td>${u.website!}</td>
						</tr>
						<#assign no = no + 1 />
						</#list>
					</tbody>
				</table>
			</div>
		</div>
		<script type="text/javascript">
		$(function() {
			$('input[name="q"]').focus();
		});
		</script>
	</body>
</html>