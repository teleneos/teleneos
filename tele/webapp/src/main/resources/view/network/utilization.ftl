<html>
	<head>
		<title><@s.text name="page.utilization.title" /></title>
		<meta name="header" content="<@s.text name="page.utilization.header" />">
		<content tag="sidenav">/view/decorator/nav/network-sidenav.ftl</content>
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
		<div class="block-content collapse in">
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
				<div class="span7">
					<table class="table table-condensed table-striped">
						<thead>
							<tr>
								<th>Item</th>
								<th>Value</th>
							</tr>
						</thead>
						<tbody id="tb-items">
							<tr>
								<td colspan="2">
									<img src="<@s.url value="/images/loading.gif" />" />
								</td>
							</tr>
						</tbody>
					</table>
				</div>
				<div class="span5 well">
					<div>
						<p><strong>Swap Usage</strong></p>
						<div style="height: 250px;margin-bottom: 50px;" id="swapchart">
							<img src="<@s.url value="/images/loading.gif" />" />
						</div>
					</div>
					<div>
						<p><strong>Disk Usage</strong></p>
						<div style="height: 250px;" id="diskchart">
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
		
		function showSwap() {
			$.getJSON('<@s.url />/swap.json', function(data) {
				var total = new Number(data.items[1].lastvalue);
				var free = new Number(data.items[0].lastvalue);
				var used = total - free;

				$.plot($('#swapchart'), 
					[{ label: "Used space (" + byteString(used) + ")", color: "#3C7DC4", data: used }, 
					{ label: "Free space (" + byteString(free) + ")", color: "#5A8F29", data: free }], {
					series: {
						pie: {
							show: true
						}
					},
					legend: {
						show: true
					}
				});
			});
		}
		
		function showDisk() {
			$.getJSON('<@s.url />/disk.json', function(data) {
				var used = new Number(data.items[1].lastvalue);
				var free = new Number(data.items[0].lastvalue);
				var total = new Number(data.items[2].lastvalue);

				$.plot($('#diskchart'), 
					[{ label: "Used space (" + byteString(used) + ")", color: "#3C7DC4", data: used }, 
					{ label: "Free space (" + byteString(free) + ")", color: "#5A8F29", data: free }], {
					series: {
						pie: {
							show: true
						}
					},
					legend: {
						show: true
					}
				});
			});
		}
		
		function updateUz() {
			$.getJSON('<@s.url />/processor.json', function(data) {
				var uz = data.history;
				var result = uz.result;
				var i = result.length;
				
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
				
				for (r in result) {
					r = result[r];
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
				
				avg = avg / result.length;
				$('#td-last-proc').text(last.toFixed(2));
				$('#td-min-proc').text(min.toFixed(2));
				$('#td-avg-proc').text(avg.toFixed(2));
				$('#td-max-proc').text(max.toFixed(2));
				
				$.plot($("#networkchart"), [
					{ label: "CPU Usage", color: "#5A8F29", data: uzdata},
				], option);
			});
			
			setTimeout(updateUz, 1000 * 60);
		}
		
		function showItems() {
			$.getJSON('<@s.url />/items.json', function(data) {
				var tb = $('#tb-items');
				tb.empty();
				
				for (d in data.items) {
					d = data.items[d];
					
					var key = new String(d.key_);
					var fIdx = key.lastIndexOf('[');
					var lIdx = key.lastIndexOf(']');
					
					key = key.substring(fIdx > 0 ? fIdx + 1 : fIdx, lIdx > 0 ? lIdx : key.length);
					var keys = key.split(',');
					var name = d.name;
					
					var tr = $('<tr></tr>');
					
					var tdName = $('<td></td>');
					for (k = 0; k < keys.length; k++) {
						name = name.replace('$' + (k + 1), keys[k]);
					}
					tdName.append(name);
					tr.append(tdName);
					
					var tdVal = $('<td></td>');
					tdVal.append(d.lastvalue + d.units);
					tr.append(tdVal);
					
					tb.append(tr);
				}
			});
		}
		
		$(function() {
			showSwap();
			updateUz();
			showDisk();
			showItems();
		});
		</script>
	</body>
</html>