<html>
	<head>
		<title><@s.text name="page.monitoring.network.title" /></title>
		<meta name="header" content="<@s.text name="page.monitoring.network.header" />">
		<content tag="sidenav">/view/decorator/nav/monitoring-sidenav.ftl</content>
		<!--[if lte IE 8]><script language="javascript" type="text/javascript" src="<@s.url value="/static/explorercanvas/r3/excanvas.min.js" />"></script><![endif]-->
		<script type="text/javascript" src="<@s.url value="/static/flot/0.8.0/jquery.flot.min.js" />"></script>
		<style type="text/css">
			.speed:after, .flot-y-axis > .flot-tick-label:after {
				content: " Kbps";
			}
		</style>
	</head>
	<body>
		<div class="block-content collapse in">
			<#assign currentTele = session.getAttribute('currentTele')!'' />
			<select id="telecentres" class="pull-right">
				<#list telecentres.entityList as t>
				<option value="${t.id}" <#if t.id == currentTele>selected</#if>>${t.name}</option>
				</#list>
			</select>
			<div class="row-fluid">
				<div class="span12" style="height: 300px;" id="chart">
					<img src="<@s.url value="/images/loading.gif" />" />
				</div>
			</div>
			<br>
			<div class="row-fluid">
				<div class="span12">
					<table class="table table-condensed table-striped">
						<thead>
						<tr>
							<th></th>
							<th></th>
							<th>last</th>
							<th>min</th>
							<th>avg</th>
							<th>max</th>
						</tr>
						</thead>
						<tbody>
						<tr>
							<td>Incoming network traffic on eth0</td>
							<td>[avg]</td>
							<td id="td-last-in" class="speed"></td>
							<td id="td-min-in" class="speed"></td>
							<td id="td-avg-in" class="speed"></td>
							<td id="td-max-in" class="speed"></td>
						</tr>
						<tr>
							<td>Outgoing network traffic on eth0</td>
							<td>[avg]</td>
							<td id="td-last-out" class="speed"></td>
							<td id="td-min-out" class="speed"></td>
							<td id="td-avg-out" class="speed"></td>
							<td id="td-max-out" class="speed"></td>
						</tr>
						</tbody>
					</table>
				</div>
			</div>
		</div>
		<script type="text/javascript">
		var data = [], totalPoints = 300;
			
		function updateData() {
			$.getJSON('<@s.url />/data.json', function(data) {
				var inTraffic = data.histories1;
				var outTraffic = data.histories2;
				var i = inTraffic.length;
				
				var inData = [];
				var outData = [];
				
				var max = 0;
				var min = 9999;
				var avg = 0;
				var last = 0;
				
				var option = { 
					series: {
						lines: { show: true, fill: true },
						points: { show: false },
						shadowSize: 0
					},
					xaxis: {
						labelAngle: 45,
						ticks: []
					}
				};
				
				for (r in inTraffic) {
					r = inTraffic[r];
					var val = new Number(r.value) / 1024;
					
					inData.push([i, val]);
					var date = new Date(new Number(r.clock + '000'));
					var hour = date.getHours();
					var min = date.getMinutes();
					option.xaxis.ticks.push([i, (hour <= 9 ? '0' : '') + hour + ':' + (min <= 9 ? '0' : '') + min]);
					
					if (val > max) {
						max = val;
					}
					
					if (val < min) {
						min = val;
					}
					
					avg += val;
					last = val;
					
					i--;
				}
				
				avg = avg / inTraffic.length;
				$('#td-last-in').text(last.toFixed(2));
				$('#td-min-in').text(min.toFixed(2));
				$('#td-avg-in').text(avg.toFixed(2));
				$('#td-max-in').text(max.toFixed(2));
				
				i = outTraffic.length;
				max = 0;
				min = 9999;
				avg = 0;
				last = 0;
				
				for (r in outTraffic) {
					r = outTraffic[r];
					var val = new Number(r.value) / 1024;
					
					outData.push([i, val]);
					
					if (val > max) {
						max = val;
					}
					
					if (val < min) {
						min = val;
					}
					
					avg += val;
					last = val;
					
					i--;
				}
				
				avg = avg / outTraffic.length;
				$('#td-last-out').text(last.toFixed(2));
				$('#td-min-out').text(min.toFixed(2));
				$('#td-avg-out').text(avg.toFixed(2));
				$('#td-max-out').text(max.toFixed(2));
				
				$.plot($("#chart"), [
					{ label: "Incoming Traffic", color: 'blue', data: inData },
					{ label: "Outgoing Traffic", color: 'green', data: outData }
				], option);
			});
			
			setTimeout(updateData, 1000 * 60);
		}
		
		$(function() {
			updateData();
			
			<#if !session.getAttribute('currentTele')??>
			location.href = '<@s.url />?telecentre=' + $('#telecentres').val();
			</#if>
			
			$('#telecentres').change(function() {
				location.href = '<@s.url />?telecentre=' + $(this).val();
			});
		});
		</script>
	</body>
</html>