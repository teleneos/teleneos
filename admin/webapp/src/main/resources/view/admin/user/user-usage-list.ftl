<html>
	<head>
		<title><@s.text name="page.onlineuser.title" /></title>
		<meta name="header" content="<@s.text name="page.userstat.header" />">
	</head>
	<body>
		<#function byteString byte>
			<#if byte &gt; (1024 * 1024 * 1024)>
				<#return (byte / (1024 * 1024 * 1024))?string('0.#') + ' GB' />
			<#elseif byte &gt; (1024 * 1024)>
				<#return (byte / (1024 * 1024))?string('0.#') + ' MB' />
			<#elseif byte &gt; 1024>
				<#return (byte / 1024)?string('0.#') + ' KB' />
			<#else>
				<#return byte?string('0.#') + ' B' />
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
		<div class="row-fluid">
			<div class="span10">
				<div class="row-fluid">
					<table class="table-condensed table-bordered">
						<tr>
							<td class="span2"><strong><@s.text name="label.admin.onlineuser.username" /></strong></td>
							<td class="span3">${user.user.username!}</td>
							<td class="span2"><strong><@s.text name="label.user.name" /></strong></td>
							<td class="span3">${user.name.first!} ${user.name.last!}</td>
						</tr>
						<tr>
							<td class="span2"><strong><@s.text name="label.admin.onlineuser.totaldownload" /></strong></td>
							<td class="span3">${byteString(statistic[0]!0)}</td>
							<td class="span2"><strong><@s.text name="label.admin.onlineuser.totalupload" /></strong></td>
							<td class="span3">${byteString(statistic[1]!0)}</td>
						</tr>
						<tr>
							<td class="span2"><strong><@s.text name="label.admin.onlineuser.totalonline" /></strong></td>
							<td class="span3">${timeFormat(statistic[2]!0)}</td>
							<td class="span2"><strong><@s.text name="label.admin.onlineuser.package" /></strong></td>
							<td class="span3">${user.internetPackage.name!}</td>
						</tr>
					</table>
				</div>
				<hr>
				<h3>Package History</h3>
				<table class="table table-striped table-condensed">
					<thead>
						<tr>
							<th class="span1">#</th>
							<th><@s.text name="label.master.packagemanager.code" /></th>
							<th><@s.text name="label.master.packagemanager.name" /></th>
							<th>Package Type</th>
							<th><@s.text name="label.admin.invoice.date" /></th>
						</tr>
					</thead>
					<tbody>
						<#assign no = 1 + ((page - 1) * max) />
						<#list user.userPackages as x>
						<tr>
							<td>${no}</td>
							<td>${x.internetPackage.code!}</td>
							<td>${x.internetPackage.name!}</td>
							<td>${x.internetPackage.type!}</td>
							<td>${x.logInformation.createDate?string.medium}</td>
						</tr>
						<#assign no = no + 1 />
						</#list>
					</tbody>
				</table>
			</div>
		</div>
	</body>
</html>