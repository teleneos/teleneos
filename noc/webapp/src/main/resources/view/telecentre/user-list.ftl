<html>
	<head>
		<title><@s.text name="page.telecentre.user.title" /></title>
		<meta name="header" content="<@s.text name="page.telecentre.user.header" />">
		<content tag="sidenav">/view/decorator/nav/telecentre-sidenav.ftl</content>
	</head>
	<body>
		<div class="block-content collapse in">
			<#assign currentTele = session.getAttribute('currentTele')!'' />
			<select id="telecentres" class="pull-right">
				<#list telecentres.entityList as t>
				<option value="${t.id}" <#if t.id == currentTele>selected</#if>>${t.name}</option>
				</#list>
			</select>
			<table class="table table-striped table-condensed">
				<thead>
					<tr>
						<th>#</th>
						<th><@s.text name="label.telecentre.user.username" /></th>
						<th><@s.text name="label.telecentre.user.name" /></th>
						<th><@s.text name="label.telecentre.user.email" /></th>
						<#--th><@s.text name="label.telecentre.user.phone" /></th-->
						<th><@s.text name="label.telecentre.user.address" /></th>
						<th><@s.text name="label.telecentre.user.age" /></th>
					</tr>
				</thead>
				<tbody>
					<#assign no = 1 />
					<#list users.entityList as u>
					<tr>
						<td>${no}</td>
						<td>${u.user.username!}</td>
						<td>${u.name.first!} ${(u.name.last!'').replace('-', '')}</td>
						<td><a href="mailto:${u.user.email!}">${u.user.email!}</a></td>
						<#--td>${u.phone!}</td-->
						<td>${u.address.street1!}</td>
						<td>${model.getDate(0)?string('yyyy')?number - u.birthDate?string('yyyy')?number}</td>
					</tr>
					<#assign no = no + 1 />
					</#list>
				</tbody>
			</table>
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
	</body>
</html>