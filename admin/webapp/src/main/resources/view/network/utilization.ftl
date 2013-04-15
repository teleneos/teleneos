<html>
	<head>
		<title><@s.text name="Utilization" /></title>
		<meta name="header" content="<@s.text name="Network Utilization" />">
		<!--[if lte IE 8]><script language="javascript" type="text/javascript" src="<@s.url value="/static/explorercanvas/r3/excanvas.min.js" />"></script><![endif]-->
		<script type="text/javascript" src="<@s.url value="/static/flot/0.7/jquery.flot.min.js" />"></script>
		<script type="text/javascript" src="<@s.url value="/static/flot/0.7/jquery.flot.pie.min.js" />"></script>
	</head>
	<body>
		<div class="row-fluid">
			<#include "/view/decorator/nav/network-sidenav.ftl" />
			<div class="span10">
				<div class="row-fluid">
					<div class="span12" style="height: 200px;" id="networkchart">
					
					</div>
				</div>
				<hr>
				<div class="row-fluid">
					<div class="span7">
						<table class="table table-condensed table-striped">
							<thead>
								<tr>
									<th>Item</th>
									<th>Value</th>
								</tr>
							</thead>
							<tbody>
								<tr>
									<td>CPU</td>
									<td>processor 0: Genuine ...</td>
								</tr>
								<tr>
									<td>CPU idle time</td>
									<td class="s5">95.75 %</td>
								</tr>
								<tr>
									<td>CPU interrupt time</td>
									<td class="s6">0%</td>
								</tr>
								<tr>
									<td>CPU iowait time</td>
									<td class="s5">2.6 %</td>
								</tr>
								<tr>
									<td>CPU nice time</td>
									<td class="s6">0%</td>
								</tr>
								<tr>
									<td>CPU softirq time</td>
									<td class="s5">0.008342 %</td>
								</tr>
								<tr>
									<td>CPU steal time</td>
									<td class="s6">0%</td>
								</tr>
								<tr>
									<td>CPU system time</td>
									<td class="s5">0.21 %</td>
								</tr>
								<tr>
									<td>CPU user time</td>
									<td class="s5">1.1 %</td>
								</tr>
								<tr>
									<td>Download speed for scenario "web".</td>
									<td>0 Bps</td>
								</tr>
								<tr>
									<td>Download speed for step "op" of scenario "web".</td>
									<td>0 Bps</td>
								</tr>
								<tr>
									<td>Download speed for step "op2" of scenario "web".</td>
									<td>11.08 KBps</td>
								</tr>
								<tr>
									<td>Download speed for step "op3" of scenario "web".</td>
									<td>43.19 KBps</td>
								</tr>
								<tr>
									<td>Failed step of scenario "web".</td>
									<td class="s4">1</td>
								</tr>
								<tr>
									<td>Free disk space on /</td>
									<td>25.79 GB</td>
								</tr>
								<tr>
									<td>Free disk space on / (percentage)</td>
									<td class="s5">81.71 %</td>
								</tr>
							</tbody>
						</table>
					</div>
					<div class="span5 well">
						<div style="height: 250px;margin-bottom: 50px;" id="swapchart"></div>
						<div>
							<p><strong>CPU Usage</strong></p>
							<div style="height: 250px;" id="cpuchart"></div>
						</div>
						<hr>
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
								<td>0.79</td>
								<td>0.58</td>
								<td>0.78</td>
								<td>0.82</td>
							</tr>
							</tbody>
						</table>
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
			
		function random() {
			if (data.length > 0)
				data = data.slice(1);

			// Do a random walk

			while (data.length < totalPoints) {

				var prev = data.length > 0 ? data[data.length - 1] : 50,
					y = prev + Math.random() * 10 - 5;

				if (y < 0) {
					y = 0;
				} else if (y > 100) {
					y = 100;
				}

				data.push(y);
			}

			// Zip the generated y values with the x values

			var res = [];
			for (var i = 0; i < data.length; ++i) {
				res.push([i, data[i]])
			}

			return res;
		}
		
		$(function() {
			var data1 = random();
			
			data = [];
			var data2 = random();
			
			data = [];
			var data3 = random();
			
			var option = { 
				series: {
					lines: { show: true },
					points: { show: false }
				}
			};
			
			$.plot($("#networkchart"), [
				{ label: "Traffic", data: data1 },
			], option);
			
			$.plot($("#cpuchart"), [
				{ label: "CPU 1", data: data1 },
				{ label: "CPU 2", data: data2 },
				{ label: "CPU 3", data: data3 }
			], option);
						
			$.plot($('#swapchart'), [{ label: "Total space (20%)", data: 2 }, { label: "Free space (80%)", data: 8 }], {
				series: {
					pie: {
						show: true
					}
				},
				legend: {
					show: true
				}
			});
			
			$('#swapchart').prepend('<strong>Swap Usage</strong>');
		});
		</script>
	</body>
</html>