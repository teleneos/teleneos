<html>
	<head>
		<title><@s.text name="page.performance.title" /></title>
		<meta name="header" content="<@s.text name="page.performance.header" />">
		<!--[if lte IE 8]><script language="javascript" type="text/javascript" src="<@s.url value="/static/explorercanvas/r3/excanvas.min.js" />"></script><![endif]-->
		<script type="text/javascript" src="<@s.url value="/static/flot/0.8.0/jquery.flot.min.js" />"></script>
	</head>
	<body>
		<div class="row-fluid">
			<#include "/view/decorator/nav/network-sidenav.ftl" />
			<div class="span10">
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
									<td>Values processed by server per second</td>
									<td>[avg]</td>
									<td id="td-last-proc" class="speed"></td>
									<td id="td-min-proc" class="speed"></td>
									<td id="td-avg-proc" class="speed"></td>
									<td id="td-max-proc" class="speed"></td>
								</tr>
							</tbody>
						</table>
					</div>
				</div>
			</div>
		</div>
		<script type="text/javascript">
		function updateData() {
			$.getJSON('<@s.url />/data.json', function(data) {
				var performance = data.history;
				var result = performance.result;
				var i = result.length;
				
				var inData = [];
				
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
				
				for (r in result) {
					r = result[r];
					var val = new Number(r.value);
					
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
				
				avg = avg / result.length;
				$('#td-last-proc').text(last.toFixed(2));
				$('#td-min-proc').text(min.toFixed(2));
				$('#td-avg-proc').text(avg.toFixed(2));
				$('#td-max-proc').text(max.toFixed(2));
				
				$.plot($("#chart"), [
					{ label: "Values processed by server", data: inData }
				], option);
			});
			
			setTimeout(updateData, 1000 * 60);
		}
		
		$(function() {
			updateData();
		});
		</script>
	</body>
</html>