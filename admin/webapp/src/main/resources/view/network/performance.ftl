<html>
	<head>
		<title><@s.text name="Network Performance" /></title>
		<meta name="header" content="<@s.text name="Network performance" />">
		<!--[if lte IE 8]><script language="javascript" type="text/javascript" src="<@s.url value="/static/explorercanvas/r3/excanvas.min.js" />"></script><![endif]-->
		<script type="text/javascript" src="<@s.url value="/static/flot/0.7/jquery.flot.min.js" />"></script>
	</head>
	<body>
		<div class="row-fluid">
			<#include "/view/decorator/nav/network-sidenav.ftl" />
			<div class="span10">
				<div class="row-fluid">
					<div class="span12" style="height: 300px;" id="chart">
					
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
								<td>Incoming network traffic on wlan0</td>
								<td>[avg]</td>
								<td>418.1 Kbps</td>
								<td>3.58 Kbps</td>
								<td>58.78 Kbps</td>
								<td>459.62 Kbps</td>
							</tr>
							<tr>
								<td>Outgoing network traffic on wlan0</td>
								<td>[avg]</td>
								<td>18.1 Kbps</td>
								<td>3.58 Kbps</td>
								<td>58.78 Kbps</td>
								<td>29.62 Kbps</td>
							</tr>
							</tbody>
						</table>
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
			
			var option = { 
				series: {
					lines: { show: true },
					points: { show: false }
				}
			};
			
			$.plot($("#chart"), [{ label: "Outgoing", data: data1 }, { label: "Incoming", data: data2 }], option);
			var yaxisLabel = $("<div class='axisLabel yaxisLabel'></div>")
				.text("Kbps")
				.appendTo($("#chart"));
		});
		</script>
	</body>
</html>