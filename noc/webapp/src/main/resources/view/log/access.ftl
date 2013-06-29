<html>
	<head>
		<title><@s.text name="page.log.access.title" /></title>
		<meta name="header" content="<@s.text name="page.log.access.header" />">
		<content tag="sidenav">/view/decorator/nav/log-sidenav.ftl</content>
		
		<script type="text/javascript" src="<@s.url value="/scripts/bootstrap-datetimepicker.min.js" />"></script>
		<link rel="stylesheet" type="text/css" href="<@s.url value="/styles/bootstrap-datetimepicker.min.css" />" />
	</head>
	<body>
		<div class="block-content collapse in">
			<div class="row-fluid">
				<#assign currentTele = session.getAttribute('currentTele')!'' />
				<form class="span9 well" method="get" id="search-form">
					<fieldset>
						<legend>Search</legend>
						<@s.hidden name="telecentre" value="${currentTele}" />
						
						<input type="text" name="user" value="${user!}" placeholder="username..." />
						<input type="text" name="q" value="${q!}" placeholder="keyword..." />
						<br>
						<div id="dfrom" class="input-append date">
							<input data-format="dd-MM-yyyy hh:mm:ss" type="text" class="span10" readonly="readonly"></input>
							<span class="add-on">
								<i data-time-icon="icon-time" data-date-icon="icon-calendar"></i>
							</span>
						</div>
						<span>&nbsp;-&nbsp;</span>
						<div id="dto" class="input-append date">
							<input data-format="dd-MM-yyyy hh:mm:ss" type="text" class="span10" readonly="readonly"></input>
							<span class="add-on">
								<i data-time-icon="icon-time" data-date-icon="icon-calendar"></i>
							</span>
						</div>
						<br>
						<#--
						<label class="checkbox">
							<input type="checkbox" name="webpage" checked="checked" /> Web page only
						</label>
						-->
						
						<input type="hidden" name="from" value="0" />
						<input type="hidden" name="to" />
						<button type="submit" class="btn">
							<i class="icon-search"></i>
							<@s.text name="button.search" />
						</button>
					</fieldset>
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
				<tbody id="search-result">
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
			var search = function() {
				$.get('<@s.url />/search.json', $('#search-form').serialize(), function(data) {
					
				}, 'html');
			};
		
			$('.pagination').pagination({
				items: ${logs.rowCount?string('#')},
				itemsOnPage: ${max?string('#')},
				currentPage: ${(logs.currentPage + 1)?string('#')},
				hrefTextPrefix: '?q=${q!}&user=${user!}&telecentre=${currentTele!}&from=${from?string('#')!}&to=${to?string('#')!}&page='
			});
			
			var from = ${from?string('#')};
			var to = ${to?string('#')};
			var date = new Date();
			
			var dto = $('#dto').datetimepicker({
				language: 'en'
			}).data('datetimepicker');
			
			if (from > 0) {
				dto.setLocalDate(new Date(to));
			} else {
				dto.setLocalDate(date);
			}
			
			var dfrom = $('#dfrom').datetimepicker({
				language: 'en'
			}).data('datetimepicker');
			
			if (to > 0) {
				dfrom.setLocalDate(new Date(from));
			} else {
				date.setDate(date.getDate() - 90);
				dfrom.setLocalDate(date);
			}
			
			$('#dfrom').on('changeDate', function(e) {
				$('input[name="from"]').val(dfrom.getLocalDate().getTime());
			}).trigger('changeDate');
			
			$('#dto').on('changeDate', function(e) {
				$('input[name="to"]').val(dto.getLocalDate().getTime());
			}).trigger('changeDate');
		});
		</script>
	</body>
</html>