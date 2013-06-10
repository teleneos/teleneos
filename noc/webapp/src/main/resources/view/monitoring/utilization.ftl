<html>
	<head>
		<title><@s.text name="page.monitoring.utilization.title" /></title>
		<meta name="header" content="<@s.text name="page.monitoring.utilization.header" />">
		<!--[if lte IE 8]><script language="javascript" type="text/javascript" src="<@s.url value="/static/explorercanvas/r3/excanvas.min.js" />"></script><![endif]-->
		<script type="text/javascript" src="<@s.url value="/static/flot/0.8.0/jquery.flot.min.js" />"></script>
		<script type="text/javascript" src="<@s.url value="/static/flot/0.8.0/jquery.flot.pie.min.js" />"></script>
		<style type="text/css">
			.flot-y-axis > .flot-tick-label:after, .speed:after {
				content: " %";
			}
		</style>
	</head>
	<body>
		<div class="row-fluid">
			<#include "/view/decorator/nav/monitoring-sidenav.ftl" />
			<div class="span10">
				<#assign currentTele = session.getAttribute('currentTele')!'' />
				<select id="telecentres" class="pull-right">
					<#list telecentres.entityList as t>
					<option value="${t.id}" <#if t.id == currentTele>selected</#if>>${t.name}</option>
					</#list>
				</select>
				<div class="row-fluid">
					<div class="span12" style="height: 250px;" id="networkchart">
						<img src="<@s.url value="/images/loading.gif" />" />
					</div>
				</div>
				<br>
				<div class="row-fluid">
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
								<td>Processor load</td>
								<td>[avg]</td>
								<td id="td-last-proc" class="speed"></td>
								<td id="td-min-proc" class="speed"></td>
								<td id="td-avg-proc" class="speed"></td>
								<td id="td-max-proc" class="speed"></td>
							</tr>
						</tbody>
					</table>
				</div>
				<hr>
				<div class="row-fluid">
					<div class="span6 well">
						<div>
							<p><strong>Swap Usage</strong></p>
							<div style="height: 300px;" id="swapchart">
								<img src="<@s.url value="/images/loading.gif" />" />
							</div>
						</div>
					</div>
					<div class="span6 well">
						<div>
							<p><strong>Disk Usage</strong></p>
							<div style="height: 300px;" id="diskchart">
								<img src="<@s.url value="/images/loading.gif" />" />
							</div>
						</div>
					</div>
				</div>
				<br>
				<div class="row-fluid">
					<div class="span12">
					</div>
				</div>
			</div>
		</div>
		<script type="text/javascript">
		var data = [], totalPoints = 300;
		
		function byteString(st) {
			st = new Number(st);
			
			if (st > (1024 * 1024 * 1024)) {
				return (st / (1024 * 1024 * 1024)).toFixed(2) + 'GB';
			} else if (st > (1024 * 1024)) {
				return (st / (1024 * 1024)).toFixed(2) + 'MB';
			} else if (st > (1024)) {
				return (st / (1024)).toFixed(2) + 'KB';
			} else {
				return (st < 0 ? 0 : st).toFixed(2) + 'B';
			}
		}
		
		function showSwap(data) {
			var total = new Number(data[3].lastvalue);
			var free = new Number(data[2].lastvalue);
			var used = total - free;

			$.plot($('#swapchart'), 
				[{ label: "Used space (" + byteString(used) + ")", data: used }, 
				{ label: "Free space (" + byteString(free) + ")", data: free }], {
				series: {
					pie: {
						show: true
					}
				},
				legend: {
					show: true
				}
			});
		}
		
		function showDisk(data) {
			var free = new Number(data[0].lastvalue);
			var total = new Number(data[1].lastvalue);
			var used = total - free;

			$.plot($('#diskchart'), 
				[{ label: "Used space (" + byteString(used) + ")", data: used }, 
				{ label: "Free space (" + byteString(free) + ")", data: free }], {
				series: {
					pie: {
						show: true
					}
				},
				legend: {
					show: true
				}
			});
		}
		
		function updateUz() {
			$.getJSON('<@s.url />/data.json', function(data) {
				var uz = data.histories1;
				var i = uz.length;
				
				var uzdata = [];
				
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
				
				var max = 0;
				var min = 9999;
				var avg = 0;
				var last = 0;
				
				for (r in uz) {
					r = uz[r];
					var val = new Number(r.value);
					
					uzdata.push([i, val]);
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
				
				avg = avg / uz.length;
				$('#td-last-proc').text(last.toFixed(2));
				$('#td-min-proc').text(min.toFixed(2));
				$('#td-avg-proc').text(avg.toFixed(2));
				$('#td-max-proc').text(max.toFixed(2));
				
				$.plot($("#networkchart"), [
					{ label: "CPU Usage", color : 'green', data: uzdata},
				], option);
				
				showDisk(data.diskSpaces);
				showSwap(data.diskSpaces);
			});
			
			setTimeout(updateUz, 1000 * 60);
		}
		
		$(function() {
			updateUz();
			
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