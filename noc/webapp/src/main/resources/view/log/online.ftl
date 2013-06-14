<html>
	<head>
		<title><@s.text name="page.log.online.title" /></title>
		<meta name="header" content="<@s.text name="page.log.online.header" />">
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
			<#include "/view/decorator/nav/log-sidenav.ftl" />
			<div class="span10">
				<#assign currentTele = session.getAttribute('currentTele')!'' />
				<div class="row-fluid">
					<select id="telecentres" class="pull-right">
						<#list telecentres.entityList as t>
						<option value="${t.id}" <#if t.id == currentTele>selected</#if>>${t.name}</option>
						</#list>
					</select>
				</div>
				
				<#--
				<div class="row-fluid">
					<table class="table-condensed table-bordered">
						<tr>
							<td class="span2"><strong><@s.text name="label.log.online.username" /></strong></td>
							<td class="span3"></td>
							<td class="span2"><strong><@s.text name="label.log.online.name" /></strong></td>
							<td class="span3"></td>
						</tr>
						<tr>
							<td class="span2"><strong><@s.text name="label.log.online.totaldownload" /></strong></td>
							<td class="span3"></td>
							<td class="span2"><strong><@s.text name="label.log.online.totalupload" /></strong></td>
							<td class="span3"></td>
						</tr>
						<tr>
							<td class="span2"><strong><@s.text name="label.log.onlin.totalonline" /></strong></td>
							<td class="span3"></td>
							<td class="span2"></td>
							<td class="span3"></td>
						</tr>
					</table>
				</div>
				<hr>
				-->
				<table class="table table-striped table-condensed">
					<thead>
						<tr>
							<th class="span1">#</th>
							<th><@s.text name="label.log.online.user" /></th>
							<th><@s.text name="label.log.online.acctid" /></th>
							<th><@s.text name="label.log.online.start" /></th>
							<th><@s.text name="label.log.online.stop" /></th>
							<th><@s.text name="label.log.online.download" /></th>
							<th><@s.text name="label.log.online.upload" /></th>
						</tr>
					</thead>
					<tbody>
						<#assign no = ((page - 1) * max) />
						<@s.url value="/log/access" var="logurl" />
						<#list accts.entityList as a>
						<#assign urlparam = 'telecentre=${currentTele}&user=${a.username!}&from=${a.acctstarttime.time?string("#")}&to=${a.acctstoptime.time?string("#")}' />
						<tr>
							<td>${(no + 1)?string('#')}</td>
							<td title="MAC: ${a.callingstationid!}, IP: ${a.framedipaddress!}">
								${a.username!}
							</td>
							<td>
								${a.radacctid?string('#')!}
							</td>
							<td>
								${a.acctstarttime?string('dd-MM-yyyy')} <strong>${a.acctstarttime?string('HH:mm:ss')}</strong>
							</td>
							<td>
								${a.acctstoptime?string('dd-MM-yyyy')} <strong>${a.acctstoptime?string('HH:mm:ss')}</strong>
								<span class="label">${timeFormat(a.acctsessiontime)}</span>
							</td>
							<td>${byteString(a.acctinputoctets)}</td>
							<td>${byteString(a.acctoutputoctets)}</td>
						</tr>
						<#assign no = no + 1 />
						</#list>
					</tbody>
				</table>
				<div class="pagination"></div>
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
				items: ${accts.rowCount?string('#')},
				itemsOnPage: ${max?string('#')},
				currentPage: ${(accts.currentPage + 1)?string('#')},
				hrefTextPrefix: '?page='
			});
		});
		</script>
	</body>
</html>